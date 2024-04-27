package alpe.bruno.notafiscalapi.service;

import alpe.bruno.notafiscalapi.domain.enums.TipoDocumento;
import alpe.bruno.notafiscalapi.domain.model.Arquivo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author brunoabneves
 */
public interface ArquivoService {

    Arquivo cadastrar(MultipartFile file, TipoDocumento tipoDocumento);
}
