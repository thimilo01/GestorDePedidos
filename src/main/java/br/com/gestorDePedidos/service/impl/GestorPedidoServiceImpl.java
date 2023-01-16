package br.com.gestorDePedidos.service.impl;

import br.com.gestorDePedidos.dto.PedidoDto;
import br.com.gestorDePedidos.entity.Cliente;
import br.com.gestorDePedidos.service.ClienteService;
import br.com.gestorDePedidos.service.GestorPedidoService;
import br.com.gestorDePedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GestorPedidoServiceImpl implements GestorPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Override
    public void gerenciarNovoPedido(PedidoDto model) {
        clienteService.criarCliente(model.getCodigoCliente());
        pedidoService.criarPedido(model);
    }

}
