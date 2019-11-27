package br.com.viasoft.projetoapi.repository;

import br.com.viasoft.projetoapi.model.Historico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    List<Historico> findByAutorizadorEquals(String autorizador);
}
