package br.com.gestorDePedidos.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Item {
    private String produto;
    private int quantidade;
    private float preco;
}
