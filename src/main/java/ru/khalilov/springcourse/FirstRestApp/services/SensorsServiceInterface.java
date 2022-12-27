package ru.khalilov.springcourse.FirstRestApp.services;

import ru.khalilov.springcourse.FirstRestApp.models.Sensors;

import java.util.Optional;

public interface SensorsServiceInterface {

    Optional<Sensors> findByName(String name);
    void save (Sensors sensor);
}
