package br.com.viasoft.projetoapi.repository;

import br.com.viasoft.projetoapi.model.Historico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    List<Historico> findByAutorizadorEquals(String autorizador);
    List<Historico> findByHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}
