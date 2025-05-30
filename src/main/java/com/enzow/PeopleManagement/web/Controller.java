package com.enzow.PeopleManagement.web;

import com.enzow.PeopleManagement.domain.Persona;
import com.enzow.PeopleManagement.service.IPersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Application controller that redirects the user to different pages
 * depending on the actions they want to perform, such as adding, editin or deleting persons.
 *
 * @author Enzo Weimann
 */

@org.springframework.stereotype.Controller
@Slf4j
public class Controller {

    @Autowired
    private IPersonService personaService;

    /**
     * Handles the root path "/" and displays the list of people.
     * "var personas" gets the list of persons from the DB
     *
     * @param model Spring component that stores the data that will be sent from the controller to the view
     * @param user User who is managing the app
     * @return sends the list of persons to the "index" view
     */
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var personas = personaService.listarPersonas();
        model.addAttribute("personas", personas);
        model.addAttribute("totalPersonas", personas.size());
        return "index";
    }

    /**
     * Shows the form to add a new person to the DB
     *
     * @param persona Creates a new Persona object to use in the form
     * @return sends to the form to create the person in the "edit" view
     */
    @GetMapping("/agregar")
    public String agregarPersona(Persona persona) {
        return "edit";
    }

    /**
     * Saves the data of a person in the DB
     *
     * @param persona Object filled with the data of a person
     * @param errores Handles field validations in the form. If there are any, it prevents the method from saving the data
     * @return redirects to the main page if the data is saved correctly, or sends back to "edit" view if there are errors
     */
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if (errores.hasErrors()) {
            return "edit";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    /**
     * Shows the form to edit the data of an already existent Persona object
     *
     * @param persona Object with the data of the person to edit
     * @param model Spring component that stores the data that will be sent from the controller to the view
     * @return sends to the form filled with the person's data in the "edit" view
     */
    @GetMapping("/editar/{idpersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "edit";
    }

    /**
     * Deletes from the DB a person based on it's ID
     *
     * @param persona Object with the id of the person to delete
     * @return redirects to the main page after the deletion is complete
     */
    @GetMapping("/eliminar/{idpersona}")
    public String eliminar (Persona persona) {
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
