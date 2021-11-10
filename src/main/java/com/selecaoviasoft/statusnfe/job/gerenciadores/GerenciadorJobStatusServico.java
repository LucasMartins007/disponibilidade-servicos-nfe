/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.job.gerenciadores;

import com.selecaoviasoft.statusnfe.domain.model.Servico;
import com.selecaoviasoft.statusnfe.domain.model.enums.EnumDisponibilidade;
import com.selecaoviasoft.statusnfe.domain.service.ServicoService;
import com.selecaoviasoft.statusnfe.domain.util.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
public class GerenciadorJobStatusServico {

    @Autowired
    private ServicoService servicoService;

    public void verificarStatusServico(String url) {
        try {
            List<Servico> servicos = new ArrayList<>();
            Document document = Jsoup.connect(url).get();
            Elements elementsTable = document.getElementsByClass("tabelaListagemDados");

            for (Element table : elementsTable) {
                Elements rows = table.getElementsByTag("tr");

                for (Element row : rows) {
                    List<String> collumnsString = new ArrayList<>();
                    List<String> status = new ArrayList<>();

                    Elements collumns = row.getElementsByTag("td");
                    for (Element collumn : collumns) {
                        if (Utils.isNotEmpty(collumn.text())) {
                            collumnsString.add(collumn.text());
                        }
                        
                        final String stats = verificarStatusColunas(collumn);
                        status.add(stats);
                        
                    }

                    if (Utils.isNotEmpty(collumnsString) && Utils.isNotEmpty(status)) {
                        final Servico servico = novoServico(collumnsString, status);
                        servicos.add(servico);
                    }
                }
            }
            servicoService.saveAll(servicos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String verificarStatusColunas(Element collumn) {
        final Elements images = collumn.getElementsByTag("img");
        for (Element image : images) {
            Elements tagsSrc = image.getElementsByAttribute("src");
            for (Element tagSrc : tagsSrc) {
                return tagSrc.toString();
            }
        }
        return null;
    }
    

    public Servico novoServico(List<String> collumns, List<String> status) {
        Servico servico = new Servico();
        servico.setAutorizador(collumns.get(0));
        servico.setAutorizacao(resolverEnumDisponibilidade(status.get(1)));
        servico.setRetornoAtualizacao(resolverEnumDisponibilidade(status.get(2)));
        servico.setInutilizacao(resolverEnumDisponibilidade(status.get(3)));
        servico.setConsultaProtocolo(resolverEnumDisponibilidade(status.get(4)));
        servico.setStatusServico(resolverEnumDisponibilidade(status.get(5)));
        servico.setTempoMedio(collumns.get(1));
        servico.setConsultaCadastro(resolverEnumDisponibilidade(status.get(7)));
        servico.setRecepcaoEvento(resolverEnumDisponibilidade(status.get(8)));
        servico.setDataInclusao(new Date());
        return servico;
    }

    public EnumDisponibilidade resolverEnumDisponibilidade(String status) {
        if (status == null) {
            return null;
        }
        if (status.contains("verde")) {
            return EnumDisponibilidade.VERDE;
        }
        if (status.contains("amarelo")) {
            return EnumDisponibilidade.AMARELO;
        }
        if (status.contains("vermelho")) {
            return EnumDisponibilidade.VERMELHO;
        }
        return null;
    }

}
