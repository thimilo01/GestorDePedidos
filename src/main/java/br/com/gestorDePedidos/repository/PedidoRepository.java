package br.com.gestorDePedidos.repository;

import br.com.gestorDePedidos.entity.Cliente;
import br.com.gestorDePedidos.entity.Pedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
