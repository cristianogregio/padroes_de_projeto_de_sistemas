package gov.br.sp.fatec.condominio.entities;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import gov.br.sp.fatec.condominio.common.ViewJson;

@Entity
@Table(name = "sal_salao")
public class Salao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sal_id")
    @JsonView(ViewJson.Salao.class)
	Long id;
    
    
    @JsonView({ViewJson.Reserva.class, ViewJson.SalaoCompleto.class, ViewJson.Salao.class})
    @Column(name = "sal_nome")
	String nome;
    
    @JsonView({ViewJson.SalaoCompleto.class, ViewJson.Reserva.class})
    @Column(name = "sal_churrasqueira")
	boolean churrasqueira;

    @JsonView({ViewJson.SalaoCompleto.class, ViewJson.Reserva.class, ViewJson.Salao.class})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "salao")
    private Set<Reserva> reservas;
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long pId)
    {
        id = pId;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    public boolean isChurrasqueira()
    {
        return churrasqueira;
    }

    public void setChurrasqueira(boolean pChurrasqueira)
    {
        churrasqueira = pChurrasqueira;
    }

    public Set<Reserva> getReservas()
    {
        return reservas;
    }

    public void setReservas(Set<Reserva> pReservas)
    {
        reservas = pReservas;
    }
}
