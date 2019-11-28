package br.com.viasoft.projetoapi.service;

import br.com.viasoft.projetoapi.model.Historico;
import br.com.viasoft.projetoapi.model.Status;
import br.com.viasoft.projetoapi.repository.HistoricoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class HistoricoServiceImpl implements HistoricoService {

    private static final Logger logger = Logger.getLogger(HistoricoServiceImpl.class.getName());

    @Autowired private HistoricoRepository repository;

    @Override
    @Cacheable("disponibilidade")
    public List<Historico> getDisponibilidade() throws IOException {
        // Pega a hora atual para gravar os junto aos registros, caso necessário.
        LocalDateTime hora = LocalDateTime.now();

        // Recupera o HTML da página
        Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();

        // Seleciona a (primeira) tabela
        Element tabela = doc.select("table.tabelaListagemDados").get(0);

        // Seleciona as linhas da tabela
        Elements linhas = tabela.select("tr");

        List<Historico> listaHistorico = new ArrayList<>();

        for (Element linha : linhas) {
            Elements colunas = linha.select("td");
            if (!colunas.isEmpty()) {
                Historico historico = new Historico()
                        .setAutorizador(colunas.get(0).text())
                        .setAutorizacao(getStatus(1, colunas))
                        .setRetornoAutorizacao(getStatus(2, colunas))
                        .setInutilizacao(getStatus(3, colunas))
                        .setConsultaProtocolo(getStatus(4, colunas))
                        .setStatusServico(getStatus(5, colunas))
                        .setConsultaCadastro(getStatus(7, colunas))
                        .setRecepcaoEvento(getStatus(8, colunas))
                        .setHora(hora);

                listaHistorico.add(historico);
            }
        }

        return listaHistorico;
    }

    @Override
    public List<Historico> findByHoraBetween(LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByHoraBetween(inicio, fim);
    }

    @Override
    public String findMostOffline() {
        return repository.findMostOffline();
    }

    @Override
    public Historico getDisponibilidadePorEstado(String autorizador) throws IOException {
        return getDisponibilidade().stream()
                .filter(historico -> historico.getAutorizador().equals(autorizador))
                .findFirst()
                .orElse(null);
    }

    // Scheduler executará o comando a cada 5 minutos, apenas em minutos múltiplos de 5
    @Scheduled(cron = "0 */5 * ? * *")
    @CacheEvict(allEntries = true, cacheNames = { "disponibilidade" })
    public void gravarHistorico() {
        try {
            repository.saveAll(getDisponibilidade());
            logger.info("Registros obtidos (Execução: " + LocalTime.now().toString() + ").");
        } catch (IOException e) {
            // Caso não consiga obter os dados, retorna a falha no console.
            logger.info("Falha ao obter os registros.");
            logger.warning(e.getMessage());
        }
    }

    private Status getStatus(int index, Elements colunas) {
        return Status.fromIcone( colunas.get(index).select("img").attr("src") );
    }
}
