package co.edu.uniandes.dse.parcialejemplo.services;


import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
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
public class HabitacionServiceTest {
    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();


    /**
     * Prueba para crear un hotel
     */
    @Test
    void testCreateHoteles() throws IllegalOperationException {
        HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);
        HabitacionEntity result = habitacionService.createHabitacion(newEntity);
        assertNotNull(result);
        HabitacionEntity entity = entityManager.find(HabitacionEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getNumCamas(), entity.getNumCamas());
        assertEquals(newEntity.getHotel(), entity.getHotel());
        assertEquals(newEntity.getNumPersonas(), entity.getNumPersonas());
        assertEquals(newEntity.getNumBanios(), entity.getNumBanios());
        assertEquals(newEntity.getNumIdentificacion(), entity.getNumIdentificacion());
    }

    @Test
    void testCreateHotelesInvalido() throws IllegalOperationException {
        HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
        newEntity.setId(121L);
        HotelEntity result = habitacionService.createHoteles(newEntity);

        assertThrows(IllegalOperationException.class, () -> {
            habitacionService.createHabitacion(newEntity);
        });
    }

}
