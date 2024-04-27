package alpe.bruno.notafiscalapi.client;

import alpe.bruno.notafiscalapi.client.ValidadorNFClient;
import alpe.bruno.notafiscalapi.shared.exception.ArquivoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author brunoabneves
 */
@Service
@RequiredArgsConstructor
public class ValidadorNFService {

    private final ValidadorNFClient client;

    public void validarNotaFiscalPorChaveDeAcesso(String chaveAcesso) {
        var notaFiscalValida = client.validarNotaFiscal(chaveAcesso).getBody();
        assert notaFiscalValida != null;
        if(!notaFiscalValida) {
            throw new ArquivoException("Nota fiscal inválida ou não existe.");
        }
    }
}
