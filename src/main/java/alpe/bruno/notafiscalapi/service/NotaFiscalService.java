package alpe.bruno.notafiscalapi.service;

import alpe.bruno.notafiscalapi.domain.dto.NotaFiscalDTO;
import alpe.bruno.notafiscalapi.domain.enums.FormaPagamento;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * @author brunoabneves
 */
public interface NotaFiscalService {
    NotaFiscalDTO processarNotaFiscal(UUID uuidCliente, MultipartFile xmlNotaFiscal,
                                      List<MultipartFile> pdfsBoletos, FormaPagamento formaPagamento);
}
