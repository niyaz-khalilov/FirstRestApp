package ru.khalilov.springcourse.FirstRestApp.DTO;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MeasurementsDTO {

    @NotNull
    @Min(value = -100, message = "temperature should be bigger then -100")
    @Max(value = 100, message = "temperature should be lower then 100")
    private double value;

    @NotNull(message = "raining should not be empty")
    private boolean raining;

    @NotNull(message = "measurement should have sensor")
    private SensorsDTO sensor;
}
