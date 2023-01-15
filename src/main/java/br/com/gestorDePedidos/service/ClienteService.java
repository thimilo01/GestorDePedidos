package br.com.gestorDePedidos.service;

import br.com.gestorDePedidos.entity.Pedido;
import java.util.List;

public interface ClienteService {
    public List<Pedido> listaPedidos(int codigoCliente);

}
