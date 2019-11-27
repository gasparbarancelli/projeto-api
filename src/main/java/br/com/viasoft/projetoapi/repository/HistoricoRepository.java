package br.com.viasoft.projetoapi.repository;

import br.com.viasoft.projetoapi.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    List<Historico> findByAutorizadorEquals(String autorizador);
    List<Historico> findByHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    /*
    Consulta abaixo formatada:
    ===============
    select
        y.autorizador
    from
        (select
            max(x.offline) as conta,
            x.autorizador
        from
            (select
                autorizador,
                sum(case when autorizacao = 1 then 1 end) as online,
                sum(case when autorizacao in (0, 2) then 1 end) as offline
            from
                historico
            group by
                historico.autorizador) as x
        group by x.autorizador) as y
    where
        conta is not null
    ===============
     */
    @Query(value = "select y.autorizador from (select max(x.offline) as conta, x.autorizador from (select autorizador, sum(case when autorizacao = 1 then 1 end) as online, sum(case when autorizacao in (0, 2) then 1 end) as offline from historico group by historico.autorizador) as x group by x.autorizador) as y where conta is not null",
            nativeQuery = true)
    String findMostOffline();
}
