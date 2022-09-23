package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service

public class HabitacionService {

    @Autowired
    HabitacionRepository habitacionRepository;

    @Transactional
    public HabitacionEntity createHabitacion(HabitacionEntity habitacion) throws IllegalOperationException {
        log.info("Inicia proceso de creación de una habitacion");
        if ( habitacion.getNumPersonas() <= habitacion.getNumCamas()/2 ) {
            throw new IllegalOperationException("La habitacion tiene un numero de personas o habitaciones incorrecto");
        }

        return habitacionRepository.save(habitacion);
    }
}