package flauzo.cruz.notafiscalapi.domain.dto;

import flauzo.cruz.notafiscalapi.domain.enums.TipoDocumento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob; 
/**
 * @author flauzo
 */
public record ArquivoDTO(
        String nomeDocumento,
        @Enumerated(EnumType.STRING)
        TipoDocumento tipoDocumento,
        @Lob
        byte[] arquivo) {
}
