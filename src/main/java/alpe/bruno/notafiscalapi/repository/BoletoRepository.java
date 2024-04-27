package alpe.bruno.notafiscalapi.repository;

import alpe.bruno.notafiscalapi.domain.model.Boleto;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author brunoabneves
 */
@Repository
@Hidden
public interface BoletoRepository extends JpaRepository<Boleto, Long> {
}
