/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.selecaoviasoft.statusnfe.domain.pattern.enums;

/**
 *
 * @author lucas
 */
public interface IEnum<E> {
    
    E getKey();

    default String getName() {
        return ((Enum) this).name();
    }


}