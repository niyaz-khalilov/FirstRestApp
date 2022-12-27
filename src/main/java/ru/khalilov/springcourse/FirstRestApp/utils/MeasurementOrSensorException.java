package ru.khalilov.springcourse.FirstRestApp.utils;

public class MeasurementOrSensorException extends RuntimeException {
    public MeasurementOrSensorException(String errorMessage) {
        super(errorMessage);
    }
}
