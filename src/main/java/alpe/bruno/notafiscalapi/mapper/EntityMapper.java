package alpe.bruno.notafiscalapi.mapper;

import java.util.List;
import java.util.Set;

public interface EntityMapper<D, E> {

    D toDto(E entity);
    E toModel(D dto);
    List<D> toDto(List<E> entityList);
}
