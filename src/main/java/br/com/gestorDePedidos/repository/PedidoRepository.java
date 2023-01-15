package br.com.gestorDePedidos.repository;

import br.com.gestorDePedidos.entity.Pedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, Integer> {

    Optional<List<Pedido>> findBycodigoCliente(int codigoCliente);
}
