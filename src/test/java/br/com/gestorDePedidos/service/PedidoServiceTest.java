package br.com.gestorDePedidos.service;

import br.com.gestorDePedidos.configuration.mapper.ObjectMapperUtil;
import br.com.gestorDePedidos.dto.ItemDto;
import br.com.gestorDePedidos.dto.PedidoDto;
import br.com.gestorDePedidos.entity.Cliente;
import br.com.gestorDePedidos.entity.Item;
import br.com.gestorDePedidos.entity.Pedido;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.repository.PedidoRepository;
import br.com.gestorDePedidos.service.impl.PedidoServiceImpl;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PedidoServiceTest {

    private float valorTotal;

    @InjectMocks
    private PedidoServiceImpl service;

    @Mock
    private PedidoRepository repository;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void novoPedido() {
        PedidoDto dto = mockPedidoDto();
        when(clienteService.buscarClientePorId(dto.getCodigoCliente())).thenReturn(
                Cliente.builder().codigoCliente(dto.getCodigoCliente()).totalPedidosCliente(1).build());
        service.criarPedido(dto);
        verify(repository).save(Pedido.builder()
                .codigoPedido(dto.getCodigoPedido())
                .cliente(clienteService.buscarClientePorId(dto.getCodigoCliente()))
                .items(ConvertListSomaTotalPedido(dto.getItens()))
                .valorTotal(valorTotal)
                .dataPedido(any())
                .build());
    }

    @Test
    public void buscarPedidoPorIdSucess() {
        when(repository.findById(1)).thenReturn(Optional.of(Pedido.builder().valorTotal(66.00F).build()));
        Pedido pedido = service.buscarPedidoPorId(1);
        assertEquals(pedido.getValorTotal(), 66.00F);
    }

    @Test
    public void buscarPedidosPorCliente() {
        when(repository.findByCliente(Cliente.builder().codigoCliente(1).build())).thenReturn(List.of());
           List<Pedido> listPedido = service.buscarPedidosPorCliente(1);
        assertEquals(listPedido.stream().count(), 0);
    }

    @Test
    public void buscarPedidoPorIdErroNaoEncontrado() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> {
            service.buscarPedidoPorId(2);
        });
    }


    private PedidoDto mockPedidoDto() {
        PedidoDto dto = new PedidoDto();
        ItemDto itemDto = new ItemDto();
        ItemDto itemDto2 = new ItemDto();
        itemDto.setProduto("Cerveja");
        itemDto.setQuantidade(100);
        itemDto.setPreco(5.25F);
        itemDto2.setProduto("Caipirinha");
        itemDto2.setQuantidade(10);
        itemDto2.setPreco(15.00F);
        dto.setCodigoCliente(1);
        dto.setCodigoPedido(10);
        dto.setItens(Arrays.asList(itemDto, itemDto2));
        return dto;
    }

    private List<Item> ConvertListSomaTotalPedido(List<ItemDto> itensDto) {
        valorTotal = 0;
        List<Item> itens = new ArrayList<>();
        itensDto.forEach(item -> {
            itens.add(ObjectMapperUtil.convertTo(item, Item.class));
            float totalValorItem = item.getPreco() * item.getQuantidade();
            valorTotal += totalValorItem;
        });
        return itens;
    }
}
