/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.domain.model.enums;

import com.selecaoviasoft.statusnfe.domain.pattern.enums.IEnum;
import com.selecaoviasoft.statusnfe.domain.pattern.enums.AbstractEnumConverter;
import javax.persistence.Converter;

/**
 *
 * @author lucas
 */
public enum EnumDisponibilidade implements IEnum<String> {
    
    VERDE("DISPONIVEL"),
    AMARELO("INDISPONIVEL TEMPORARIAMENTE"),
    VERMELHO("INDISPONIVEL");
    
    private final String key;

    private EnumDisponibilidade(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }
    
    @Converter
    public static class EnumConverter extends AbstractEnumConverter<EnumDisponibilidade, String> {
    }
}
