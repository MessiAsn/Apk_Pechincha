package com.example.pechincha.model;

public class Oferta {
    private Long id;
    private String titulo;
    private Double preco;
    private String cupom;

    // Construtores
    public Oferta() {
    }

    public Oferta(Long id, String titulo, Double preco, String cupom) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
        this.cupom = cupom;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }
}
