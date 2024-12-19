package com.monografia.buka.services;
import com.monografia.buka.dto.UsuarioDto;
import com.monografia.buka.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioServiceE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setup() {
        baseUrl = "http://localhost:" + port + "/usuarios";  
    }

    @Test
    public void testRegistrarUsuario() {
       
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("João Silva");
        usuarioDto.setEmail("joao.silva@example.com");
        usuarioDto.setSenha("senha123");
 
        ResponseEntity<Usuario> response = restTemplate.postForEntity(baseUrl + "/registrar", usuarioDto, Usuario.class);
        
        assertEquals(201, response.getStatusCode().value()); 
        assertEquals("João Silva", response.getBody().getNome());
        assertEquals("joao.silva@example.com", response.getBody().getEmail());
        assertEquals("senha123", response.getBody().getSenha());
    }

    @Test
    public void testAtualizarUsuario() {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("João Silva Atualizado");
        usuarioDto.setEmail("joao.silva.atualizado@example.com");
        usuarioDto.setSenha("senha123");

        ResponseEntity<Usuario> response = restTemplate.exchange(baseUrl + "/atualizar/1", 
                HttpMethod.PUT, 
                new HttpEntity<>(usuarioDto), 
                Usuario.class);

        assertEquals(200,response.getStatusCode().value());
        assertEquals("João Silva Atualizado", response.getBody().getNome());
        assertEquals("joao.silva.atualizado@example.com", response.getBody().getEmail());
         assertEquals("senha123", response.getBody().getSenha());
    }

    @Test
    public void testDeletarUsuario() {
      
        restTemplate.delete(baseUrl + "/deletar/1");

        ResponseEntity<Usuario> response = restTemplate.getForEntity(baseUrl + "/buscar/1", Usuario.class);
        assertEquals(404, response.getStatusCode().value());
    }
}
