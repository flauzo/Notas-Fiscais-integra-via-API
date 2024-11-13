package flauzo.cruz.notafiscalapi.controller;

import flauzo.cruz.notafiscalapi.domain.dto.NotaFiscalDTO;
import flauzo.cruz.notafiscalapi.domain.enums.FormaPagamento;
import flauzo.cruz.notafiscalapi.service.NotaFiscalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * @author flauzo
 */
@RestController
@RequestMapping("/notas-fiscais")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotaFiscalController {

    private NotaFiscalService service;

    @Operation(summary = "Receber arquivos", description = "recebe Nota Fiscal e PDFs do ERP de clientes.")
    @PostMapping(value = "/receber-arquivos/{uuidCliente}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<NotaFiscalDTO> receberArquivos(@RequestPart("xmlNotaFiscal") MultipartFile xmlNotaFiscal,
                                                        @RequestPart("pdfs") List<MultipartFile> pdfs,
                                                        @PathVariable("uuidCliente") UUID uuidCliente,
                                                        @RequestParam FormaPagamento formaPagamento) {

        return ResponseEntity.ok(service.processarNotaFiscal(uuidCliente, xmlNotaFiscal, pdfs, formaPagamento));
    }
}
