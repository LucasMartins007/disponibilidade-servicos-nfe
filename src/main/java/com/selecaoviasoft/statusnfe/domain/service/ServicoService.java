/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.domain.service;

import com.selecaoviasoft.statusnfe.domain.model.Servico;
import com.selecaoviasoft.statusnfe.domain.model.dto.ServicoIndisponivelDTO;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public interface ServicoService {
    
    void saveAll(List<Servico> servico);
    
    Servico encontrarPorUf(String uf);
    
    String encontrarPorEstadoAndServico(String uf, String servico);
    
    List<Servico> encontrarPorEstadoAndData(String uf, Date dataInicial, Date dataFinal);
    
    ServicoIndisponivelDTO encontrarEstadoMaiorIndisponibilidade();
    
}
