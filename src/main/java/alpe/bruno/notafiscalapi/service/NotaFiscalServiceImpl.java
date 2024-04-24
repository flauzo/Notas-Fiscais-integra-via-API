package alpe.bruno.notafiscalapi.service;

import alpe.bruno.notafiscalapi.domain.dto.NotaFiscalDTO;
import alpe.bruno.notafiscalapi.domain.enums.TipoDocumento;
import alpe.bruno.notafiscalapi.domain.model.Arquivo;
import alpe.bruno.notafiscalapi.mapper.NotaFiscalMapper;
import alpe.bruno.notafiscalapi.repository.NotaFiscalRepository;
import alpe.bruno.notafiscalapi.shared.utils.XMLParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author brunoabneves
 */
@Service
@RequiredArgsConstructor
public class NotaFiscalServiceImpl implements NotaFiscalService{

    private final NotaFiscalRepository repository;
    private final NotaFiscalMapper mapper;

    @Override
    public NotaFiscalDTO processarNotaFiscal(MultipartFile xmlNotaFiscal, MultipartFile pdfBoleto) {

        try {
            var notaFiscal = XMLParserService.parseXML(xmlNotaFiscal);
            var arquivo = new Arquivo(xmlNotaFiscal.getName(), TipoDocumento.XML, xmlNotaFiscal.getBytes());
            notaFiscal.setArquivo(arquivo);

            return mapper.toDto(repository.save(notaFiscal));
        }catch (Exception e) {
            throw new RuntimeException("Erro durante a análise do conteúdo do arquivo XML.", e);
        }
    }
}
