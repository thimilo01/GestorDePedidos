package br.com.gestorDePedidos.controller;

import br.com.gestorDePedidos.entity.Pedido;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
@Slf4j
public class PedidoController {

    private final PedidoService service;

    @GetMapping(value = "/{codigoPedido}")
    @Operation(summary = "Consulta de pedido detalhado por ID")
    public ResponseEntity get(@PathVariable int codigoPedido) {
        try {
            return ResponseEntity.ok(service.buscarPedidoPorId(codigoPedido));

        } catch (NaoEncontradoException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/total/{codigoPedido}")
    @Operation(summary = "Valor total do pedido")
    public ResponseEntity getTotalpedido(@PathVariable int codigoPedido) {
        try {
            Pedido pedido = service.buscarPedidoPorId(codigoPedido);
            return ResponseEntity.ok(pedido.getValorTotal());

        } catch (NaoEncontradoException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/porcliente/{codigoCliente}")
    @Operation(summary = "Pedidos realizados pelo cliente")
    public ResponseEntity getPedidosPorCliente(@PathVariable int codigoCliente) {
        try {
            return ResponseEntity.ok(service.buscarPedidosPorCliente(codigoCliente));

        } catch (NaoEncontradoException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
