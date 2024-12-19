package com.monografia.buka.services;

import com.monografia.buka.BukaApplication;
import com.monografia.buka.dto.UsuarioDto;
import com.monografia.buka.models.Usuario;
import com.monografia.buka.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
@SpringBootTest(classes = BukaApplication.class)
@ActiveProfiles("test") 
public class UsuarioServiceTestIntegracao {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void RegistrarUsuarios() {
        
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("Teste Nome");
        usuarioDto.setEmail("teste@exemplo.com");
        usuarioDto.setSenha("senha123");

       
        Usuario usuarioRegistrado = usuarioServices.registrar(usuarioDto);

        assertNotNull(usuarioRegistrado);
        assertEquals("Teste Nome", usuarioRegistrado.getNome());
        assertEquals("teste@exemplo.com", usuarioRegistrado.getEmail());
        assertEquals("senha123", usuarioRegistrado.getSenha());

        Usuario usuarioNoBanco = usuarioRepository.findByEmail("teste@exemplo.com");
        assertNotNull(usuarioNoBanco);
        assertEquals("Teste Nome", usuarioNoBanco.getNome());
    }


    @Test
    public void DeletarUsuario() {
       
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("Teste Nome");
        usuarioDto.setEmail("teste@exemplo.com");
        usuarioDto.setSenha("senha123");
        Usuario usuarioRegistrado = usuarioServices.registrar(usuarioDto);

       
        usuarioServices.deletar(usuarioRegistrado.getId());

       
        Usuario usuarioNoBanco = usuarioRepository.findById(usuarioRegistrado.getId()).orElse(null);
        assertNull(usuarioNoBanco);
    }

    @Test
    public void BuscarUsuarioPorId() {
       
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("Teste Nome");
        usuarioDto.setEmail("teste@exemplo.com");
        usuarioDto.setSenha("senha123");
        Usuario usuarioRegistrado = usuarioServices.registrar(usuarioDto);

   
        Usuario usuarioEncontrado = usuarioRepository.findById(usuarioRegistrado.getId()).orElse(null);

       
        assertNotNull(usuarioEncontrado);
        assertEquals("Teste Nome", usuarioEncontrado.getNome());
    }

    @Test
    public void atualizarUsuario_DeveAlterarDadosComSucesso() {
        
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("Nome Original");
        usuarioDto.setEmail("original@exemplo.com");
        usuarioDto.setSenha("senha123");
        Usuario usuarioRegistrado = usuarioServices.registrar(usuarioDto);

        UsuarioDto usuarioAtualizado = new UsuarioDto();
        usuarioAtualizado.setNome("Nome Atualizado");
        usuarioAtualizado.setEmail("atualizado@exemplo.com");
        usuarioAtualizado.setSenha("novaSenha123");

       
        Usuario usuarioAtualizadoResultado = usuarioServices.atualizar(usuarioRegistrado.getId(), usuarioAtualizado);

        
        assertNotNull(usuarioAtualizadoResultado);
        assertEquals("Nome Atualizado", usuarioAtualizadoResultado.getNome());
        assertEquals("atualizado@exemplo.com", usuarioAtualizadoResultado.getEmail());
    }
}
