package alpe.bruno.notafiscalapi.mapper;

import alpe.bruno.notafiscalapi.domain.dto.NotaFiscalDTO;
import alpe.bruno.notafiscalapi.domain.model.NotaFiscal;
import org.mapstruct.Mapper;

/**
 * @author brunoabneves
 */
@Mapper(componentModel = "spring")
public interface NotaFiscalMapper extends EntityMapper<NotaFiscalDTO, NotaFiscal>{
}
