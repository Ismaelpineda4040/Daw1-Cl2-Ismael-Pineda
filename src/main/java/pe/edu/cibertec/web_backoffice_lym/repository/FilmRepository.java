package pe.edu.cibertec.web_backoffice_lym.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.web_backoffice_lym.entity.Film;


//NOTA: CONFIG, ENTITY Y REPOSITORY SON LA CAPA DE DATOS

public interface FilmRepository extends CrudRepository<Film, Integer> {



}
