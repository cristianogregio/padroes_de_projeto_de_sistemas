package gov.br.sp.fatec.condominio.security;

/**
 * @author Cristiano
 */
public class Login
{
    private String username;
    private String password;
    private String token;
    private String autorizacao;

    public Login()
    {
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String pToken)
    {
        token = pToken;
    }

    public String getAutorizacao()
    {
        return autorizacao;
    }

    public void setAutorizacao(String pAutorizacao)
    {
        autorizacao = pAutorizacao;
    }

}
