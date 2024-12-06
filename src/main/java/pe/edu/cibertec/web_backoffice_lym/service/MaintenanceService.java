package pe.edu.cibertec.web_backoffice_lym.service;

import pe.edu.cibertec.web_backoffice_lym.dto.Detalledto;
import pe.edu.cibertec.web_backoffice_lym.dto.FilmDto;

import java.util.List;

//ACA SE MANEJAN LAS FIRMAS
public interface MaintenanceService {

    List<FilmDto> findAllFilms();

    Detalledto findFilmById(int id);

    Boolean updateFilm(Detalledto detalledto);

    Boolean deleteFilmById(int id);
}
