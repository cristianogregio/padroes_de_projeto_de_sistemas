package gov.br.sp.fatec.condominio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gov.br.sp.fatec.condominio.entities.Autorizacao;


public interface AutorizacaoRepository extends CrudRepository<Autorizacao, Long> {
    
    public Autorizacao findByNome(String nome);
    
    public List<Autorizacao> findByNomeContainsIgnoreCase(String nome);

}