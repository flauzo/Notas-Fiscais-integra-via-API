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
@FeignClient(name = "cobranca-client", url = "https://www.url.ficticia")
public interface CobrancaClient {

    @RequestMapping("/cobrancas/processar-boleto")
    ResponseEntity<ArquivosClienteResponse> obterArquivosCliente(@PathVariable("uuid") UUID uuid);
}
