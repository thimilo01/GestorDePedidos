package br.com.gestorDePedidos.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Item {
    private String produto;
    private int quantidade;
    private float preco;
}
