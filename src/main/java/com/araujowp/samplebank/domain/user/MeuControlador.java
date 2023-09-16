package com.araujowp.samplebank.domain.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeuControlador {
    @GetMapping("/minha-pagina")
    public String minhaPagina() {
        // Lógica do controlador aqui
        return "minha-pagina";
    }
}
