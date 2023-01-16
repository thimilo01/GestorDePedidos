package br.com.gestorDePedidos.service;


import br.com.gestorDePedidos.entity.Cliente;

public interface ClienteService {

    public Cliente buscarClientePorId(int codigoCliente);

    public void criarCliente(int codigoCliente);

}
