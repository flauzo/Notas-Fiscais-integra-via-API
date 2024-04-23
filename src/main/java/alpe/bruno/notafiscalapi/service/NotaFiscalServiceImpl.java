package alpe.bruno.notafiscalapi.service;

import alpe.bruno.notafiscalapi.domain.dto.NotaFiscalDTO;
import alpe.bruno.notafiscalapi.repository.NotaFiscalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author brunoabneves
 */
@Service
@RequiredArgsConstructor
public class NotaFiscalServiceImpl implements NotaFiscalService{

    private final NotaFiscalRepository repository;

    @Override
    public NotaFiscalDTO processarNotaFiscal(MultipartFile xmlNotaFiscal, MultipartFile pdfBoleto) {
        return null;
    }
}
