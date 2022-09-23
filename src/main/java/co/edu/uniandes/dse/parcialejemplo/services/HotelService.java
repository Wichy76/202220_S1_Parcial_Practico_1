package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service

public class HotelService {


    @Autowired
    HotelRepository hotelRepository;


    @Transactional
    public HotelEntity createHoteles(HotelEntity hotel) throws IllegalOperationException {
        log.info("Inicia proceso de creación de un hotel");
        Optional<HotelEntity> hotelEntity = hotelRepository.findById(hotel.getId() );
        if ( hotelEntity.isPresent() ) {
            throw new IllegalOperationException("Ya esta creado el hotel");
        }

        return hotelRepository.save(hotel);
    }
}
