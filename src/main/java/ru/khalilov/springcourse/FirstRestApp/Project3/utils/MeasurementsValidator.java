package ru.khalilov.springcourse.FirstRestApp.Project3.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.khalilov.springcourse.FirstRestApp.Project3.models.Measurements;
import ru.khalilov.springcourse.FirstRestApp.Project3.services.SensorsServiceInterface;

@Component
public class MeasurementsValidator implements Validator {

    private final SensorsServiceInterface sensorsService;

    @Autowired
    public MeasurementsValidator(SensorsServiceInterface sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurements.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurements measurement = (Measurements) o;

        if (measurement.getSensor() == null) {
            return;
        }

        if (sensorsService.findByName(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "There is no sensor with this name");
    }
}
