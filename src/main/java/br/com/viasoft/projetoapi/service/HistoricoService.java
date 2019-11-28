package br.com.viasoft.projetoapi.service;

import br.com.viasoft.projetoapi.model.Historico;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface HistoricoService {

    List<Historico> getDisponibilidade() throws IOException;

    List<Historico> findByHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    Historico getDisponibilidadePorEstado(String autorizador) throws IOException;

    String findMostOffline();

}
