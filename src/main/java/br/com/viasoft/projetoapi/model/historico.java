package br.com.viasoft.projetoapi.model;

import javax.persistence.*;

@Entity
public class historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 6, nullable = false)
    private String autorizador;

    @Column(nullable = false)
    private int autorizacao;

    @Column(nullable = false)
    private int retorno_autorizacao;

    @Column(nullable = false)
    private int inutilizacao;

    @Column(nullable = false)
    private int consulta_protocolo;

    @Column(nullable = false)
    private int status_servico;
}
