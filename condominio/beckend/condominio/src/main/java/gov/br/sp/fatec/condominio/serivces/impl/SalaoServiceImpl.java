package gov.br.sp.fatec.condominio.serivces.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import gov.br.sp.fatec.condominio.common.exception.RegistroNaoEncontradoException;
import gov.br.sp.fatec.condominio.entities.Salao;
import gov.br.sp.fatec.condominio.repository.SalaoRepository;
import gov.br.sp.fatec.condominio.serivces.SalaoService;

/**
 * @author Cristiano
 */
@Service("ServicoDeSalao")
public class SalaoServiceImpl implements SalaoService
{
    @Autowired
    public SalaoRepository salaoRepo;
    
    @Autowired
    public SalaoService salaoService;
    
    
    /**
     * @see gov.br.sp.fatec.condominio.serivces.SalaoService#buscarTodosSaloes()
     */
    @Override
    public List<Salao> buscarTodosSaloes()
    {
        return salaoService.buscarTodosSaloes();
    }


    @Override
    @PreAuthorize("isAuthenticated()") //Aqui o bloqueio é no método (na camada de negócio)
    public Salao buscarSalaoPorNome(String pNomeSalao)
    {
        Salao salao = salaoRepo.findByNome(pNomeSalao);
        if(salao != null)
        {
            return salao;
        }
        throw new RegistroNaoEncontradoException("Salão não encontrado");
    }
    
    
}
