package flauzo.cruz.notafiscalapi.mapper;

import flauzo.cruz.notafiscalapi.domain.dto.BoletoDTO;
import flauzo.cruz.notafiscalapi.domain.model.Boleto;
import org.mapstruct.Mapper;

/**
 * @author flauzo
 */
@Mapper(componentModel = "spring")
public interface BoletoMapper extends EntityMapper<BoletoDTO, Boleto>{
}
