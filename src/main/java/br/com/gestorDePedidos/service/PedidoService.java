package br.com.gestorDePedidos.service;

import br.com.gestorDePedidos.dto.PedidoDto;
import br.com.gestorDePedidos.entity.Pedido;

public interface PedidoService {

    public void criarPedido(PedidoDto model);

    public Pedido buscarPedido(int codigoPedido);
}
