package br.com.gestorDePedidos.repository;

import br.com.gestorDePedidos.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, Integer> {
}
