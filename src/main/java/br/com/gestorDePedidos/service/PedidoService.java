package br.com.gestorDePedidos.service;

import br.com.gestorDePedidos.dto.PedidoDto;
import br.com.gestorDePedidos.entity.Pedido;
import java.util.List;

public interface PedidoService {

    public void criarPedido(PedidoDto dto);

    public Pedido buscarPedidoPorId(int codigoPedido);

    public List<Pedido> buscarPedidosPorCliente(int codigoCliente);
}
