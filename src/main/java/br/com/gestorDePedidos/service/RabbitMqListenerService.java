package br.com.gestorDePedidos.service;

import br.com.gestorDePedidos.configuration.mapper.ObjectMapperUtil;
import br.com.gestorDePedidos.dto.PedidoDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqListenerService implements MessageListener {

    private GestorPedidoService service;

    public RabbitMqListenerService(GestorPedidoService service){
        this.service = service;
    }
    public void onMessage(Message message) {
        PedidoDto pedidoDto = ObjectMapperUtil.convertMsgTo(new String(message.getBody()) , PedidoDto.class);
        service.gerenciarNovoPedido(pedidoDto);
    }
}
