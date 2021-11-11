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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
    public Servico encontrarPorEstadoAndData(String uf, Date data) {
        Servico servico = findAndValidateByUfAndData(uf, data);
        return servico;
    }

    @Override
    public String encontrarEstadoMaiorIndiponibilidade() {
        List<Servico> servicos = servicoRepository.findAllIndisponiveis(EnumDisponibilidade.VERMELHO);
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
        Servico servico = servicoRepository.findFirstByAutorizadorOrderByIdDesc(uf);
        if (Utils.isEmpty(servico)) {
            throw new DomainException(EnumDomainException.UF_NAO_ENCONTRADA.getMessage(), uf);
        }
        return servico;
    }

    public Servico findAndValidateByUfAndData(String uf, Date data) {
        Date startDay = DateUtil.atStartOfDay(data);
        Date endDay = DateUtil.atEndOfDay(data);
        Servico servico = servicoRepository.findByAutorizadorAndDataInclusao(uf, startDay, endDay);
        return servico;
    }

}
