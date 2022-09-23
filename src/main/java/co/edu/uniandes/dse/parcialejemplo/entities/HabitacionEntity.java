package co.edu.uniandes.dse.parcialejemplo.entities;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class HabitacionEntity extends BaseEntity{
    private int numIdentificacion;
    private int numPersonas;
    private int numCamas;
    private int numBanios;

    @PodamExclude
    @ManyToOne
    private HotelEntity hotel;
}
