package br.com.gestorDePedidos.model;

import lombok.Data;

@Data
public class Item {
    private String produto;
    private int quantidade;
    private float preco;
}
