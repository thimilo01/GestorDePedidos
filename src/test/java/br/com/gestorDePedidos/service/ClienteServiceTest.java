package br.com.gestorDePedidos.service;

import br.com.gestorDePedidos.entity.Cliente;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.repository.ClienteRepository;
import br.com.gestorDePedidos.service.impl.ClienteServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl service;

    @Mock
    private ClienteRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void buscarClientePorIdSucess() {
        when(repository.findById(1)).thenReturn(Optional.of(Cliente.builder().totalPedidosCliente(2).build()));
        Cliente cliente = service.buscarClientePorId(1);
        assertEquals(cliente.getTotalPedidosCliente(), 2);
    }

    @Test
    public void buscarClientePorIdErroNaoEncontrado() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> {
            service.buscarClientePorId(2);
        });
    }

    @Test
    public void buscarTodosSucesso() {
        when(repository.findAll()).thenReturn(List.of());
        List<Cliente> listCliente = service.buscarTodos();
        assertEquals(listCliente.stream().count(), 0);
    }

    @Test
    public void atualizarCliente() {
        when(repository.findById(1)).thenReturn(Optional.of(Cliente.builder().codigoCliente(1).totalPedidosCliente(1).build()));
        service.criarCliente(1);
        verify(repository).save(Cliente.builder().codigoCliente(1).totalPedidosCliente(2).build());
    }

    @Test
    public void novoCliente() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        service.criarCliente(2);
        verify(repository).save(Cliente.builder().codigoCliente(2).totalPedidosCliente(1).build());
    }


}
