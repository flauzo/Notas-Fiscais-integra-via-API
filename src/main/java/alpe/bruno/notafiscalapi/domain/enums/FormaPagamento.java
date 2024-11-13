package flauzo.cruz.notafiscalapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author flauzo
 */
@Getter
@AllArgsConstructor
public enum FormaPagamento {
    DINHEIRO,
    CARTAO,
    BOLETO,
    PIX
}
