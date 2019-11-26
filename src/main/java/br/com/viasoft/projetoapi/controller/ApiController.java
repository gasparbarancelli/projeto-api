package br.com.viasoft.projetoapi.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ApiController {

    @RequestMapping("/obter")
    @ResponseBody
    public String getPagina() throws IOException {
        Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
        Elements tabela = doc.select("table.tabelaListagemDados");
        Elements lista = tabela.select("tr");
        Elements listaServicos = lista.select("th");
        return listaServicos.eachText().toString();
    }

}
