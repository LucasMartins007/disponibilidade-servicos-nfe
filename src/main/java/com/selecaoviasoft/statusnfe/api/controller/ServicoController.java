/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.api.controller;

import com.selecaoviasoft.statusnfe.domain.model.Servico;
import com.selecaoviasoft.statusnfe.domain.model.dto.ServicoIndisponivelDTO;
import com.selecaoviasoft.statusnfe.domain.util.DateUtil;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = ServicoController.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public interface ServicoController {

    public static final String PATH = "servico/";

    @GetMapping("estado/{uf}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Servico> buscarServicosPorEstado(@PathVariable("uf") String uf);

    @GetMapping("estado/{uf}/servico/{servico}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> buscarStatusPorEstadoAndServico(@PathVariable("uf") String uf,
            @PathVariable("servico") String servico);
    
    @GetMapping("estado/{uf}/filtrar")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<Servico>> buscarStatusPorEstadoPorData(@PathVariable("uf") String uf,
            @RequestParam(value = "dataInicial", required = true) @DateTimeFormat(pattern = DateUtil.DATE_PATTERN) Date dataInicial, 
            @RequestParam(value = "dataFinal", required = true) @DateTimeFormat(pattern = DateUtil.DATE_PATTERN) Date dataFinal);
    
    @GetMapping("indisponiveis")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ServicoIndisponivelDTO> buscarEstadoMaiorIndisponibilidade();
    
    
}
