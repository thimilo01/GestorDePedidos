package br.com.gestorDePedidos.service.impl;

import br.com.gestorDePedidos.dto.PedidoDto;
import br.com.gestorDePedidos.service.ClienteService;
import br.com.gestorDePedidos.service.GestorPedidoService;
import br.com.gestorDePedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GestorPedidoServiceImpl implements GestorPedidoService {


    private final PedidoService pedidoService;


    private final ClienteService clienteService;

    @Override
    public void gerenciarNovoPedido(PedidoDto model) {
        clienteService.criarCliente(model.getCodigoCliente());
        pedidoService.criarPedido(model);
    }

}
