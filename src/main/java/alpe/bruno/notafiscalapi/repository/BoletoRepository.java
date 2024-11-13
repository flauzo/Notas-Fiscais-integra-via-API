package flauzo.cruz.notafiscalapi.repository;

import flauzo.cruz.notafiscalapi.domain.model.Boleto;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author flauzo
 */
@Repository
@Hidden
public interface BoletoRepository extends JpaRepository<Boleto, Long> {
}
