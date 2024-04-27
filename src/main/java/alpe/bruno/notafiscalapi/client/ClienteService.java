package alpe.bruno.notafiscalapi.client;

import alpe.bruno.notafiscalapi.client.ClienteClient;
import alpe.bruno.notafiscalapi.client.response.ArquivosClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author brunoabneves
 */
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteClient client;

    public ArquivosClienteResponse obterArquivosCliente(UUID uuidCliente) {
        return client.obterArquivosCliente(uuidCliente).getBody();
    }
}
