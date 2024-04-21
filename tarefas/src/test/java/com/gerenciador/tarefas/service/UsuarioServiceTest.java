package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.model.Usuario;
import com.gerenciador.tarefas.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    public void testIsEmailValid_ReturnsTrue() {
        String validEmail = "test@example.com";

        boolean isValid = service.isEmailValid(validEmail);

        assertTrue(isValid);
    }

    @Test
    public void testIsEmailValid_ReturnsFalse() {
        // Arrange
        String invalidEmail = "invalid_email";

        boolean isValid = service.isEmailValid(invalidEmail);

        assertFalse(isValid);
    }

    @Test
    public void testIsUserValid_ReturnsFalse() {

        Usuario usuario = new Usuario();
        usuario.setEmail("teste2@example.com");
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(usuario));

        boolean isValid = service.isUserValid(usuario);

        assertFalse(isValid);
    }
}

