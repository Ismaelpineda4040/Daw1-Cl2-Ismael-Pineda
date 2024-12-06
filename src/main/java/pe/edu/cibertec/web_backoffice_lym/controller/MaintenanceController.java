package pe.edu.cibertec.web_backoffice_lym.controller;

//CAPA DE PRESENTACION

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.web_backoffice_lym.dto.Detalledto;
import pe.edu.cibertec.web_backoffice_lym.dto.FilmDto;
import pe.edu.cibertec.web_backoffice_lym.service.MaintenanceService;

import java.util.List;


    //http://localhost:8080/maintenance/start este link!!!!

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping("/start")
    public String start(Model model){

        List<FilmDto> films = maintenanceService.findAllFilms();
        model.addAttribute("films", films);

        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){

        Detalledto detalledto = maintenanceService.findFilmById(id);
        model.addAttribute("film", detalledto);

        return "maintenance_detail";

    }

  /////////CODIGO PARA EDITAR////////////

    @GetMapping("/listEdit/{id}")
    public String listEdit(@PathVariable Integer id, Model model){

        Detalledto detalledto = maintenanceService.findFilmById(id);
        model.addAttribute("film", detalledto);

        return "maintenance_edit";

    }

////codigo ventana de alerta
    @PostMapping("/edit-confirm")

    public String editConfirm(@ModelAttribute Detalledto detalledto, Model model){

        maintenanceService.updateFilm(detalledto);

        return "redirect:/maintenance/start";

    }

    @PostMapping("/remove/{id}")

    public String remove(@PathVariable Integer id, Model model){

        System.out.println("Eliminando: " + id);
        return "redirect:/maintenance/start";

    }


}
