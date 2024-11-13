package flauzo.cruz.notafiscalapi.service.impl;

import flauzo.cruz.notafiscalapi.domain.enums.TipoDocumento;
import flauzo.cruz.notafiscalapi.domain.model.Arquivo;
import flauzo.cruz.notafiscalapi.repository.ArquivoRepository;
import flauzo.cruz.notafiscalapi.service.ArquivoService;
import flauzo.cruz.notafiscalapi.shared.exception.ArquivoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author flauzo
 */

@Service
@RequiredArgsConstructor
@Transactional
public class ArquivoServiceImpl implements ArquivoService {

    private final ArquivoRepository repository;

    @Override
    public Arquivo cadastrar(MultipartFile file, TipoDocumento tipoDocumento) {
        try {
            Arquivo arquivo = new Arquivo(file.getOriginalFilename(), tipoDocumento, file.getBytes());
            return repository.save(arquivo);
        } catch (IOException e) {
            throw new ArquivoException("Erro ao cadastrar arquivo."+ e.getMessage());
        }
    }
}
