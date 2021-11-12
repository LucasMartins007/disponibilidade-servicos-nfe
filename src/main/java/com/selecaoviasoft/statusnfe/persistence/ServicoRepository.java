/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.selecaoviasoft.statusnfe.persistence;

import com.selecaoviasoft.statusnfe.domain.model.Servico;
import com.selecaoviasoft.statusnfe.domain.model.dto.ServicoIndisponivelDTO;
import com.selecaoviasoft.statusnfe.domain.model.vo.ServicoIndisponivelVO;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{

    Servico findFirstByAutorizadorOrderByIdDesc(String uf);

    
    @Query(" SELECT s FROM Servico s "
            + " WHERE s.dataInclusao BETWEEN :dataInicial AND :dataFinal "
            + "     AND s.autorizador = :uf "
            + " ORDER BY s.dataInclusao DESC ")
    List<Servico> findFirstByAutorizadorAndDataInclusaoOrderByDataInclusaoDesc(String uf, Date dataInicial, Date dataFinal);
    
    @Query(value = " select autorizador as estado, soma as qtdeIndisponiveis from ( "
            + "select autorizador, " +
            "  ( (case when autorizacao = :disponibilidade THEN 1 ELSE 0 END) " +
            "   + SUM(case when retorno_atualizacao = :disponibilidade then 1 else 0 end) " +
            "	+ SUM(case when inutilizacao = :disponibilidade then 1 else 0 end) " +
            "	+ SUM(case when consulta_protocolo = :disponibilidade then 1 else 0 end) " +
            "	+ SUM(case when status_servico = :disponibilidade then 1 else 0 end) " +
            "	+ SUM(case when consulta_cadastro = :disponibilidade then 1 else 0 end) " +
            "	+ SUM(case when recepcao_evento = :disponibilidade then 1 else 0 end)) as soma " +
            " from servico " +
            " group by autorizador, autorizacao " + 
            " order by soma DESC ) as soma_indisponiveis  ", nativeQuery = true)
    List<ServicoIndisponivelVO> findAllIndisponiveis(@Param("disponibilidade") String disponibilidade);
    
}
