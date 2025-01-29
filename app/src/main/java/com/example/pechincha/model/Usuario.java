package com.example.pechincha.model;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String imageUri; // Novo campo para a URI da imagem

    // Construtor padrão
    public Usuario() {}

    // Construtor completo
    public Usuario(Long id, String nome, String email, String senha, String imageUri) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.imageUri = imageUri;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}