package br.com.gestorDePedidos.service;


import br.com.gestorDePedidos.entity.Cliente;
import java.util.List;

public interface ClienteService {

    public Cliente buscarClientePorId(int codigoCliente);

    public void criarCliente(int codigoCliente);

    public List<Cliente> buscarTodos();

}
