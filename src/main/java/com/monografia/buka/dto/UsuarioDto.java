package com.monografia.buka.dto;
import jakarta.validation.constraints.*;

public class UsuarioDto {

    @NotBlank(message = "Erro: O nome do usuário é obrigatório.")
    private String nome;

    @NotBlank(message = "Erro: O e-mail do usuário é obrigatório.")
    @Email(message = "Erro: E-mail inválido.")
    private String email;

    @NotBlank(message = "Erro: A senha do usuário é obrigatória.")
    @Size(min = 6, message = "Erro: A senha deve ter no mínimo 6 caracteres.")
    private String senha;

    public UsuarioDto(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}