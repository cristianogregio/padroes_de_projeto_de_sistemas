package gov.br.sp.fatec.condominio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import gov.br.sp.fatec.condominio.entities.Autorizacao;
import gov.br.sp.fatec.condominio.entities.Usuario;
import gov.br.sp.fatec.condominio.repository.AutorizacaoRepository;
import gov.br.sp.fatec.condominio.repository.UsuarioRepository;
import gov.br.sp.fatec.condominio.response.JwtResponse;
import gov.br.sp.fatec.condominio.security.JwtUtils;

/**
 * @author Cristiano
 */
@RestController
@RequestMapping(path = "/autenticacao")
@CrossOrigin(origins = "*")
public class LoginController
{
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    private AutorizacaoRepository autorizacaoRepo;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtUtils jwtUtils;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Usuario pUsuario) throws JsonProcessingException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(pUsuario.getUsername(), pUsuario.getSenha()));

        Usuario user = usuarioRepo.findByNome(pUsuario.getNome());
        pUsuario.setAutorizacoes(user.getAutorizacoes());
        //JwtUtils.generateToken(pUsuario);
        
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(pUsuario);
        //String jwt = JwtUtils.generateToken(pUsuario);
        
        Usuario userDetails = (Usuario) authentication.getPrincipal();      
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println(jwt);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
    }
    
    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<?> registerUser(@RequestBody Usuario pUsuario) {
        if (usuarioRepo.existsByNome(pUsuario.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Usuário já existente. Escolha outro.");
        }

        Usuario usuario = new Usuario(pUsuario.getUsername(), encoder.encode(pUsuario.getSenha()));
        
        if(pUsuario.getUsername().contains("sindico_"))
        {
            adicionaAutorizacao(usuario, "ROLE_SINDICO");
        }
        else
        {
            adicionaAutorizacao(usuario, "ROLE_MORADOR");
        }
        
        usuarioRepo.save(usuario);
        
        return ResponseEntity.ok("Usuário registrado!");
    }

    /**
     * @param usuario
     */
    private void adicionaAutorizacao(Usuario usuario, String role)
    {
        Autorizacao autorizacao = autorizacaoRepo.findByNome(role);
        usuario.setAutorizacoes(new ArrayList<Autorizacao>());
        usuario.getAutorizacoes().add(autorizacao);
    }
    
    
      @PostMapping("/login1")
      public String autenticar(@RequestBody Usuario login) throws JsonProcessingException
      {
      //Authentication auth = new UsernamePasswordAuthenticationToken(login.getNome(), login.getSenha(), login.getAuthorities());
      //authManager.authenticate(auth);
      //login.setSenha(null);
      Usuario user = usuarioRepo.findByNome(login.getNome());
      login.setAutorizacoes(user.getAutorizacoes());
      //login.setAutorizacao(//login.);
      //login.setToken(JwtUtils.generateToken(login));
      return "";
      }
     
}
