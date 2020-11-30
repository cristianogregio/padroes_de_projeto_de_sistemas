package gov.br.sp.fatec.condominio.entities;

import javax.persistence.*;

@Entity
@Table(name = "mrd_morador")
public class Morador
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mrd_id")
    private Long id;
    
    @Column(name = "mrd_nome")
    private String nome;
    
    @Column(name = "mrd_telefone")
    private String telefone;
    
    @Column(name = "mrd_email")
    private String email;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}
