package gov.br.sp.fatec.condominio.serivces;

import java.util.List;

import gov.br.sp.fatec.condominio.entities.Autorizacao;


public interface AutorizacaoService {
    
    public Autorizacao salvar(Autorizacao autorizacao);
    
    public void excluir(Long idAutorizacao);
    
    public List<Autorizacao> todos();
    
    public List<Autorizacao> buscar(String nome);
    
    public Autorizacao buscarPorId(Long idAutorizacao);

}