package flauzo.cruz.notafiscalapi.service;

import flauzo.cruz.notafiscalapi.domain.enums.TipoDocumento;
import flauzo.cruz.notafiscalapi.domain.model.Arquivo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author flauzo
 */
public interface ArquivoService {

    Arquivo cadastrar(MultipartFile file, TipoDocumento tipoDocumento);
}
