package alpe.bruno.notafiscalapi.domain.model;

import alpe.bruno.notafiscalapi.domain.enums.FormaPagamento;
import alpe.bruno.notafiscalapi.domain.enums.StatusNotaFiscal;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author brunoabneves
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotaFiscal extends ModeloGenerico{

    private String numero;
    private String chaveAcesso;
    private String cnpjEmissor;
    private String nomeEmissor;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataOperacao;

    private BigDecimal valorTotal;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusNotaFiscal status;

    private String observacao;

    private UUID uuidClienteExterno;

    @Enumerated(EnumType.STRING)
    FormaPagamento formaPagamento;
    // private List<UUID> itensUuids;

    @OneToOne
    @JoinColumn(name = "arquivo_id")
    private Arquivo arquivo;
}
