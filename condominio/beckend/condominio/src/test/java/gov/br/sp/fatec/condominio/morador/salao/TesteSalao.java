package gov.br.sp.fatec.condominio.morador.salao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import gov.br.sp.fatec.condominio.entities.Salao;
import gov.br.sp.fatec.condominio.repository.SalaoRepository;

/**
 * @author Cristiano Gregio
 */
@SpringBootTest
@Transactional
@Rollback
public class TesteSalao
{
    @Autowired
    private SalaoRepository salaoRepo;

    @Test
    void testaInsercaoSalao()
    {
        Salao salao = new Salao();
        salao.setNome("Salao Gourmet 2");
        salao.setChurrasqueira(true);
        salaoRepo.save(salao);
        assertNotNull(salao.getId());
    }
    
}
