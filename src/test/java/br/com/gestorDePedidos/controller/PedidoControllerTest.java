package br.com.gestorDePedidos.controller;


import br.com.gestorDePedidos.entity.Pedido;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.service.PedidoService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers =  PedidoController.class)
public class PedidoControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PedidoService service;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new PedidoController(service))
                .build();
    }

    @Test
    void getSucesso() throws Exception {
        when(service.buscarPedidoPorId(1)).thenReturn(Pedido.builder().build());
        mockMvc.perform(get("/pedido/1")
                )
                .andExpect(status().isOk());
    }

    @Test
    void getErrorNaoEncontrado() throws Exception {
        when(service.buscarPedidoPorId(2)).thenThrow(new NaoEncontradoException(""));
        mockMvc.perform(get("/pedido/2")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getErrorInterno() throws Exception {
        when(service.buscarPedidoPorId(3)).thenThrow(new RuntimeException(""));
        mockMvc.perform(get("/pedido/3")
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getPorClienteSucesso() throws Exception {
        when(service.buscarPedidosPorCliente(1)).thenReturn(List.of());
        mockMvc.perform(get("/pedido/porcliente/1")
                )
                .andExpect(status().isOk());
    }

    @Test
    void getPorClienteErrorNaoEncontrado() throws Exception {
        when(service.buscarPedidosPorCliente(2)).thenThrow(new NaoEncontradoException(""));
        mockMvc.perform(get("/pedido/porcliente/2")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getPorClienteErrorInterno() throws Exception {
        when(service.buscarPedidosPorCliente(3)).thenThrow(new RuntimeException(""));
        mockMvc.perform(get("/pedido/porcliente/3")
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getTotalpedidoSucesso() throws Exception {
        when(service.buscarPedidoPorId(1)).thenReturn(Pedido.builder().build());
        mockMvc.perform(get("/pedido/total/1")
                )
                .andExpect(status().isOk());
    }

    @Test
    void getTotalpedidoErrorNaoEncontrado() throws Exception {
        when(service.buscarPedidoPorId(2)).thenThrow(new NaoEncontradoException(""));
        mockMvc.perform(get("/pedido/total/2")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getTotalpedidoErrorInterno() throws Exception {
        when(service.buscarPedidoPorId(3)).thenThrow(new RuntimeException(""));
        mockMvc.perform(get("/pedido/total/3")
                )
                .andExpect(status().isInternalServerError());
    }

}
