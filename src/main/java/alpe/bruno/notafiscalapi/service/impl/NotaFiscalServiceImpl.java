package flauzo.cruz.notafiscalapi.service.impl;

import flauzo.cruz.notafiscalapi.client.ValidadorNFService;
import flauzo.cruz.notafiscalapi.domain.dto.NotaFiscalDTO;
import flauzo.cruz.notafiscalapi.domain.enums.FormaPagamento;
import flauzo.cruz.notafiscalapi.domain.enums.StatusNotaFiscal;
import flauzo.cruz.notafiscalapi.domain.enums.TipoDocumento;
import flauzo.cruz.notafiscalapi.kafka.ProducerKafka;
import flauzo.cruz.notafiscalapi.mapper.BoletoMapper;
import flauzo.cruz.notafiscalapi.mapper.NotaFiscalMapper;
import flauzo.cruz.notafiscalapi.repository.NotaFiscalRepository;
import flauzo.cruz.notafiscalapi.service.ArquivoService;
import flauzo.cruz.notafiscalapi.service.NotaFiscalService;
import flauzo.cruz.notafiscalapi.shared.utils.XMLParserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * @author flauzo
 */
@Service
@RequiredArgsConstructor
@Transactional
public class NotaFiscalServiceImpl implements NotaFiscalService {

    private final NotaFiscalRepository repository;
    private final NotaFiscalMapper mapper;
    private final BoletoMapper boletoMapper;
    private final ValidadorNFService validadorNFService;
    private final ProducerKafka producerKafka;
    private final BoletoService boletoService;
    private final ArquivoService arquivoService;

    private static final Logger logger = LoggerFactory.getLogger(NotaFiscalServiceImpl.class);

    @Override
    public NotaFiscalDTO processarNotaFiscal(UUID uuidCliente, MultipartFile xmlNotaFiscal,
                                             List<MultipartFile> pdfsBoletos, FormaPagamento formaPagamento) {
        try {
            var notaFiscal = XMLParserService.extrairCamposDoXML(xmlNotaFiscal);
            validadorNFService.validarNotaFiscalPorChaveDeAcesso(notaFiscal.getChaveAcesso());
            var arquivo = arquivoService.cadastrar(xmlNotaFiscal, TipoDocumento.XML);
            notaFiscal.setUuidClienteExterno(uuidCliente);
            notaFiscal.setStatus(StatusNotaFiscal.EMITIDA);
            notaFiscal.setFormaPagamento(formaPagamento);
            notaFiscal.setArquivo(arquivo);
            var notaSalva = repository.saveAndFlush(notaFiscal);

            // Envia os boletos para um tópico que será consumido pelo serviço de cobrança.
            if(formaPagamento.equals(FormaPagamento.BOLETO) && !pdfsBoletos.isEmpty()) {
                pdfsBoletos.forEach(pdf -> {
                    var boleto = boletoService.converterPDFParaBoleto(pdf);
                    producerKafka.send(boletoMapper.toDto(boleto));
                });
            }

            return mapper.toDto(notaSalva);
        }catch (Exception e) {
            logger.info("Erro durante a Análise do conteúdo dos arquivos. " + e);
            throw new RuntimeException("Erro durante a Análise do conteúdo dos arquivos.", e);
        }
    }
}
