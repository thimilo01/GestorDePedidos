package br.com.gestorDePedidos.service;

import br.com.gestorDePedidos.configuration.mapper.ObjectMapperUtil;
import br.com.gestorDePedidos.model.Pedido;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqListenerService implements MessageListener {
    public void onMessage(Message message) {
        Pedido pedido = ObjectMapperUtil.convertMsgTo(new String(message.getBody()) ,Pedido.class);
    }
}
