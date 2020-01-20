package fr.biblioc.bibliocreservation.mapper;

import fr.biblioc.bibliocreservation.dto.ReservationDto;
import fr.biblioc.bibliocreservation.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDto reservationToReservationDto(Reservation reservation);
    Reservation reservationDtoToReservation(ReservationDto reservationDto);
}
