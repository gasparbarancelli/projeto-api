package br.com.viasoft.projetoapi.controller;

import br.com.viasoft.projetoapi.model.Historico;
import br.com.viasoft.projetoapi.model.RespostaString;
import br.com.viasoft.projetoapi.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ApiController {

    @Autowired private HistoricoService historicoService;

    @GetMapping("/atual")
    public List<Historico> getDisponibilidade() throws IOException {
        return historicoService.getDisponibilidade();
    }

    @GetMapping("/atual/estado")
    public Historico getDisponibilidadePorEstado(@RequestParam("autorizador") String autorizador) throws IOException {
        return historicoService.getDisponibilidadePorEstado(autorizador);
    }

    @GetMapping("/historico/data")
    public List<Historico> getDisponibilidadePorData(
            @DateTimeFormat( pattern = "dd/MM/yyyy") @RequestParam("inicio") LocalDate dataInicio,
            @DateTimeFormat( pattern = "dd/MM/yyyy") @RequestParam("fim") LocalDate dataFim) {
        return historicoService.findByHoraBetween(
                dataInicio.atStartOfDay(),
                dataFim.atTime(23, 59, 59)
        );
    }

    @GetMapping("/offline")
    public RespostaString teste() {
        return new RespostaString(historicoService.findMostOffline());
    }

}
