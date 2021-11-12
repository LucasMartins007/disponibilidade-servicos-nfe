/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.selecaoviasoft.statusnfe.persistence;

import com.selecaoviasoft.statusnfe.domain.model.Servico;
import com.selecaoviasoft.statusnfe.domain.model.enums.EnumDisponibilidade;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    
    @Query(value = " select s.autorizador from Servico s where s.autorizador in ( "
            + "select s.autorizador, " +
            " (  SUM(case when s.autorizacao = :disponibilidade THEN 1 ELSE 0 END) " +
            "   SUM(case when s.retornoAtualizacao = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when s.inutilizacao = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when s.consultaProtocolo = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when s.statusServico = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when s.consultaCadastro = :disponibilidade then 1 else 0 end) " +
            "	SUM(case when s.recepcaoEvento = :disponibilidade then 1 else 0 end)     ) as soma " +
            " from Servico s " +
            " group by s.autorizador " + 
            " order by soma DESC ) as soma_indisponiveis  ")
    List<Servico> findAllIndisponiveis(@Param("disponibilidade") String disponibilidade);
    
}
