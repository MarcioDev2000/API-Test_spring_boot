package com.monografia.buka.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.monografia.buka.dto.UsuarioDto;
import com.monografia.buka.repositories.UsuarioRepository;

@SpringBootTest
@ActiveProfiles("test") 
public class UsuarioServicePerformanceTest {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Test
    @Transactional
    public void testarDesempenhoDeInsercaoDeUsuarios() {
        int quantidadeDeUsuarios = 50;
        long tempoLimite = 9000; 
    
        long tempoInicial = System.currentTimeMillis();
    
        for (int i = 0; i < quantidadeDeUsuarios; i++) {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setNome("Usuario " + i);
            usuarioDto.setEmail("usuario" + i + "@exemplo.com");
            usuarioDto.setSenha("senha123");
    
            usuarioServices.registrar(usuarioDto);
        }
    
        long tempoFinal = System.currentTimeMillis();
        long duracaoTotal = tempoFinal - tempoInicial;
    
        System.out.println("Tempo total para registrar " + quantidadeDeUsuarios + " usuários: " + duracaoTotal + "ms");
    
        // Teste de falha: O tempo total deve ser menor que 5000ms, mas o limite é muito baixo
        assertTrue(duracaoTotal <= tempoLimite,
                String.format("O tempo de execução para registrar os %d usuários foi muito alto! Tempo total: %dms", quantidadeDeUsuarios, duracaoTotal));
    
        double tempoMedioPorUsuario = (double) duracaoTotal / quantidadeDeUsuarios;
        System.out.println("Tempo médio por usuário: " + tempoMedioPorUsuario + "ms");
    
        // Teste de falha: O tempo médio por usuário deve ser menor que 100ms
        assertTrue(tempoMedioPorUsuario < 100,
                String.format("O tempo médio por usuário foi muito alto! Tempo médio: %.2fms", tempoMedioPorUsuario));
    
        long usuariosSalvosNoBanco = usuarioRepository.count();
        assertTrue(usuariosSalvosNoBanco == quantidadeDeUsuarios,
                "O número de usuários registrados no banco de dados não corresponde ao esperado!");
    }
    
}
