package alpe.bruno.notafiscalapi.client.response;

import alpe.bruno.notafiscalapi.domain.enums.FormaPagamento;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * @author brunoabneves
 */
public record ArquivosClienteResponse(
        MultipartFile xmlNotaFiscal,
        List<MultipartFile> pdfsBoletos,
        UUID uuidCliente,
        FormaPagamento formaPagamento
) {
}
