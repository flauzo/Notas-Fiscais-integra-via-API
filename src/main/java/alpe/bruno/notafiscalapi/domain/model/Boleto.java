package flauzo.cruz.notafiscalapi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author flauzo
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Boleto extends ModeloGenerico {

    private String linhaDigitavel;
    private BigDecimal valor;
    private String numeroDocumento;
    private String beneficiario;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataVencimento;

    @OneToOne
    @JoinColumn(name = "arquivo_id")
    private Arquivo arquivo;

    @OneToMany
    @JoinColumn(name = "boleto_id")
    private List<NotaFiscal> notasFiscais;
}
