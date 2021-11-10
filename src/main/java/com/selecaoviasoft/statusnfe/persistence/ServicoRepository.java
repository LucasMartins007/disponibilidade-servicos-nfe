/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.selecaoviasoft.statusnfe.persistence;

import com.selecaoviasoft.statusnfe.domain.model.Servico;
import com.selecaoviasoft.statusnfe.domain.model.enums.EnumDisponibilidade;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{

    public Servico findByAutorizador(String uf);

    
    @Query(" SELECT s FROM Servico s "
            + " WHERE s.dataInclusao BETWEEN :startDay AND :endDay "
            + "     AND s.autorizador = :uf ")
    public Servico findByAutorizadorAndDataInclusao(String uf, Date startDay, Date endDay);
    
    @Query(value = " select AUTORIZADOR, " +
           " (SUM(case when autorizacao = :disponibilidade THEN 1 ELSE 0 END) " +
            " SUM(case when retorno_atualizacao = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when inutilizacao = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when consulta_protocolo = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when status_servico = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when consulta_cadastro = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when recepcao_evento = :disponibilidade then 1 else 0 end)) as soma " +
           " from servico " +
           " group by AUTORIZADOR " + 
           " order by SOMA DESC " , nativeQuery = true)
    public List<Servico> findAllIndisponiveis(EnumDisponibilidade disponibilidade);
    
}
