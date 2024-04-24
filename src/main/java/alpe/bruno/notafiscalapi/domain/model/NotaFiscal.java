package alpe.bruno.notafiscalapi.domain.model;

import alpe.bruno.notafiscalapi.domain.enums.StatusNotaFiscal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class NotaFiscal extends ModeloGenerico{

    private String numero;
    private String cnpjEmissor;
    private String nomeEmissor;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataOperacao;
    private BigDecimal valorTotal;
    private String descricao;
    private UUID clienteUuid;
    @Enumerated(EnumType.STRING)
    private StatusNotaFiscal status;
    private String observacao;
    // private List<UUID> itensUuids;

    @OneToOne(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
    private Arquivo arquivo;
}
