package ru.khalilov.springcourse.FirstRestApp.Project3.services;

import ru.khalilov.springcourse.FirstRestApp.Project3.models.Measurements;

import java.util.List;

public interface MeasurementsServiceInterface {
    List<Measurements> findAll();

    void save(Measurements measurements);

}
