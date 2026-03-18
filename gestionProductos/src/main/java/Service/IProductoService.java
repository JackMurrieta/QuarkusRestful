package Service;

import DTO.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> findAll();

    ProductoDTO findById(Long id);

    void actualizarProductoStock(Long idProducto, int cantidad);

}
