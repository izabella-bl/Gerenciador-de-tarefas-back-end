package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.controller.UsuarioController;
import com.gerenciador.tarefas.model.Usuario;
import com.gerenciador.tarefas.repository.UsuarioRepository;
import com.gerenciador.tarefas.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.repository = usuarioRepository;
        usuarioController.service = usuarioService;
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void testBuscaUsuario() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("test@example.com");

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));


        mockMvc.perform(get("/usuario")
                        .param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(usuarioRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testSaveUsuario() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("test@example.com");

        when(usuarioService.isEmailValid("test@example.com")).thenReturn(true);
        when(usuarioService.isUserValid(any(Usuario.class))).thenReturn(true);


        mockMvc.perform(post("/usuario/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isOk());

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testSaveUsuarioInvalidEmail() throws Exception {
        // Arrange
        when(usuarioService.isEmailValid("invalid-email")).thenReturn(false);


        mockMvc.perform(post("/usuario/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"invalid-email\"}"))
                .andExpect(status().isBadRequest());

        verify(usuarioRepository, times(0)).save(any(Usuario.class));
    }

    @Test
    public void testSaveUsuarioAlreadyExists() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");

        when(usuarioService.isEmailValid("test@example.com")).thenReturn(true);
        when(usuarioService.isUserValid(any(Usuario.class))).thenReturn(false);


        mockMvc.perform(post("/usuario/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\"}"))
                .andExpect(status().isBadRequest());

        verify(usuarioRepository, times(0)).save(any(Usuario.class));
    }
}
