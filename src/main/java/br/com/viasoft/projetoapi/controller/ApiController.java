package br.com.viasoft.projetoapi.controller;

import br.com.viasoft.projetoapi.model.Historico;
import br.com.viasoft.projetoapi.model.Status;
import br.com.viasoft.projetoapi.repository.HistoricoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableScheduling
public class ApiController {

    @Autowired
    HistoricoRepository repository;

    @RequestMapping(value = "/obter", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Historico> getDisponibilidade() throws IOException {
        // Pega a hora atual para gravar os junto aos registros, caso necessário.
        LocalDateTime hora = LocalDateTime.now();

        // Recupera o HTML da página
        Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();

        // Seleciona a (primeira) tabela
        Element tabela = doc.select("table.tabelaListagemDados").get(0);

        // Seleciona as linhas da tabela
        Elements linhas = tabela.select("tr");

        List<Historico> listaHistorico = new ArrayList<Historico>();

        for (int i = 1; i < linhas.size(); i++) {
            Element linha = linhas.get(i);
            Elements colunas = linha.select("td");

            Historico historico = new Historico();
            historico.setAutorizador(colunas.get(0).text());
            historico.setAutorizacao( getStatus(1, colunas) );
            historico.setRetornoAutorizacao( getStatus(2, colunas) );
            historico.setInutilizacao( getStatus(3, colunas) );
            historico.setConsultaProtocolo( getStatus(4, colunas) );
            historico.setStatusServico( getStatus(5, colunas) );
            historico.setConsultaCadastro( getStatus(7, colunas) );
            historico.setRecepcaoEvento( getStatus(8, colunas) );
            historico.setHora(hora);

            listaHistorico.add(historico);
        }

        return listaHistorico;
    }

    @RequestMapping(value = "/estado", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Historico> getDisponibilidadePorEstado(@RequestParam("autorizador") String autorizador) throws IOException {

        List<Historico> listaHistorico = new ArrayList<Historico>();
        listaHistorico = repository.findByAutorizadorEquals(autorizador);

        return listaHistorico;
    }

    // Scheduler executará o comando a cada 5 minutos, apenas em minutos múltiplos de 5
    @Scheduled(cron = "0 */5 * ? * *")
    public void gravarHistorico() {
        List<Historico> listaHistorico = new ArrayList<Historico>();
        try {
            listaHistorico = getDisponibilidade();
            repository.saveAll(listaHistorico);
            System.out.println("Registros obtidos (Execução: " + LocalTime.now().toString() + ").");
        } catch (IOException e) {
            // Caso não consiga obter os dados, retorna a falha no console.
            System.out.println("Falha ao obter os registros.");
            e.printStackTrace();
        }
    }

    private Status getStatus(int index, Elements colunas) {
        return Status.fromIcone( colunas.get(index).select("img").attr("src") );
    }

}
