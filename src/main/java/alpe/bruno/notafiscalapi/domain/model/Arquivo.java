package alpe.bruno.notafiscalapi.domain.model;

import alpe.bruno.notafiscalapi.domain.enums.TipoDocumento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author brunoabneves
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Arquivo extends ModeloGenerico{

    private String nomeDocumento;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    @Lob
    private byte[] arquivo;
}
