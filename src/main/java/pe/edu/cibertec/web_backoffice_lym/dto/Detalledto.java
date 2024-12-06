package pe.edu.cibertec.web_backoffice_lym.dto;

import java.util.Date;

public record Detalledto(Integer filmId,String title, String description, Integer releaseYear, Integer rentalDuration,
                         Double rentalRate, Integer length, Double replacementCost, String rating, String specialFeatures, Date lastUpdate) {


}
