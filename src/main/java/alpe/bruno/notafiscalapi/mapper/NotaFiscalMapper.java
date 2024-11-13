package flauzo.cruz.notafiscalapi.mapper;

import flauzo.cruz.notafiscalapi.domain.dto.NotaFiscalDTO;
import flauzo.cruz.notafiscalapi.domain.model.NotaFiscal;
import org.mapstruct.Mapper;

/**
 * @author flauzo
 */
@Mapper(componentModel = "spring")
public interface NotaFiscalMapper extends EntityMapper<NotaFiscalDTO, NotaFiscal>{
}
