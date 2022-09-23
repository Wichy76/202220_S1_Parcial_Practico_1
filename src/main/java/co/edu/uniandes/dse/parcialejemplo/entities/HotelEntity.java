package co.edu.uniandes.dse.parcialejemplo.entities;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class HotelEntity extends BaseEntity{
    private String nombre;
    private String direccion;
    private int estrellas;

    @PodamExclude
    @OneToMany(mappedBy = "hotel")
    private List<HabitacionEntity> habitaciones = new ArrayList<>();
}
