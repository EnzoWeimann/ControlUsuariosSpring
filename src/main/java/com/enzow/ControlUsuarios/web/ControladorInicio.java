package com.enzow.ControlUsuarios.web;

import com.enzow.ControlUsuarios.domain.Persona;
import com.enzow.ControlUsuarios.servicio.InterfazPersonaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private InterfazPersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {//Model permite compartir información con la vista
        log.info("Usuario logeado: " + user);
        var personas = personaService.listarPersonas(); //Recupero datos desde la DB
        model.addAttribute("personas", personas); //envio a front en forma key / value
        model.addAttribute("totalPersonas", personas.size());
        return "index";
    }

    @GetMapping("/agregar")
    public String agregarPersona(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if (errores.hasErrors()) {
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idpersona}") // <- pathVariable
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar/{idpersona}")
    public String eliminar (Persona persona) {
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
