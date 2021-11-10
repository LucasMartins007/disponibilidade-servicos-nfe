/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.job;

import com.selecaoviasoft.statusnfe.job.gerenciadores.GerenciadorJobStatusServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
@EnableScheduling
public class JobVerificacaoStatusServico {

    @Value("${url.portal.disponibilidade}")
    private String url;
    
    @Autowired
    private GerenciadorJobStatusServico gerenciadorJob;
    
    @Scheduled(cron = "0 0/5 * * * *")
    public void verificarStatus() {
        gerenciadorJob.verificarStatusServico(url);
    }
}
