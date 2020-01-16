package fr.biblioc.bibliocbibliotheque.mapper;

import fr.biblioc.bibliocbibliotheque.dto.AuteurDto;
import fr.biblioc.bibliocbibliotheque.model.Auteur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuteurMapper {
    AuteurDto auteurtoAuteurDto(Auteur auteur);
    Auteur auteurDtoToAuteur(AuteurDto auteurDto);

}
