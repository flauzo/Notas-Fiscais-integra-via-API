package flauzo.cruz.notafiscalapi.client;

import flauzo.cruz.notafiscalapi.shared.exception.ArquivoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author flauzo
 */
@Service
@RequiredArgsConstructor
public class ValidadorNFService {

    private final ValidadorNFClient client;

    public void validarNotaFiscalPorChaveDeAcesso(String chaveAcesso) {
        var notaFiscalValida = client.validarNotaFiscal(chaveAcesso).getBody();
        if(notaFiscalValida == null || !notaFiscalValida.contains("Nota fiscal validada com Sucesso!")) {
            throw new ArquivoException("Nota fiscal inválida ou não existe.");
        }
    }
}
