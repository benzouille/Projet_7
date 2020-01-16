package fr.biblioc.bibliocbibliotheque.mapper;

import fr.biblioc.bibliocbibliotheque.dto.LivreDto;
import fr.biblioc.bibliocbibliotheque.model.Livre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivreMapper {
    LivreDto livreToLivreDto(Livre livre);
    Livre livreDtoToLivre(LivreDto livreDto);
}
