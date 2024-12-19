package com.monografia.buka.services;

import com.monografia.buka.dto.UsuarioDto;
import com.monografia.buka.models.Usuario;
import com.monografia.buka.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsuarioServiceTDD {

    @InjectMocks
    private UsuarioServices usuarioServices; 

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

     @Test
    public void registrarUsuario_DeveSalvarUsuarioComSucesso() {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("Teste Nome");
        usuarioDto.setEmail("teste@exemplo.com");
        usuarioDto.setSenha("senha123");

        Usuario usuarioMock = new Usuario("Teste Nome", "teste@exemplo.com", "senha123");

       
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario usuarioRegistrado = usuarioServices.registrar(usuarioDto);

        assertNotNull(usuarioRegistrado);
        assertEquals("Teste Nome", usuarioRegistrado.getNome());
        assertEquals("teste@exemplo.com", usuarioRegistrado.getEmail());
        assertEquals("senha123", usuarioRegistrado.getSenha());

        
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }


}
