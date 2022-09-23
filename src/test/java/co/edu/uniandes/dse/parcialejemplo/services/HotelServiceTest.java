package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
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
@Import(HotelService.class)
public class HotelServiceTest {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();


    /**
     * Prueba para crear un hotel
     */
    @Test
    void testCreateHoteles() throws IllegalOperationException {
        HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
        HotelEntity result = hotelService.createHoteles(newEntity);
        assertNotNull(result);
        HotelEntity entity = entityManager.find(HotelEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getEstrellas(), entity.getEstrellas());
        assertEquals(newEntity.getHabitaciones(), entity.getHabitaciones());
        assertEquals(newEntity.getDireccion(), entity.getDireccion());
        assertEquals(newEntity.getNombre(), entity.getId());
    }

    @Test
    void testCreateHotelesInvalido() throws IllegalOperationException {
        HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
        newEntity.setId(121L);
        HotelEntity result = hotelService.createHoteles(newEntity);

        assertThrows(IllegalOperationException.class, () -> {
            hotelService.createHoteles(newEntity);
        });
    }

}