package flauzo.cruz.notafiscalapi.domain.model;

import flauzo.cruz.notafiscalapi.domain.enums.FormaPagamento;
import flauzo.cruz.notafiscalapi.domain.enums.StatusNotaFiscal;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author flauzo
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
