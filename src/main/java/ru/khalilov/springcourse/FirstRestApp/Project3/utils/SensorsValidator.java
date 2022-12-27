package ru.khalilov.springcourse.FirstRestApp.Project3.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.khalilov.springcourse.FirstRestApp.Project3.models.Sensors;
import ru.khalilov.springcourse.FirstRestApp.Project3.services.SensorsServiceInterface;

@Component
public class SensorsValidator implements Validator {

    private final SensorsServiceInterface sensorsService;

    @Autowired
    public SensorsValidator(SensorsServiceInterface sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensors.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensors sensors = (Sensors) o;
        if (sensorsService.findByName(sensors.getName()).isPresent())
            errors.rejectValue("name", "a sensor with that name already exists");
    }
}
