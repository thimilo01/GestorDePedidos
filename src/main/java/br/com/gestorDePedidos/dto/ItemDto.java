package br.com.gestorDePedidos.dto;

import lombok.Data;

@Data
public class ItemDto {
    private String produto;
    private int quantidade;
    private float preco;
}
