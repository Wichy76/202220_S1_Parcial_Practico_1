package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Service
public class HotelHabitacionService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Transactional
    public HabitacionEntity addHabitacion(Long hotelId, Long habitacionId) throws EntityNotFoundException {
        log.info("Inicia proceso de asociarle una habitacion a un hotel con id ", hotelId);
        Optional<HabitacionEntity> habitacion = habitacionRepository.findById(habitacionId);
        if (habitacion.isEmpty())
            throw new EntityNotFoundException("Habitacion no encontrada");

        Optional<HotelEntity> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty())
            throw new EntityNotFoundException("Hotel no encontrado");

        hotel.get().getHabitaciones().add(habitacion.get());
        log.info("Terimna el proceso de asociarle a un hotel la habitacion con id", habitacionId);
        return habitacion.get();
    }
}
