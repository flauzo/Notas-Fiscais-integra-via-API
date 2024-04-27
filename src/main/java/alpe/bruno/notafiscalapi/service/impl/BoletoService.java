package alpe.bruno.notafiscalapi.service.impl;

import alpe.bruno.notafiscalapi.domain.model.Boleto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author brunoabneves
 */
public interface BoletoService {

    Boleto converterPDFParaBoleto(MultipartFile file);
}
