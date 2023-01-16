package br.com.gestorDePedidos.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cliente")
@Getter
@Setter
@Builder
public class Cliente {

    @Id
    private Integer codigoCliente;

    private Integer totalPedidosCliente;
}
