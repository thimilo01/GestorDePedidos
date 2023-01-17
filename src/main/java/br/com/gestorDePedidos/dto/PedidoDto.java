package br.com.gestorDePedidos.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoDto {
    private int codigoPedido;
    private int codigoCliente;
    private List<ItemDto> itens;
}
