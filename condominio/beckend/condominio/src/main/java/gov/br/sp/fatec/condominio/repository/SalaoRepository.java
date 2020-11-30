package gov.br.sp.fatec.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.br.sp.fatec.condominio.entities.Reserva;
import gov.br.sp.fatec.condominio.entities.Salao;

public interface SalaoRepository extends JpaRepository<Salao, Long>
{
    @Query("select s from Salao s where s.nome = ?1")
    public List<Salao> buscaPorNome(String nome);
    
    public Salao findByNome(String nome);
    
}