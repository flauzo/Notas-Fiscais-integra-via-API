package alpe.bruno.notafiscalapi.domain.model;

import alpe.bruno.notafiscalapi.domain.enums.FormaPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author brunoabneves
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosPagamento extends ModeloGenerico{


    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    private BigDecimal valorPago;
    private LocalDateTime dataPagamento;
}
