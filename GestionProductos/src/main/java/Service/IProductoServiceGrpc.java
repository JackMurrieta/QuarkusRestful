//package Service;
//
//import com.itson.grpc.Empty;
//import com.itson.grpc.ListaProductosResponse;
//import com.itson.grpc.ProductoResponse;
//import com.itson.grpc.ProductoUpdateRequest;
//import com.itson.org.ProductoRequest;
//import io.smallrye.mutiny.Uni;
//import org.antlr.v4.runtime.atn.SemanticContext;
//
//public interface IProductoServiceGrpc {
//    public Uni<ProductoResponse> verificarYObtener(ProductoRequest request);
//    //efinir metodos de IProductoService normel http
//
//    public Uni<ListaProductosResponse> obtenerTodos(Empty request);
//
//    public Uni<Empty> actualizarProductoStock(ProductoUpdateRequest request);
//}
