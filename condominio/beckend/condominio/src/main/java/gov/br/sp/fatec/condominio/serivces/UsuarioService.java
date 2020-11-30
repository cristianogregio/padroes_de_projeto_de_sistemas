package gov.br.sp.fatec.condominio.serivces;

import java.util.List;

import gov.br.sp.fatec.condominio.entities.Usuario;

public interface UsuarioService
{

    public Usuario incluirUsuario(String nome, String senha, String nomeAutorizacao);

    public List<Usuario> buscar(String nome);

    public Usuario buscar(Long id);

    public List<Usuario> todos();

    public Usuario salvar(Usuario usuario);

}
