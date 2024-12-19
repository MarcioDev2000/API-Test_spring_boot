package com.monografia.buka.controllers;

import com.monografia.buka.dto.UsuarioDto;
import com.monografia.buka.models.Usuario;
import com.monografia.buka.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/usuarios") 
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioRegistrado = usuarioServices.registrar(usuarioDto);
        return new ResponseEntity<>(usuarioRegistrado, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
    Usuario usuarioAtualizado = usuarioServices.atualizar(id, usuarioDto);
    return ResponseEntity.ok(usuarioAtualizado);
    }

}
