package alpe.bruno.notafiscalapi.service.impl;

import alpe.bruno.notafiscalapi.client.ValidadorNFService;
import alpe.bruno.notafiscalapi.domain.dto.NotaFiscalDTO;
import alpe.bruno.notafiscalapi.domain.enums.FormaPagamento;
import alpe.bruno.notafiscalapi.domain.enums.StatusNotaFiscal;
import alpe.bruno.notafiscalapi.domain.enums.TipoDocumento;
import alpe.bruno.notafiscalapi.kafka.ProducerKafka;
import alpe.bruno.notafiscalapi.mapper.BoletoMapper;
import alpe.bruno.notafiscalapi.mapper.NotaFiscalMapper;
import alpe.bruno.notafiscalapi.repository.NotaFiscalRepository;
import alpe.bruno.notafiscalapi.service.ArquivoService;
import alpe.bruno.notafiscalapi.service.NotaFiscalService;
import alpe.bruno.notafiscalapi.shared.utils.XMLParserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * @author brunoabneves
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

            //Envia os boletos para um tópico que será consumido pelo serviço de cobrança
            if(formaPagamento.equals(FormaPagamento.BOLETO) && !pdfsBoletos.isEmpty()) {
                pdfsBoletos.forEach(pdf -> {
                    var boleto = boletoService.converterPDFParaBoleto(pdf);
                    producerKafka.send(boletoMapper.toDto(boleto));
                });
            }

            return mapper.toDto(notaSalva);
        }catch (Exception e) {
            logger.info("Erro durante a análise do conteúdo dos arquivos. " + e);
            throw new RuntimeException("Erro durante a análise do conteúdo dos arquivos.", e);
        }
    }
}
