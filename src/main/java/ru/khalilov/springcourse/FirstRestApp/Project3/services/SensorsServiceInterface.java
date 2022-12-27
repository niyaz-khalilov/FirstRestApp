package ru.khalilov.springcourse.FirstRestApp.Project3.services;

import ru.khalilov.springcourse.FirstRestApp.Project3.models.Sensors;

import java.util.Optional;

public interface SensorsServiceInterface {

    Optional<Sensors> findByName(String name);
    void save (Sensors sensor);
}
