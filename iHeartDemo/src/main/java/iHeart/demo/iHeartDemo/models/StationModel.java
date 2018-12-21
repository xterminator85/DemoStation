package iHeart.demo.iHeartDemo.models;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
public class StationModel implements Serializable {

    @Id
    @GeneratedValue
    private Long stationId;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ColumnDefault(value = "false")
    private boolean hdEnabled;

    @Column(nullable = false)
    private String callSign;



}
