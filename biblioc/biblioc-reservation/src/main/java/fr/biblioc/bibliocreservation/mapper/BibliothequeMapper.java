package fr.biblioc.bibliocreservation.mapper;

import fr.biblioc.bibliocreservation.dto.BibliothequeDto;
import fr.biblioc.bibliocreservation.model.Bibliotheque;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BibliothequeMapper {
    BibliothequeDto bibliothequeToBibliothequeDto(Bibliotheque bibliotheque);
    Bibliotheque bibliothequeDtoToBibliotheque(BibliothequeDto bibliothequeDto);
}
