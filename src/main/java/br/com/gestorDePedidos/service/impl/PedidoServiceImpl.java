package br.com.gestorDePedidos.service.impl;

import br.com.gestorDePedidos.configuration.mapper.ObjectMapperUtil;
import br.com.gestorDePedidos.dto.ItemDto;
import br.com.gestorDePedidos.dto.PedidoDto;
import br.com.gestorDePedidos.entity.Cliente;
import br.com.gestorDePedidos.entity.Item;
import br.com.gestorDePedidos.entity.Pedido;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.repository.PedidoRepository;
import br.com.gestorDePedidos.service.ClienteService;
import br.com.gestorDePedidos.service.PedidoService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    private final ClienteService clienteService;

    private float valorTotal;

    @Override
    public void criarPedido(PedidoDto dto) {
        repository.save(Pedido.builder()
                .codigoPedido(dto.getCodigoPedido())
                .cliente(clienteService.buscarClientePorId(dto.getCodigoCliente()))
                .items(ConvertListSomaTotalPedido(dto.getItens()))
                .valorTotal(valorTotal)
                .dataPedido(LocalDateTime.now())
                .build());
    }

    @Override
    public Pedido buscarPedidoPorId(int codigoPedido) {
        Optional<Pedido> pedido = repository.findById(codigoPedido);
        if (pedido.isPresent()) {
            return pedido.get();
        }
        throw new NaoEncontradoException("Não encontramos nenhum pedido com esse código!");
    }

    @Override
    public List<Pedido> buscarPedidosPorCliente(int codigoCliente) {
        Cliente cliente = clienteService.buscarClientePorId(codigoCliente);
        return repository.findByCliente(cliente);
    }

    private List<Item> ConvertListSomaTotalPedido(List<ItemDto> itensDto) {
        log.info("--- Validação para inserção ---");
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
