package alpe.bruno.notafiscalapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author brunoabneves
 */
@Getter
@AllArgsConstructor
public enum StatusNotaFiscal {

    EMITIDA,
    CANCELADA,
    PENDENTE
}
