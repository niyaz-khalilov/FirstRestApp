package ru.khalilov.springcourse.FirstRestApp.services;

import ru.khalilov.springcourse.FirstRestApp.models.Measurements;

import java.util.List;

public interface MeasurementsServiceInterface {
    List<Measurements> findAll();

    void save(Measurements measurements);

}
