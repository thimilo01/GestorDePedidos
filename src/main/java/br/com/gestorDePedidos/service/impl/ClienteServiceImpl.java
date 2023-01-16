package br.com.gestorDePedidos.service.impl;

import br.com.gestorDePedidos.dto.ClienteDto;
import br.com.gestorDePedidos.entity.Cliente;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.repository.ClienteRepository;
import br.com.gestorDePedidos.service.ClienteService;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;


    @Override
    public Cliente buscarClientePorId(int codigoCliente) {
        Optional<Cliente> cliente = repository.findById(codigoCliente);
        if(cliente.isPresent()){
            return cliente.get();
        };
        throw new NaoEncontradoException("Não encontramos nenhum cliente com esse código!");
    }

    @Override
    public void criarCliente(int codigoCliente) {
        Cliente cliente = clienteExistente(codigoCliente);
        if(Objects.isNull(cliente)){
            repository.save(Cliente.builder()
                    .codigoCliente(codigoCliente)
                    .totalPedidosCliente(1)
                    .build());
        }else {
            repository.save(Cliente.builder()
                    .codigoCliente(cliente.getCodigoCliente())
                    .totalPedidosCliente(cliente.getTotalPedidosCliente() + 1)
                    .build());
        }
    }

    private Cliente clienteExistente(int codigoCliente){
        Optional<Cliente> cliente = repository.findById(codigoCliente);
        if(cliente.isPresent()){
            return cliente.get();
        };
        return null;
    }
}
