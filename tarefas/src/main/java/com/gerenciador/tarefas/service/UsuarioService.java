package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.model.Usuario;
import com.gerenciador.tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Optional;

@Service
public class UsuarioService {


    @Autowired
    public UsuarioRepository repository;

    public boolean isEmailValid(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    public  boolean isUserValid(Usuario user){
        Optional<Usuario> userExists  = repository.findByEmail(user.getEmail());
        if (userExists == null){
            return true;
        }else{
            return  false;
        }
    }
}
