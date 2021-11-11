/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selecaoviasoft.statusnfe.api.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.OffsetDateTime;
import java.util.List;

/**
 *
 * @author lucas
 */
@JsonInclude(Include.NON_NULL)
public class Error {
    
    private Integer status;
	private String tituloErro;
	private OffsetDateTime dataHora;
	private List<CampoErro> camposErro;

        public static class CampoErro{
		private String nomeErro;
		private String mensagemErro;
		
		public CampoErro(String nomeErro, String mensagemErro) {
			super();
			this.nomeErro = nomeErro;
			this.mensagemErro = mensagemErro;
		}
		
		public String getNomeErro() {
			return nomeErro;
		}
		public void setNomeErro(String nomeErro) {
			this.nomeErro = nomeErro;
		}
		public String getMensagemErro() {
			return mensagemErro;
		}
		public void setMensagemErro(String mensagemErro) {
			this.mensagemErro = mensagemErro;
		}
	}
        
        public List<CampoErro> getCampos() {
		return camposErro;
	}

	public void setCampos(List<CampoErro> camposErro) {
		this.camposErro = camposErro;
	}

	public OffsetDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return tituloErro;
	}

	public void setTitulo(String titulo) {
		this.tituloErro = titulo;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
