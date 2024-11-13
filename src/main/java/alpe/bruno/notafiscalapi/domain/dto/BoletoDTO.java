package flauzo.cruz.notafiscalapi.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author flauzo
 */
@Builder
public record BoletoDTO(
        UUID uuid,
        String linhaDigitavel,
        BigDecimal valor,
        String numeroDocumento,
        String beneficiario,
        LocalDateTime dataVencimento,
        UUID uuidClienteExterno
        ) {
}
