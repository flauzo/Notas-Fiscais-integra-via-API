package alpe.bruno.notafiscalapi.shared.utils;

import alpe.bruno.notafiscalapi.domain.model.NotaFiscal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author brunoabneves
 */
@Service
public class XMLParserService {

    private static final DateTimeFormatter parser = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static NotaFiscal extrairCamposDoXML(MultipartFile file) {
        try {

            String xmlContent = new String(file.getBytes());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlContent)));
            Element rootElement = document.getDocumentElement();

            return buildNotaFiscal(file, rootElement);
        } catch (Exception e) {
            throw  new RuntimeException("Erro ao processar arquivo", e);
        }
    }

    private static NotaFiscal buildNotaFiscal(MultipartFile file, Element rootElement) throws IOException {
        return NotaFiscal.builder()
                .numero(getElementTextByTagName(rootElement, "Numero"))
                .chaveAcesso(getElementTextByTagName(rootElement, "CodigoVerificacao"))
                .cnpjEmissor(getElementTextByTagName(rootElement, "Cnpj"))
                .nomeEmissor(getElementTextByTagName(rootElement, "RazaoSocial"))
                .dataOperacao(LocalDateTime.parse(Objects.requireNonNull(getElementTextByTagName(rootElement, "DataEmissao")), parser))
                .valorTotal(new BigDecimal(Objects.requireNonNull(getElementTextByTagName(rootElement, "ValorLiquidoNfse"))))
                .descricao(getElementTextByTagName(rootElement, "Discriminacao"))
                .build();
    }

    private static String getElementTextByTagName(org.w3c.dom.Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return null;
        }
    }

//    private static NotaFiscal mapearNotaFiscal(Element element) {
//        NotaFiscal notaFiscal = new NotaFiscal();
//        notaFiscal.setNumero(element.getAttribute("Numero"));
//        notaFiscal.setCnpjEmissor(element.getAttribute("Cnpj"));
//        notaFiscal.setNomeEmissor(element.getAttribute("RazaoSocial"));
//
//        String dataOperacaoStr = element.getAttribute("DataEmissao");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//        LocalDateTime dataOperacao = LocalDateTime.parse(dataOperacaoStr, formatter);
//        notaFiscal.setDataOperacao(dataOperacao);
//
//        String valorTotalStr = element.getAttribute("ValorLiquidoNfse");
//        BigDecimal valorTotal = new BigDecimal(valorTotalStr);
//        notaFiscal.setValorTotal(valorTotal);
//
//        notaFiscal.setDescricao(element.getAttribute("Discriminacao"));
//
//        NodeList itensNodeList = element.getElementsByTagName("item");
//        List<UUID> itensUuids = new ArrayList<>();
//        for (int i = 0; i < itensNodeList.getLength(); i++) {
//            Element itemElement = (Element) itensNodeList.item(i);
//            UUID itemUuid = UUID.fromString(itemElement.getAttribute("uuid"));
//            itensUuids.add(itemUuid);
//        }
//        notaFiscal.setItensUuids(itensUuids);
//        return notaFiscal;
//    }
}
