package gov.br.sp.fatec.condominio.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import gov.br.sp.fatec.condominio.common.ViewJson;
import gov.br.sp.fatec.condominio.entities.Reserva;
import gov.br.sp.fatec.condominio.entities.Salao;
import gov.br.sp.fatec.condominio.repository.ReservaRepository;
import gov.br.sp.fatec.condominio.repository.SalaoRepository;
import gov.br.sp.fatec.condominio.serivces.SalaoService;

/**
 * @author Cristiano
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/reserva")
public class ReservaController
{
    @Autowired
    public ReservaRepository reservaRepo;
    
    @Autowired
    public SalaoRepository salaoRepo;
    
    @Autowired
    public SalaoService salaoService;
    

    @GetMapping(value = "/todos")
    @PreAuthorize("isAuthenticated()")
    @JsonView(ViewJson.SalaoCompleto.class)
    public List<Reserva> buscarTodos()
    {
        return reservaRepo.findAll();
    }
    

    @PostMapping(value = "/cadastrar")
    @JsonView(ViewJson.SalaoCompleto.class)
    @PreAuthorize("isAuthenticated()")
    @Transactional
    public ResponseEntity<?> criarReserva(@RequestBody Reserva pReserva, UriComponentsBuilder uriComponentBuilder)
    {
        if(salaoRepo.existsById(pReserva.getSalao().iterator().next().getId()))
        {
            Reserva reserva = new Reserva();
            Optional<Salao> salao = salaoRepo.findById(pReserva.getSalao().iterator().next().getId());
            Set<Salao> a = new HashSet<Salao>();
            a.add(salao.get());
            reserva.setSalao(a);           
            reserva.setData(pReserva.getData());
            reserva.setAprovado(false);
            reservaRepo.save(reserva);
            
            return new ResponseEntity<Reserva>(reserva, HttpStatus.CREATED);
        }
        
        return ResponseEntity.badRequest().body("Possível que Salão não exista.");
    }
    
    @PostMapping(value = "/aprovar")
    @JsonView(ViewJson.SalaoCompleto.class)
    @PreAuthorize("hasRole('ROLE_SINDICO')")
    @Transactional
    public ResponseEntity<?> aprovarReserva(@RequestBody Reserva pReserva, UriComponentsBuilder uriComponentBuilder)
    {
        if(reservaRepo.existsById(pReserva.getId()))
        {
            Reserva reserva = reservaRepo.getOne(pReserva.getId());
            reserva.setAprovado(pReserva.isAprovado());
            reservaRepo.save(reserva);
            System.out.println("Reservado");
            return new ResponseEntity<Reserva>(reserva, HttpStatus.CREATED);
        }
        
        return ResponseEntity.badRequest().body("Possível que a Reserva não exista ou usuario não permissão.");
    }
    
    
}
