package br.com.viasoft.projetoapi.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 6, nullable = false)
    private String autorizador;

    @Enumerated
    @Column(nullable = false)
    private Status autorizacao;

    @Enumerated
    @Column(name = "retorno_autorizacao", nullable = false)
    private Status retornoAutorizacao;

    @Enumerated
    @Column(nullable = false)
    private Status inutilizacao;

    @Enumerated
    @Column(name = "consulta_protocolo", nullable = false)
    private Status consultaProtocolo;

    @Enumerated
    @Column(name = "status_servico", nullable = false)
    private Status statusServico;

    @Enumerated
    @Column(name = "consulta_cadastro", nullable = false)
    private Status consultaCadastro;

    @Enumerated
    @Column(name = "recepcao_evento", nullable = false)
    private Status recepcaoEvento;

    @Column(nullable = false)
    private LocalDateTime hora;

    public long getId() {
        return id;
    }

    public Historico setId(long id) {
        this.id = id;
        return this;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public Historico setAutorizador(String autorizador) {
        this.autorizador = autorizador;
        return this;
    }

    public Status getAutorizacao() {
        return autorizacao;
    }

    public Historico setAutorizacao(Status autorizacao) {
        this.autorizacao = autorizacao;
        return this;
    }

    public Status getRetornoAutorizacao() {
        return retornoAutorizacao;
    }

    public Historico setRetornoAutorizacao(Status retornoAutorizacao) {
        this.retornoAutorizacao = retornoAutorizacao;
        return this;
    }

    public Status getInutilizacao() {
        return inutilizacao;
    }

    public Historico setInutilizacao(Status inutilizacao) {
        this.inutilizacao = inutilizacao;
        return this;
    }

    public Status getConsultaProtocolo() {
        return consultaProtocolo;
    }

    public Historico setConsultaProtocolo(Status consultaProtocolo) {
        this.consultaProtocolo = consultaProtocolo;
        return this;
    }

    public Status getStatusServico() {
        return statusServico;
    }

    public Historico setStatusServico(Status statusServico) {
        this.statusServico = statusServico;
        return this;
    }

    public Status getConsultaCadastro() {
        return consultaCadastro;
    }

    public Historico setConsultaCadastro(Status consultaCadastro) {
        this.consultaCadastro = consultaCadastro;
        return this;
    }

    public Status getRecepcaoEvento() {
        return recepcaoEvento;
    }

    public Historico setRecepcaoEvento(Status recepcaoEvento) {
        this.recepcaoEvento = recepcaoEvento;
        return this;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public Historico setHora(LocalDateTime hora) {
        this.hora = hora;
        return this;
    }

    @Override
    public String toString() {

        return "ID: " + this.id +
                "\nAutorizador: " + this.autorizador +
                "\nAutorização: " + this.autorizacao +
                "\nRetornoAutorizacao: " + this.retornoAutorizacao +
                "\nInutilizacao: " + this.inutilizacao +
                "\nConsultaProtocolo: " + this.consultaProtocolo +
                "\nStatusServico: " + this.statusServico +
                "\nConsultaCadastro: " + this.consultaCadastro +
                "\nRecepcaoEvento: " + this.recepcaoEvento +
                "\nHora: " + this.hora;
    }
}
