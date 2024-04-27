package alpe.bruno.notafiscalapi.domain.dto;

import alpe.bruno.notafiscalapi.domain.enums.FormaPagamento;
import alpe.bruno.notafiscalapi.domain.enums.StatusNotaFiscal;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author brunoabneves
 */
@Builder
public record NotaFiscalDTO (
        String numero,
        String cnpjEmissor,
        String nomeEmissor,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataOperacao,
        BigDecimal valorTotal,
        String descricao,
        UUID clienteUuidExterno,
        @Enumerated(EnumType.STRING)
        StatusNotaFiscal status,
        @Enumerated(EnumType.STRING)
        FormaPagamento formaPagamento,
        String observacao,
        ArquivoDTO arquivoDTO){
}
