/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.domain.model;

import com.selecaoviasoft.statusnfe.domain.model.enums.EnumDisponibilidade;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@Entity
@Table(name = "servico")
@SequenceGenerator(name = "seq_servicos", sequenceName = "seq_servicos")
public class Servico implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_servicos")
    private Integer id;
    
    private String autorizador;
    
    @Convert(converter = EnumDisponibilidade.EnumConverter.class)
    private EnumDisponibilidade autorizacao;
    
    @Convert(converter = EnumDisponibilidade.EnumConverter.class)
    private EnumDisponibilidade retornoAtualizacao;
    
    @Convert(converter = EnumDisponibilidade.EnumConverter.class)
    private EnumDisponibilidade inutilizacao;
    
    @Convert(converter = EnumDisponibilidade.EnumConverter.class)
    private EnumDisponibilidade consultaProtocolo;
    
    @Convert(converter = EnumDisponibilidade.EnumConverter.class)
    private EnumDisponibilidade statusServico;
    
    private String tempoMedio;
    
    @Convert(converter = EnumDisponibilidade.EnumConverter.class)
    private EnumDisponibilidade consultaCadastro;
    
    @Convert(converter = EnumDisponibilidade.EnumConverter.class)
    private EnumDisponibilidade recepcaoEvento;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    
    
}
