package flauzo.cruz.notafiscalapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "nf-validator-client", url = "${feign.client.url.nfvalidator}")
public interface ValidadorNFClient {

    @RequestMapping("/validar/{chaveAcesso}")
    ResponseEntity<String> validarNotaFiscal(@PathVariable("chaveAcesso") String chaveAcesso);
}
