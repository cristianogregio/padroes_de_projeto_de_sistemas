package gov.br.sp.fatec.condominio.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cristiano
 */
@RestController
@CrossOrigin
public class Hello
{

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String hello()
    {
        return "<b>Olá Cristiano!!!</b>";
    }
}
