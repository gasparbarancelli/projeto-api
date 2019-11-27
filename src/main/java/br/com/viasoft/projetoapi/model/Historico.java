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

    public Historico(String autorizador, Status autorizacao, Status retornoAutorizacao, Status inutilizacao, Status consultaProtocolo, Status statusServico, Status consultaCadastro, Status recepcaoEvento) {
        this.autorizador = autorizador;
        this.autorizacao = autorizacao;
        this.retornoAutorizacao = retornoAutorizacao;
        this.inutilizacao = inutilizacao;
        this.consultaProtocolo = consultaProtocolo;
        this.statusServico = statusServico;
        this.consultaCadastro = consultaCadastro;
        this.recepcaoEvento = recepcaoEvento;
    }

    public Historico() {
    }

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

    public Status getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(Status autorizacao) {
        this.autorizacao = autorizacao;
    }

    public Status getRetornoAutorizacao() {
        return retornoAutorizacao;
    }

    public void setRetornoAutorizacao(Status retorno_autorizacao) {
        this.retornoAutorizacao = retorno_autorizacao;
    }

    public Status getInutilizacao() {
        return inutilizacao;
    }

    public void setInutilizacao(Status inutilizacao) {
        this.inutilizacao = inutilizacao;
    }

    public Status getConsultaProtocolo() {
        return consultaProtocolo;
    }

    public void setConsultaProtocolo(Status consulta_protocolo) {
        this.consultaProtocolo = consulta_protocolo;
    }

    public Status getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(Status status_servico) {
        this.statusServico = status_servico;
    }

    public Status getConsultaCadastro() {
        return consultaCadastro;
    }

    public void setConsultaCadastro(Status consulta_cadastro) {
        this.consultaCadastro = consulta_cadastro;
    }

    public Status getRecepcaoEvento() {
        return recepcaoEvento;
    }

    public void setRecepcaoEvento(Status recepcao_evento) {
        this.recepcaoEvento = recepcao_evento;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {

        return "ID: " + this.id +
                "\nAutorizador: " + this.autorizador +
                "\nAutorização: " + this.autorizacao +
                "\nRetorno_autorizacao: " + this.retornoAutorizacao +
                "\nInutilizacao: " + this.inutilizacao +
                "\nConsulta_protocolo: " + this.consultaProtocolo +
                "\nStatus_servico: " + this.statusServico +
                "\nConsulta_cadastro: " + this.consultaCadastro +
                "\nRecepcao_evento: " + this.recepcaoEvento +
                "\nHora: " + this.hora;
    }
}
