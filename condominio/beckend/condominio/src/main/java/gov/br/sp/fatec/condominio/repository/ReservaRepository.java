package gov.br.sp.fatec.condominio.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gov.br.sp.fatec.condominio.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>
{
    public List<Reserva> findBySalaoNome(String salao);
    
    public Reserva findByData(Date data);
    
    //@Query("select r from Reserva where r.dataInicial = ?1 or r.dataFinal = ?2")
    //public Reserva buscaPorDataInicialOrDataFinal(Date dataInicial, Date dataFinal);
    
    @Query("select r from Reserva r inner join r.salao a where a.nome = ?1")
    public List<Reserva> buscaPorSalaoNome(String salao);
}
