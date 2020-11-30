package gov.br.sp.fatec.condominio.morador.reserva;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import gov.br.sp.fatec.condominio.entities.Reserva;
import gov.br.sp.fatec.condominio.repository.ReservaRepository;
import gov.br.sp.fatec.condominio.repository.SalaoRepository;
import gov.br.sp.fatec.condominio.serivces.ReservaService;

/**
 * @author Cristiano Gregio
 */
@SpringBootTest
@Transactional
@Rollback
public class TesteReserva
{
    @Autowired
    private ReservaRepository reservaRepo;
    
    @Autowired
    private SalaoRepository salaoRepo;
    
    @Autowired
    private ReservaService resService;
    
    @Test
    void testaBuscaReservaPorNomeDosSalao()
    {
        List<Reserva> reservas = reservaRepo.findBySalaoNome("Salão Gourme 1");
        assertTrue(reservas.isEmpty());
    }
    
    @Test
    void testaBuscaReservaPorDatas()
    {
        Date data = new Date();
        data.setHours(1);
        
        Reserva reserva = reservaRepo.findByData(data);
        assertNull(reserva);
    }
    
    @Test
    void testaBuscaReservaPorNomeDosSalaoComQuery()
    {
        List<Reserva> reservas = reservaRepo.buscaPorSalaoNome("Salão Gourme 1");
        assertTrue(reservas.isEmpty());
    }
    
    @Test
    void testaCriarComService()
    {
       Reserva reserva = resService.criarReserva("Salao Gourmet 1");
       assertNotNull(reserva);
    }
}