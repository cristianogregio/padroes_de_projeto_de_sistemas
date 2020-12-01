# Padrões de Projetos de Sistemas

Este repositório é utilizado para mostrar o projeto da disciplina de Padrões de Projetos de Sistemas.

## Aluno
- [Cristiano Gregio](https://gitlab.com/cristiano.gregio)

## Professor
- [Giuliano Bertoti](https://github.com/giulianobertoti)


### Objetivo
Objetivo deste projeto é demonstração o Padrão de Projetos MVC. Para isso, a aplicação de reserva de salão de condomínio foi implementada.

### Como rodar o projeto ?
Para cada parte do sistema, veja as instruções de como rodar o:
- [Backend](https://github.com/cristianogregio/padroes_de_projeto_de_sistemas/blob/master/condominio/beckend/condominio/README.md) 
- [Frontend](https://github.com/cristianogregio/padroes_de_projeto_de_sistemas/blob/master/condominio/frontend/condominio/README.md)

### MVC
MVC é o acrônimo de **Model-View-Controller** (em português: Arquitetura Modelo-Visão-Controle - MVC) é um padrão de projeto de software, ou padrão de arquitetura de software formulado na década de 1970, focado no reuso de código e a separação de conceitos em três camadas interconectadas, onde a apresentação dos dados e interação dos usuários (front-end) são separados dos métodos que interagem com o banco de dados (back-end).

Abaixo tem exemplos de como o Modle, o Controle e a Visão foram implementados neste projeto.

### Modelo
Exemplo da modelagem da Reserva.

```java
@Entity
@Table(name = "rsv_reserva")
public class Reserva
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsv_id")
    @JsonView({ViewJson.SalaoCompleto.class})
	private Long id;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "res_reserva_salao",
       joinColumns = {@JoinColumn(name = "rsv_id")},
       inverseJoinColumns = {@JoinColumn (name = "sal_id")})
    @JsonView({ViewJson.Reserva.class, ViewJson.ReservaCompleto.class})
    private Set<Salao> salao;
    
    @JsonView({ViewJson.Reserva.class, ViewJson.SalaoCompleto.class})
    @Column(name = "srv_data") 
    private Date data;
    
    @JsonView({ViewJson.Reserva.class, ViewJson.SalaoCompleto.class})
    @Column(name = "srv_aprovado")
    private boolean aprovado;
```

### Controle
Exemplo de controle da Reserva.

```java
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

    @PostMapping(value = "/cadastrar")
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
```
- Exemplo utilizando o Postman para envio de request para o Controller

![controle](https://github.com/cristianogregio/padroes_de_projeto_de_sistemas/blob/master/condominio/Reserva.png)


### Visão
Exemplo de Visão para fazer uma Reserva.

```javascript
import axios from 'axios';
import authHeader from './auth-header';
import reservaCadastroJSON from './reserva';

const API_URL = 'http://localhost:8082/condominio/';

class SalaoService {
  reservarSalao(reserva) {
    return axios({
      method: "POST",
      url: API_URL + 'reserva/cadastrar',
      headers: authHeader(),
      data: reservaCadastroJSON(reserva)
       
    });
  }

}

export default new SalaoService();
```

- Tela de Reserva

![visao](https://github.com/cristianogregio/padroes_de_projeto_de_sistemas/blob/master/condominio/ReservaView.png)



### Outras telas do sistema
- Login
![login](https://github.com/cristianogregio/padroes_de_projeto_de_sistemas/blob/master/condominio/LoginView.png)

- Cadastro de usuários
![cadastro](https://github.com/cristianogregio/padroes_de_projeto_de_sistemas/blob/master/condominio/Cadastro.png)

- Tela de aprovação de Reservas por parte do Sindico
![sindico aprovação de reserva](https://github.com/cristianogregio/padroes_de_projeto_de_sistemas/blob/master/condominio/Sindico.png)









