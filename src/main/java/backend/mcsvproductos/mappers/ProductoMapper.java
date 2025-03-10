package backend.mcsvproductos.mappers;

import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;
import backend.mcsvproductos.models.entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductoMapper {
    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(target = "id", ignore = true)
    Producto toEntity(ProductoDtoRequest dto);

    Producto toEntity(ProductoDtoResponse dto);

    ProductoDtoResponse toDto(Producto entity);

    List<ProductoDtoResponse> toDtoList(List<Producto> list);
}
