package ru.khalilov.springcourse.FirstRestApp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Measurements")
public class Measurements {
    @Id
    @Column(name = "measurement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull
    @Min(value = -100, message = "temperature should be bigger then -100")
    @Max(value = 100, message = "temperature should be lower then 100")
    private double value;

    @Column(name = "raining")
    @NotNull(message = "raining should not be empty")
    private boolean raining;

    @Column(name = "measurement_date_time")
    @NotNull(message = "measurementDateTime should not be empty")
    private LocalDateTime measurementDateTime;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull(message = "measurement should have sensor")
    private Sensors sensor;
}
