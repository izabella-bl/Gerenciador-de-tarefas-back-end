package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.model.Usuario;
import com.gerenciador.tarefas.repository.UsuarioRepository;
import com.gerenciador.tarefas.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private UsuarioService service;

    @InjectMocks
    private UsuarioController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuscaUsuario() {

        String email = "test@example.com";
        Usuario usuario = new Usuario();
        when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = controller.buscaUsuario(email);

        assertEquals(usuario, result.get());
        verify(repository).findByEmail(email);
    }

    @Test
    public void testSaveUsuario_ComUsuarioValido() {

        Usuario user = new Usuario();
        when(service.isEmailValid(user.getEmail())).thenReturn(true);
        when(service.isUserValid(user)).thenReturn(true);

        ResponseEntity<?> responseEntity = controller.saveUsuario(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(repository).save(user);
    }

    @Test
    public void testSaveUsuario_ComEmailInvalido() {

        Usuario user = new Usuario();
        when(service.isEmailValid(user.getEmail())).thenReturn(false);


        ResponseEntity<?> responseEntity = controller.saveUsuario(user);


        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
        verify(repository, never()).save(user);
    }

    @Test
    public void testSaveUsuario_ComUsuarioInvalido() {

        Usuario user = new Usuario();
        when(service.isEmailValid(user.getEmail())).thenReturn(true);
        when(service.isUserValid(user)).thenReturn(false);


        ResponseEntity<?> responseEntity = controller.saveUsuario(user);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        verify(repository, never()).save(user);
    }
}
