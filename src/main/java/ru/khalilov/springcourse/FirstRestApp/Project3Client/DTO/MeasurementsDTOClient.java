package ru.khalilov.springcourse.FirstRestApp.Project3Client.DTO;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MeasurementsDTOClient {

    private double value;
    private boolean raining;
    private SensorsDTOClient sensor;
}
