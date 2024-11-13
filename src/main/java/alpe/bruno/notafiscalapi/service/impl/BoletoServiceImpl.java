package flauzo.cruz.notafiscalapi.service.impl;

import flauzo.cruz.notafiscalapi.domain.enums.TipoDocumento;
import flauzo.cruz.notafiscalapi.domain.model.Arquivo;
import flauzo.cruz.notafiscalapi.domain.model.Boleto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author flauzo
 */
@Service
public class BoletoServiceImpl implements BoletoService{

    @Override
    public Boleto converterPDFParaBoleto(MultipartFile file) {
        try {
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String texto = pdfStripper.getText(document);
            var arquivo = new Arquivo(file.getOriginalFilename(), TipoDocumento.PDF, file.getBytes());
            var boleto = Boleto.builder()
                    .linhaDigitavel(extrairLinhaDigitavel(texto))
                    .valor(extrairValor(texto))
                    .numeroDocumento(extrairNumeroDocumento(texto))
                    .beneficiario(extrairBeneficiario(texto))
                    .dataVencimento(extrairDataVencimento(texto))
                    .arquivo(arquivo)
                    .build();

            document.close();
            return boleto;
        } catch (IOException e) {
            throw new RuntimeException("Erro na Conversão de arquivo.", e);
        }
    }

    private String extrairLinhaDigitavel(String texto) {
        // Exemplo de expressão regular para extrair a linha digitável de um boleto.
        Pattern pattern = Pattern.compile("\\d{5}\\.\\d{5} \\d{5}\\.\\d{6} \\d{5}\\.\\d{6} \\d \\d{14}");
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    private BigDecimal extrairValor(String texto) {
        // Exemplo de expressão regular para extrair um valor monetário.
        Pattern pattern = Pattern.compile("Valor do Documento\\s+([\\d\\.,]+)");
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find()) {
            String valorString = matcher.group(1).replace(".", "").replace(",", ".");
            return new BigDecimal(valorString);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private String extrairNumeroDocumento(String texto) {
        // Exemplo de expressão regular para extrair um número de documento de 10 dígitos.
        Pattern pattern = Pattern.compile("Número do Documento\\s+(\\d+)");
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    private String extrairBeneficiario(String texto) {
        // Exemplo de expressão regular para extrair o beneficiário do boleto.
        Pattern pattern = Pattern.compile("Cedente\\s+([^\\n]+)");
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return null;
        }
    }

    private LocalDateTime extrairDataVencimento(String texto) {
        // Exemplo de expressão regular para extrair a data de vencimento do boleto.
        Pattern pattern = Pattern.compile("Vencimento: (\\d{2}/\\d{2}/\\d{4})");
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find()) {
            String dataString = matcher.group(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dataString, formatter).atStartOfDay();
        } else {
            return null;
        }
    }
}
