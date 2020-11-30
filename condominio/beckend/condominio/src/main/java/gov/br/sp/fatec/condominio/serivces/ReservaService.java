package gov.br.sp.fatec.condominio.serivces;

import gov.br.sp.fatec.condominio.entities.Reserva;
import gov.br.sp.fatec.condominio.entities.Salao;

public interface ReservaService 
{
    public Reserva criarReserva(String salao);
}
