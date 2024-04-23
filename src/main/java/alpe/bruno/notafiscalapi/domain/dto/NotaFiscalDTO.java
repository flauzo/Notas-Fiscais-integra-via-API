package alpe.bruno.notafiscalapi.domain.dto;

import alpe.bruno.notafiscalapi.domain.enums.FormaPagamento;
import alpe.bruno.notafiscalapi.domain.enums.StatusNotaFiscal;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID; /**
 * @author brunoabneves
 */
public record NotaFiscalDTO(
        String numero,
        String cnpjEmissor,
        String nomeEmissor,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataOperacao,
        BigDecimal valorTotal,
        String descricao,
        UUID clienteUuid,
        @Enumerated(EnumType.STRING)
        StatusNotaFiscal status,
        @Enumerated(EnumType.STRING)
        FormaPagamento formaPagamento,
        String observacao,
        List<UUID> itensUuids
        )
{
}
