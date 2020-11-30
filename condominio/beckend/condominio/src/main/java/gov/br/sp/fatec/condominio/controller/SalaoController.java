package gov.br.sp.fatec.condominio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import gov.br.sp.fatec.condominio.common.ViewJson;
import gov.br.sp.fatec.condominio.entities.Reserva;
import gov.br.sp.fatec.condominio.entities.Salao;
import gov.br.sp.fatec.condominio.repository.SalaoRepository;
import gov.br.sp.fatec.condominio.serivces.SalaoService;

/**
 * @author Cristiano
 */
@RestController
@CrossOrigin// permite que requisições sejam feitas de outros servidores
@RequestMapping(value = "/salao")
public class SalaoController
{
    @Autowired
    public SalaoRepository salaoRepo;
    
    @Autowired
    public SalaoService salaoService;
    
    //192.168.56.1:8082/condominio/salao/todos
    @GetMapping(value = "/todos")
    @JsonView(ViewJson.SalaoCompleto.class)
    //@PreAuthorize("hasRole('ROLE_SINDICO')") //Aqui po bloqueio é na rota.
    @PreAuthorize("isAuthenticated()")
    public List<Salao> buscarTodos()
    {
        return salaoRepo.findAll();
    }
    
    
    //192.168.56.1:8081/condominio/salao/5
    @GetMapping(value = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public Optional<Salao> buscarPorId(@PathVariable("id") Long id)
    {
        return salaoRepo.findById(id);
    }
    
    //192.168.56.1:8082/condominio/salao/nome?nome=Salao Gourmet 1
    @JsonView(ViewJson.SalaoCompleto.class)
    @GetMapping(value = "/nome")
    public Salao buscarPorNome(@RequestParam("nome") String nome)
    {
        //O método buscarSalaoPorNome() tem um check de 'autenticação'
        return salaoService.buscarSalaoPorNome(nome);
    }
    
    //192.168.56.1:8081/condominio/salao/cadastrar
    // { "nome": "Salao Gourmet 4" }
    @PostMapping(value = "/cadastrar")
    @PreAuthorize("hasRole('ROLE_SINDICO')")
    public ResponseEntity<Salao> buscarPorNome(@RequestBody Salao pSalao, UriComponentsBuilder uriComponentBuilder)
    {
        Salao salao = new Salao();
        Reserva reserva = new Reserva();
        salao.setNome(pSalao.getNome());
        salao.setChurrasqueira(true);
        salaoRepo.save(salao);
        
        // Este é padrão adotado para REST
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uriComponentBuilder.path("/salao/" + salao.getId()).build().toUri());
        
        return new ResponseEntity<Salao>(salao, responseHeaders, HttpStatus.CREATED);
    }
}
