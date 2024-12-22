package backend.mcsvproductos.services.impl;

import backend.mcsvproductos.mappers.ProductoMapper;
import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;
import backend.mcsvproductos.repositories.ProductoRepository;
import backend.mcsvproductos.services.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper = ProductoMapper.INSTANCE;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductoDtoResponse agregarProducto(ProductoDtoRequest productoDtoResponse) {
        return null;
    }
}
