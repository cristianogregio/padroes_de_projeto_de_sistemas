package gov.br.sp.fatec.condominio.serivces;

import java.util.List;

import gov.br.sp.fatec.condominio.entities.Salao;

/**
 * @author Cristiano
 */
public interface SalaoService
{
    public List<Salao> buscarTodosSaloes();
    
    public Salao buscarSalaoPorNome(String nomeSalao);
}
