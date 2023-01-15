package br.com.gestorDePedidos.service.impl;

import br.com.gestorDePedidos.configuration.mapper.ObjectMapperUtil;
import br.com.gestorDePedidos.dto.ItemDto;
import br.com.gestorDePedidos.entity.Item;
import br.com.gestorDePedidos.entity.Pedido;
import br.com.gestorDePedidos.dto.PedidoDto;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.repository.PedidoRepository;
import br.com.gestorDePedidos.service.PedidoService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository repository;

    private float valorTotal;

    @Override
    public void criarPedido(PedidoDto model) {
        repository.save(Pedido.builder()
                .codigoPedido(model.getCodigoPedido())
                .codigoCliente(model.getCodigoCliente())
                .items(ConvertListSomaTotalPedido(model.getItens()))
                .valorTotal(valorTotal)
                .dataPedido(LocalDateTime.now())
                .build());
    }

    @Override
    public Pedido buscarPedido(int codigoPedido) {
        Optional<Pedido> pedido = repository.findById(codigoPedido);
        if(pedido.isPresent()){
            return pedido.get();
        };
         throw new NaoEncontradoException("Não encontramos nenhum pedido com esse código!");
    }

    private List<Item> ConvertListSomaTotalPedido(List<ItemDto> itensDto) {
        valorTotal = 0;
        List<Item> itens = new ArrayList<>();
        itensDto.forEach(item -> {
            itens.add(ObjectMapperUtil.convertTo(item, Item.class));
            float totalValorItem = item.getPreco() * item.getQuantidade();
            valorTotal += totalValorItem;
        });
        return itens;
    }
}
