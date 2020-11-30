package gov.br.sp.fatec.condominio.morador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import gov.br.sp.fatec.condominio.entities.Morador;
import gov.br.sp.fatec.condominio.repository.MoradorRepository;

/**
 * @author Cristiano Gregio
 */
//@SpringBootTest
//@Transactional
//@Rollback
public class TesteMorador
{
    @Autowired
    private MoradorRepository moradorRepo;

    //@Test
    void testaInsercaoMorador()
    {
        Morador morador = new Morador();
        morador.setNome("Cristiano");
        morador.setTelefone("98169-5690");
        morador.setEmail("cristiano.nascimento8@fatec.sp.gov.br");
        moradorRepo.save(morador);
        assertNotNull(morador.getId());
    }
}
