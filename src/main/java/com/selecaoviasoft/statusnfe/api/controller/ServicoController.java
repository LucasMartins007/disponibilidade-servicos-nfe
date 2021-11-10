/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.api.controller;

import com.selecaoviasoft.statusnfe.domain.model.Servico;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(value = ServicoController.PATH)
public interface ServicoController {

    final String PATH = "servico/";

    @GetMapping("estado/{uf}")
    ResponseEntity<Servico> buscarServicosPorEstado(@PathVariable("uf") String uf);

    @GetMapping("estado/{uf}/servico/{servico}")
    ResponseEntity<String> buscarStatusPorEstadoAndServico(@PathVariable("uf") String uf, @PathVariable("servico") String servico);
}
