package gov.br.sp.fatec.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.br.sp.fatec.condominio.entities.Morador;

public interface MoradorRepository extends JpaRepository<Morador, Long>
{

}
