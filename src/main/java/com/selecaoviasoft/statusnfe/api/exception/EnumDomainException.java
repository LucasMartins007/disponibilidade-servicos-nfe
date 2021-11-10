/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.api.exception;

/**
 *
 * @author lucas
 */
public enum EnumDomainException {
    
    UF_NAO_ENCONTRADA("Estado {0} não foi encontrado"),
    SERVICO_NAO_ENCONTRADO("Servico {0} não foi encontrado")
    ;
    
    private final String message;

    private EnumDomainException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
