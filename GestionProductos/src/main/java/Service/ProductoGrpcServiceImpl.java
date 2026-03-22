package Service;

import Repository.ProductoRepository;
import com.itson.grpc.*;
import com.itson.grpc.ProductoRequest;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.inject.Inject;

@GrpcService  // ← NECESARIO para que Quarkus lo registre como servidor gRPC
public class ProductoGrpcServiceImpl extends MutinyProductoServiceGrpc.ProductoServiceImplBase {

    @Inject
    ProductoRepository productoRepository;


    @Override
    public Uni<ProductoResponse> verificarYObtener(ProductoRequest request) {
        return Uni.createFrom().item(() -> {
            Entity.Producto producto = productoRepository.findById(request.getId());

            if (producto == null) {
                throw new RuntimeException("Producto no encontrado con ID: " + request.getId());
            }

            return ProductoResponse.newBuilder()
                    .setId(producto.id)
                    .setNombre(producto.nombre)
                    .setPrecio(producto.precio)
                    .setStockDisponible(producto.stock)
                    .build();
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    @Override
    public Uni<ListaProductosResponse> obtenerTodos(Empty request) {
        return Uni.createFrom().item(() -> {
            ListaProductosResponse.Builder builder = ListaProductosResponse.newBuilder();

            productoRepository.listAll().forEach(producto ->
                    builder.addProductos(
                            ProductoResponse.newBuilder()
                                    .setId(producto.id)
                                    .setNombre(producto.nombre)
                                    .setPrecio(producto.precio)
                                    .setStockDisponible(producto.stock)
                                    .build()
                    )
            );

            return builder.build();
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    @Override
    public Uni<Empty> actualizarProductoStock(ProductoUpdateRequest request) {
        return Uni.createFrom().item(() -> {
            productoRepository.actualizarProductoStock(
                    request.getIdProducto(),
                    (int) request.getCantidad()
            );
            return Empty.newBuilder().build();
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }
}
