package alpe.bruno.notafiscalapi.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author brunoabneves
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Boleto extends ModeloGenerico {

    private String codigoBarras;
    private BigDecimal valor;
    private Long numeroDocumento;
    private String beneficiario;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataVencimento;

    private Arquivo arquivo;

    @OneToMany(mappedBy = "boleto", cascade = CascadeType.ALL)
    private List<NotaFiscal> notasFiscais;
}
