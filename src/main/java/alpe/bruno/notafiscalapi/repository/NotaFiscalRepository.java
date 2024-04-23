package alpe.bruno.notafiscalapi.repository;

import alpe.bruno.notafiscalapi.domain.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author brunoabneves
 */
@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {
}
