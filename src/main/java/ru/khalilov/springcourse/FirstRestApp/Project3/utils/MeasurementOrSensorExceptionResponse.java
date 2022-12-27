package ru.khalilov.springcourse.FirstRestApp.Project3.utils;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MeasurementOrSensorExceptionResponse {
    String message;
    LocalDateTime timestamp;

    public MeasurementOrSensorExceptionResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
