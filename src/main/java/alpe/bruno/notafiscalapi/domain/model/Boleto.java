package alpe.bruno.notafiscalapi.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author brunoabneves
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Boleto extends ModeloGenerico{

    private String codigoBarras;
    private BigDecimal valor;
    private Arquivo arquivo;
    @OneToOne(mappedBy = "boleto", cascade = CascadeType.ALL)
    private DadosPagamento dadosPagamento;
}
