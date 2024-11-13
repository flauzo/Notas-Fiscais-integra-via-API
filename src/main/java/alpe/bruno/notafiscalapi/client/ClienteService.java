package flauzo.cruz.notafiscalapi.client;

import flauzo.cruz.notafiscalapi.client.ClienteClient;
import flauzo.cruz.notafiscalapi.client.response.ArquivosClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author flauzo
 */
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteClient client;

    public ArquivosClienteResponse obterArquivosCliente(UUID uuidCliente) {
        return client.obterArquivosCliente(uuidCliente).getBody();
    }
}
