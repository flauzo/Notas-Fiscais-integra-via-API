package flauzo.cruz.notafiscalapi.repository;

import flauzo.cruz.notafiscalapi.domain.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author flauzo
 */
@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {
}
