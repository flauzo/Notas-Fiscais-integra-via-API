package flauzo.cruz.notafiscalapi.repository;

import flauzo.cruz.notafiscalapi.domain.model.Arquivo;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author flauzo
 */
@Repository
@Hidden
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
}
