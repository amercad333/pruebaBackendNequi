package co.com.amrsoftware.msvc_franquicias.infrastructure.repository.mapper;

import co.com.amrsoftware.msvc_franquicias.domain.model.product.Product;
import co.com.amrsoftware.msvc_franquicias.infrastructure.repository.product.ProductData;
import org.mapstruct.Mapper;

@SuppressWarnings("uncheked")
@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toDto(ProductData data);
    ProductData toEntity(Product data);
}
