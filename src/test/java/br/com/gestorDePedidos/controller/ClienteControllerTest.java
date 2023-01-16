package br.com.gestorDePedidos.controller;


import br.com.gestorDePedidos.entity.Cliente;
import br.com.gestorDePedidos.exception.NaoEncontradoException;
import br.com.gestorDePedidos.service.ClienteService;
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
@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new ClienteController(service))
                .build();
    }

    @Test
    void getSucesso() throws Exception {
        when(service.buscarClientePorId(1)).thenReturn(Cliente.builder().build());
        mockMvc.perform(get("/cliente/1")
                )
                .andExpect(status().isOk());
    }

    @Test
    void getErrorNaoEncontrado() throws Exception {
        when(service.buscarClientePorId(3)).thenThrow(new NaoEncontradoException(""));
        mockMvc.perform(get("/cliente/3")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getErrorInterno() throws Exception {
        when(service.buscarClientePorId(2)).thenThrow(new RuntimeException(""));
        mockMvc.perform(get("/cliente/2")
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getTodosSucesso() throws Exception {
        when(service.buscarTodos()).thenReturn(List.of());
        mockMvc.perform(get("/cliente")
                )
                .andExpect(status().isOk());
    }
}
