package br.com.gestorDePedidos.service.impl;

import br.com.gestorDePedidos.entity.Pedido;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.repository.PedidoRepository;
import br.com.gestorDePedidos.service.ClienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private PedidoRepository repository;

    @Override
    public List<Pedido> listaPedidos(int codigoCliente) {
        Optional<List<Pedido>> pedidos = repository.findBycodigoCliente(codigoCliente);
        if(pedidos.isPresent()){
            return pedidos.get();
        }
        throw new NaoEncontradoException("Não encontramos nenhum pedido para esse cliente!");
    }
}
