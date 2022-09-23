package co.edu.uniandes.dse.parcialejemplo.repositories;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
}
