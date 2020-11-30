package gov.br.sp.fatec.condominio.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import gov.br.sp.fatec.condominio.common.ViewJson;

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
    
    
    public Set<Salao> getSalao()
    {
        return salao;
    }

    public void setSalao(Set<Salao> pSalao)
    {
        salao = pSalao;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long pId)
    {
        id = pId;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date pData)
    {
        data = pData;
    }

    public boolean isAprovado()
    {
        return aprovado;
    }

    public void setAprovado(boolean pAprovado)
    {
        aprovado = pAprovado;
    }

    
    
}
