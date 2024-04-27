package alpe.bruno.notafiscalapi.mapper;

import alpe.bruno.notafiscalapi.domain.dto.BoletoDTO;
import alpe.bruno.notafiscalapi.domain.model.Boleto;
import org.mapstruct.Mapper;

/**
 * @author brunoabneves
 */
@Mapper(componentModel = "spring")
public interface BoletoMapper extends EntityMapper<BoletoDTO, Boleto>{
}
