package pe.edu.cibertec.web_backoffice_lym.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.web_backoffice_lym.dto.Detalledto;
import pe.edu.cibertec.web_backoffice_lym.dto.FilmDto;
import pe.edu.cibertec.web_backoffice_lym.entity.Film;
import pe.edu.cibertec.web_backoffice_lym.repository.FilmRepository;
import pe.edu.cibertec.web_backoffice_lym.service.MaintenanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired // esto es para darle memoria
    FilmRepository filmRepository;

    @Override
    @Cacheable(value = "films", unless = "#result == null")
    public List<FilmDto> findAllFilms() {


        List<FilmDto> films = new ArrayList<FilmDto>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(), film.getTitle(), film.getDescription()
            );
            films.add(filmDto);
        });
        return films;
    }

    @Override
    public Detalledto findFilmById(int id) {

        Optional<Film> optional = filmRepository.findById(id);

        return optional.map(film -> new Detalledto(film.getFilmId(), film.getTitle(), film.getDescription(), film.getReleaseYear(),
                 film.getRentalDuration(), film.getRentalRate(), film.getLength(), film.getReplacementCost(), film.getRating(), film.getSpecialFeatures(),
                film.getLastUpdate())
        ).orElse(null);
    }

    @Override
    @CacheEvict(value = "films", allEntries = true)
    public Boolean updateFilm(Detalledto detalledto) {
        Optional<Film> optional = filmRepository.findById(detalledto.filmId());
        return optional.map(
                film -> {
                    film.setTitle(detalledto.title());
                    film.setReleaseYear(detalledto.releaseYear());
                    film.setRentalDuration(detalledto.rentalDuration());
                    film.setRentalRate(detalledto.rentalRate());

                    filmRepository.save(film);
                    return true;
                }
        ).orElse(false);
    }


    @Override
    @CacheEvict(value = "films", allEntries = true)
    public Boolean deleteFilmById(int id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(film -> {
            filmRepository.delete(film);
            return true;
        }).orElse(false);
    }


}
