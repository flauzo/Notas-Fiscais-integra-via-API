package flauzo.cruz.notafiscalapi.client;

import flauzo.cruz.notafiscalapi.client.response.ArquivosClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * @author flauzo
 */
@FeignClient(name = "cliente-client", url = "https://www.url.ficticia")
public interface ClienteClient {

    @RequestMapping("/enviar-arquivos/{uuidCliente}")
    ResponseEntity<ArquivosClienteResponse> obterArquivosCliente(@PathVariable("uuidCliente") UUID uuidCliente);
}
