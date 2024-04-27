package alpe.bruno.notafiscalapi.domain.dto;

import alpe.bruno.notafiscalapi.domain.enums.TipoDocumento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob; /**
 * @author brunoabneves
 */
public record ArquivoDTO(
        String nomeDocumento,
        @Enumerated(EnumType.STRING)
        TipoDocumento tipoDocumento,
        @Lob
        byte[] arquivo) {
}
