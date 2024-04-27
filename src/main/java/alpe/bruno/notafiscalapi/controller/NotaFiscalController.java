package alpe.bruno.notafiscalapi.controller;

import alpe.bruno.notafiscalapi.domain.dto.NotaFiscalDTO;
import alpe.bruno.notafiscalapi.domain.enums.FormaPagamento;
import alpe.bruno.notafiscalapi.service.NotaFiscalService;
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
 * @author brunoabneves
 */
@RestController
@RequestMapping("/notas-fiscais")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotaFiscalController {

    private NotaFiscalService service;

    @Operation(summary = "Receber arquivos", description = "recebe nota fiscal e pdfs do ERP de clientes.")
    @PostMapping(value = "/receber-arquivos/{uuidCliente}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<NotaFiscalDTO> receberArquivos(@RequestPart("xmlNotaFiscal") MultipartFile xmlNotaFiscal,
                                                        @RequestPart("pdfs") List<MultipartFile> pdfs,
                                                        @PathVariable("uuidCliente") UUID uuidCliente,
                                                        @RequestParam FormaPagamento formaPagamento) {

        return ResponseEntity.ok(service.processarNotaFiscal(uuidCliente, xmlNotaFiscal, pdfs, formaPagamento));
    }
}
