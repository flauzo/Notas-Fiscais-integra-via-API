package flauzo.cruz.notafiscalapi.service.impl;

import flauzo.cruz.notafiscalapi.domain.model.Boleto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author flauzo
 */
public interface BoletoService {

    Boleto converterPDFParaBoleto(MultipartFile file);
}
