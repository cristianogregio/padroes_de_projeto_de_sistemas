package gov.br.sp.fatec.condominio.serivces.impl;

import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.br.sp.fatec.condominio.entities.Reserva;
import gov.br.sp.fatec.condominio.entities.Salao;
import gov.br.sp.fatec.condominio.repository.ReservaRepository;
import gov.br.sp.fatec.condominio.repository.SalaoRepository;
import gov.br.sp.fatec.condominio.serivces.ReservaService;

@Transactional
@Service("SericoDeReserva")
public class ReservaServiceImpl implements ReservaService
{

    @Autowired
    private SalaoRepository salaoRepo;
    
    @Autowired
    private ReservaRepository reservaRepo;
    /**
     * @see gov.br.sp.fatec.condominio.serivces.ReservaService#criarReserva(gov.br.sp.fatec.condominio.entities.Salao)
     */
    @Override
    public Reserva criarReserva(String pSalao)
    {
        Salao salao = salaoRepo.findByNome(pSalao);
        if(salao == null)
        {
            Salao sal = new Salao();
            sal.setNome(pSalao);
            sal.setChurrasqueira(true);
            salaoRepo.save(sal);
        }
        Reserva reserva = new Reserva();
        Date dataInicial = new Date();
        dataInicial.setHours(1);
        Date dataFinal = new Date();
        dataFinal.setHours(2);
        
        reserva.setData(dataInicial);
        reserva.setSalao(new HashSet<Salao>());
        reserva.getSalao().add(salao);
        reservaRepo.save(reserva);
        return reserva;
    }
  
}
