package alpe.bruno.notafiscalapi.service;

import alpe.bruno.notafiscalapi.domain.dto.NotaFiscalDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author brunoabneves
 */
public interface NotaFiscalService {
    NotaFiscalDTO processarNotaFiscal(MultipartFile xmlNotaFiscal, MultipartFile pdfBoleto);
}
