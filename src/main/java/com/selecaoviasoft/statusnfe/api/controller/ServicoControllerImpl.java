/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.api.controller;

import com.selecaoviasoft.statusnfe.domain.model.Servico;
import com.selecaoviasoft.statusnfe.domain.model.dto.ServicoIndisponivelDTO;
import com.selecaoviasoft.statusnfe.domain.service.ServicoService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
public class ServicoControllerImpl implements ServicoController {

    @Autowired
    private ServicoService servicoService;

    @Override
    public ResponseEntity<Servico> buscarServicosPorEstado(String uf) {
        Servico servico = servicoService.encontrarPorUf(uf);
        return ResponseEntity.ok(servico);
    }

    @Override
    public ResponseEntity<String> buscarStatusPorEstadoAndServico(String uf, String servico) {
        String statusServico = servicoService.encontrarPorEstadoAndServico(uf, servico);
        return ResponseEntity.ok(statusServico);
    }

    @Override
    public ResponseEntity<List<Servico>> buscarStatusPorEstadoPorData(String uf, Date dataInicial, Date dataFinal) {
        List<Servico> servicos = servicoService.encontrarPorEstadoAndData(uf, dataInicial, dataFinal);
        return ResponseEntity.ok(servicos);
    }

    @Override
    public ResponseEntity<ServicoIndisponivelDTO> buscarEstadoMaiorIndisponibilidade() {
        ServicoIndisponivelDTO servicoIndisponivelDTO = servicoService.encontrarEstadoMaiorIndisponibilidade();
        return ResponseEntity.ok(servicoIndisponivelDTO);
    }
}
