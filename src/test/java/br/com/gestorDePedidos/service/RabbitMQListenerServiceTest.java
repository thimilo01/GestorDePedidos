package br.com.gestorDePedidos.service;

import br.com.gestorDePedidos.dto.ItemDto;
import br.com.gestorDePedidos.dto.PedidoDto;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Message;


import static org.mockito.Mockito.verify;

public class RabbitMQListenerServiceTest {

    private static final String message = "{\"codigoPedido\":10,\"codigoCliente\":1,\"itens\":[{\"produto\":\"Cerveja\",\"quantidade\":100,\"preco\":5.25},{\"produto\":\"Caipirinha\",\"quantidade\":10,\"preco\":15.00}]}";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void onMessage()  {
        GestorPedidoService  gestorPedidoService = Mockito.spy(GestorPedidoService.class);
        RabbitMqListenerService service = new RabbitMqListenerService(gestorPedidoService);
        Message msg = new Message(message.getBytes());
        service.onMessage(msg);

        verify(gestorPedidoService).gerenciarNovoPedido(mockPedidoDto());
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
}
