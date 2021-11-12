/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selecaoviasoft.statusnfe.domain.model.dto;

import lombok.Getter;
import lombok.Setter;



/**
 *
 * @author operador
 */
@Getter
@Setter
public class ServicoIndisponivelDTO {
 
    private String estado;
    
    private Integer qtdeIndisponiveis;
}
