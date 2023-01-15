package br.com.gestorDePedidos.controller;

import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/{codigoCliente}")
    public ResponseEntity consultarPedidos(@PathVariable int codigoCliente) {
        try {
            return ResponseEntity.ok(service.listaPedidos(codigoCliente));
        } catch (NaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }


}
