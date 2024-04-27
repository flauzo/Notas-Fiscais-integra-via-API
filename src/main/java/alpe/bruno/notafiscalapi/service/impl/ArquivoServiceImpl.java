package alpe.bruno.notafiscalapi.service.impl;

import alpe.bruno.notafiscalapi.domain.enums.TipoDocumento;
import alpe.bruno.notafiscalapi.domain.model.Arquivo;
import alpe.bruno.notafiscalapi.repository.ArquivoRepository;
import alpe.bruno.notafiscalapi.service.ArquivoService;
import alpe.bruno.notafiscalapi.shared.exception.ArquivoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author brunoabneves
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
            throw new ArquivoException("Erro ao cadastrar arquivo"+ e.getMessage());
        }
    }
}
