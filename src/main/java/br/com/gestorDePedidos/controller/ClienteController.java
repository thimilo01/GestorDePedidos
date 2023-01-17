package br.com.gestorDePedidos.controller;

import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @GetMapping("/{codigoCliente}")
    @Operation(summary = "Consulta de total de pedidos por cliente")
    public ResponseEntity get(@PathVariable int codigoCliente) {
        try {
            return ResponseEntity.ok(service.buscarClientePorId(codigoCliente));
        } catch (NaoEncontradoException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }

    @GetMapping
    @Operation(summary = "Todos clientes e total de pedidos")
    public ResponseEntity getAll() {
            return ResponseEntity.ok(service.buscarTodos());
    }


}
