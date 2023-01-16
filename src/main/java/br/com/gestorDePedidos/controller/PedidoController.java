package br.com.gestorDePedidos.controller;

import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping(value = "/{codigoPedido}")
    public ResponseEntity get(@PathVariable int codigoPedido) {
        try {
            return ResponseEntity.ok(service.buscarPedidoPorId(codigoPedido));

        } catch (NaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/porcliente/{codigoCliente}")
    public ResponseEntity getPedidosPorCliente(@PathVariable int codigoCliente) {
        try {
            return ResponseEntity.ok(service.buscarPedidosPorCliente(codigoCliente));

        } catch (NaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
