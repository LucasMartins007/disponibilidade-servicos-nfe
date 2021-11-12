/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.domain.exception;

/**
 *
 * @author lucas
 */
public enum EnumDomainException {
    
    UF_NAO_ENCONTRADA("O estado {0} não foi encontrado. "),
    SERVICO_NAO_ENCONTRADO("O servico {0} não foi encontrado."),
    DATA_INVALIDA("A data inicial:{0}, não deve ser posterior a data final:{1}, tente novamente. "),
    DATA_E_UF_NAO_ENCONTRADOS("Não foram encontrados serviços dispponíveis no estados {0}, entre as datas {1} e {2}.")
    ;
    
    private final String message;

    private EnumDomainException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
