package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.repository.UsuarioRepository;
import com.gerenciador.tarefas.model.Usuario;
import com.gerenciador.tarefas.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    public UsuarioRepository repository;

    @Autowired
    public UsuarioService service;



    @ApiOperation(value = "Retorna dados do usuario pelo email")
    @GetMapping("/usuario")
    public Optional<Usuario> buscaUsuario(@RequestParam String email) {
        Optional<Usuario> usuario = repository.findByEmail(email);
        return usuario;
    }

    @ApiOperation(value = "Salva usuario valido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario salvo com sucesso"),
            @ApiResponse(code = 406, message = "Email com formato incorreto"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(value = "usuario/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario user) {
        boolean isEmailValid = service.isEmailValid(user.getEmail());
        boolean isUserValid = service.isUserValid(user);
        if (isUserValid) {
            repository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
