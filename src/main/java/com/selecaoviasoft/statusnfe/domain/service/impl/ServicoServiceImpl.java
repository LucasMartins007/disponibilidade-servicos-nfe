/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.domain.service.impl;

import com.selecaoviasoft.statusnfe.domain.exception.DomainException;
import com.selecaoviasoft.statusnfe.domain.exception.EnumDomainException;
import com.selecaoviasoft.statusnfe.domain.model.Servico;
import com.selecaoviasoft.statusnfe.domain.model.enums.EnumDisponibilidade;
import com.selecaoviasoft.statusnfe.domain.service.ServicoService;
import com.selecaoviasoft.statusnfe.domain.util.DateUtil;
import com.selecaoviasoft.statusnfe.domain.util.StringUtil;
import com.selecaoviasoft.statusnfe.domain.util.Utils;
import com.selecaoviasoft.statusnfe.persistence.ServicoRepository;
import com.sun.tools.javac.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class ServicoServiceImpl implements ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public void saveAll(List<Servico> servicos) {
        servicoRepository.saveAll(servicos);
    }

    @Override
    public Servico encontrarPorUf(String uf) {
        Servico servicos = findAndValidateByUf(uf);
        return servicos;
    }

    @Override
    public String encontrarPorEstadoAndServico(String uf, String nomeServico) {
        Servico servico = findAndValidateByUf(uf);
        nomeServico = StringUtil.toUpperCase(nomeServico);
        return resolverServico(servico, nomeServico);
    }

    @Override
    public List<Servico> encontrarPorEstadoAndData(String uf, Date dataInicial, Date dataFinal) {
        List<Servico> servicos = findAndValidateByUfAndData(uf, dataInicial, dataFinal);
        return servicos;
    }

    @Override
    public String encontrarEstadoMaiorIndiponibilidade() {
        List<Servico> servicos = servicoRepository.findAllIndisponiveis(EnumDisponibilidade.INDISPONIVEL.getKey());
        return servicos.get(0).getAutorizador();
    }

    private String resolverServico(Servico servico, String nomeServico) {
        switch (nomeServico) {
            case "AUTORIZACAO":
                return servico.getAutorizacao().getKey();

            case "RETORNO_AUTORIZACAO":
                return servico.getRetornoAtualizacao().getKey();

            case "INUTILIZACAO":
                return servico.getInutilizacao().getKey();

            case "CONSULTA_PROTOCOLO":
                return servico.getConsultaProtocolo().getKey();

            case "STATUS_SERVICO":
                return servico.getStatusServico().getKey();

            case "CONSULTA_CADASTRO":
                return servico.getConsultaCadastro().getKey();

            case "RECEPCAO_EVENTO":
                return servico.getRecepcaoEvento().getKey();

            default:
                throw new DomainException(EnumDomainException.SERVICO_NAO_ENCONTRADO.getMessage(), nomeServico);
        }
    }

    public Servico findAndValidateByUf(String uf) {
        Servico servico = servicoRepository.findFirstByAutorizadorOrderByIdDesc(StringUtils.toUpperCase(uf));
        if (Utils.isEmpty(servico)) {
            throw new DomainException(EnumDomainException.UF_NAO_ENCONTRADA.getMessage(), uf);
        }
        return servico;
    }

    public List<Servico> findAndValidateByUfAndData(String uf, Date dataInicial, Date dataFinal) {
        if(dataFinal.before(dataInicial)){
            throw new DomainException(EnumDomainException.DATA_INVALIDA.getMessage(), dataInicial, dataFinal);
        }
        
        List<Servico> servicos = servicoRepository.findFirstByAutorizadorAndDataInclusaoOrderByDataInclusaoDesc(uf, dataInicial, dataFinal);
        if (Utils.isEmpty(servicos)) {
                throw new DomainException(EnumDomainException.DATA_E_UF_NAO_ENCONTRADOS.getMessage(), uf, dataInicial, dataFinal);
        }
        return servicos;
    }

}
