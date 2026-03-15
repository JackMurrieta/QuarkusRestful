package Service;

import DTO.ProductoDTO;
import Entity.Producto;
import Repository.ProductoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProductoService implements IProductoService {
    @Inject
    ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> findAll() {
        return productoRepository.listAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public ProductoDTO findById(Long id) {
        Producto entity = productoRepository.findById(id);
        return toDTO(entity);
    }

    private ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.id = producto.id;
        dto.nombre = producto.nombre;
        dto.precio = producto.precio;
        dto.stock = producto.stock;
        return dto;

    }
}
