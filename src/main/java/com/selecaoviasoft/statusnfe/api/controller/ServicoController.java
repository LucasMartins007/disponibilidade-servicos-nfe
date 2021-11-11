/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.api.controller;

import com.selecaoviasoft.statusnfe.domain.model.Servico;
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

    @GetMapping(path = "estado/{uf}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Servico> buscarServicosPorEstado(@PathVariable("uf") String uf);

    @GetMapping("estado/{uf}/servico/{servico}")
    ResponseEntity<String> buscarStatusPorEstadoAndServico(@PathVariable("uf") String uf, @PathVariable("servico") String servico);
}
