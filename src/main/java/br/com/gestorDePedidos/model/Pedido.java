package br.com.gestorDePedidos.model;

import lombok.Data;

import java.util.List;

@Data
public class Pedido {
    private int codigoPedido;
    private int codigoCliente;
    private List<Item> itens;
}
