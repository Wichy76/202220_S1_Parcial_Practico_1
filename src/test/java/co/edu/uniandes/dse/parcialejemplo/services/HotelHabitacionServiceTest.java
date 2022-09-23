package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HotelHabitacionService.class)
public class HotelHabitacionServiceTest {
    @Autowired
    private HotelHabitacionService hotelHabitacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();


    /**
     * Prueba para crear un hotel
     */
    @Test
    void testAddHabitacion() throws IllegalOperationException, EntityNotFoundException {
        HotelEntity newHotel = factory.manufacturePojo(HotelEntity.class);
        entityManager.persist(newHotel);

        HabitacionEntity newHabitacion = factory.manufacturePojo(HabitacionEntity.class);
        newHabitacion.setNumCamas(2);
        newHabitacion.setNumPersonas(500);
        entityManager.persist(newHabitacion);

        hotelHabitacionService.addHabitacion(newHotel.getId(), newHabitacion.getId());

        HabitacionEntity lastHabitacion = newHotel.getHabitaciones().get(0);
        assertEquals(newHabitacion.getId(), lastHabitacion.getId());
        assertEquals(newHabitacion.getHotel(), lastHabitacion.getHotel());
        assertEquals(newHabitacion.getNumBanios(), lastHabitacion.getNumBanios());
        assertEquals(newHabitacion.getNumIdentificacion(), lastHabitacion.getNumIdentificacion());
        assertEquals(newHabitacion.getNumCamas(), lastHabitacion.getNumCamas());
        assertEquals(newHabitacion.getNumPersonas(), lastHabitacion.getNumPersonas());
    }

    @Test
    void testAddHabitacionInvalidHotel() throws IllegalOperationException, EntityNotFoundException {
        assertThrows(EntityNotFoundException.class, ()->{
            HotelEntity newHotel = factory.manufacturePojo(HotelEntity.class);
            entityManager.persist(newHotel);
            hotelHabitacionService.addHabitacion(newHotel.getId(), 12312312312L);
        });
    }

    @Test
    void testAddHabitacionInvalidHabitacion() throws IllegalOperationException, EntityNotFoundException {
        assertThrows(EntityNotFoundException.class, ()->{
            HabitacionEntity newHabitacion = factory.manufacturePojo(HabitacionEntity.class);
            newHabitacion.setNumCamas(2);
            newHabitacion.setNumPersonas(500);
            entityManager.persist(newHabitacion);
            hotelHabitacionService.addHabitacion(23412341244231L, newHabitacion.getId());
        });
    }
}
