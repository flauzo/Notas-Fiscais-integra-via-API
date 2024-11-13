package flauzo.cruz.notafiscalapi.client.response;

import flauzo.cruz.notafiscalapi.domain.enums.FormaPagamento;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * @author flauzo
 */
public record ArquivosClienteResponse(
        MultipartFile xmlNotaFiscal,
        List<MultipartFile> pdfsBoletos,
        UUID uuidCliente,
        FormaPagamento formaPagamento
) {
}
