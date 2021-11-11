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
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author lucas
 */
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

    public Servico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

    public EnumDisponibilidade getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(EnumDisponibilidade autorizacao) {
        this.autorizacao = autorizacao;
    }

    public EnumDisponibilidade getRetornoAtualizacao() {
        return retornoAtualizacao;
    }

    public void setRetornoAtualizacao(EnumDisponibilidade retornoAtualizacao) {
        this.retornoAtualizacao = retornoAtualizacao;
    }

    public EnumDisponibilidade getInutilizacao() {
        return inutilizacao;
    }

    public void setInutilizacao(EnumDisponibilidade inutilizacao) {
        this.inutilizacao = inutilizacao;
    }

    public EnumDisponibilidade getConsultaProtocolo() {
        return consultaProtocolo;
    }

    public void setConsultaProtocolo(EnumDisponibilidade consultaProtocolo) {
        this.consultaProtocolo = consultaProtocolo;
    }

    public EnumDisponibilidade getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(EnumDisponibilidade statusServico) {
        this.statusServico = statusServico;
    }

    public String getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(String tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public EnumDisponibilidade getConsultaCadastro() {
        return consultaCadastro;
    }

    public void setConsultaCadastro(EnumDisponibilidade consultaCadastro) {
        this.consultaCadastro = consultaCadastro;
    }

    public EnumDisponibilidade getRecepcaoEvento() {
        return recepcaoEvento;
    }

    public void setRecepcaoEvento(EnumDisponibilidade recepcaoEvento) {
        this.recepcaoEvento = recepcaoEvento;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
    
    
    
}
