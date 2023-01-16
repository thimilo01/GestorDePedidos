package br.com.gestorDePedidos.service.impl;

import br.com.gestorDePedidos.entity.Cliente;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.repository.ClienteRepository;
import br.com.gestorDePedidos.service.ClienteService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private static final int VALOR_INICIAL = 1;

    private final ClienteRepository repository;

    @Override
    public Cliente buscarClientePorId(int codigoCliente) {
        Optional<Cliente> cliente = repository.findById(codigoCliente);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new NaoEncontradoException("Não encontramos nenhum cliente com esse código!");
    }

    @Override
    public void criarCliente(int codigoCliente) {
        Cliente cliente = clienteExistente(codigoCliente);
        if (Objects.isNull(cliente)) {
            log.info("---Inserindo novo cliente ----");
            repository.save(Cliente.builder()
                    .codigoCliente(codigoCliente)
                    .totalPedidosCliente(VALOR_INICIAL)
                    .build());
        } else {
            log.info("---Atualizando cliente ----");
            repository.save(Cliente.builder()
                    .codigoCliente(cliente.getCodigoCliente())
                    .totalPedidosCliente(cliente.getTotalPedidosCliente() + VALOR_INICIAL)
                    .build());
        }
    }

    @Override
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    private Cliente clienteExistente(int codigoCliente) {
        Optional<Cliente> cliente = repository.findById(codigoCliente);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        return null;
    }
}
