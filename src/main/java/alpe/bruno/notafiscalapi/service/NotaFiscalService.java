package flauzo.cruz.notafiscalapi.service;

import flauzo.cruz.notafiscalapi.domain.dto.NotaFiscalDTO;
import flauzo.cruz.notafiscalapi.domain.enums.FormaPagamento;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * @author flauzo
 */
public interface NotaFiscalService {
    NotaFiscalDTO processarNotaFiscal(UUID uuidCliente, MultipartFile xmlNotaFiscal,
                                      List<MultipartFile> pdfsBoletos, FormaPagamento formaPagamento);
}
