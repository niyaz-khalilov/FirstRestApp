package ru.khalilov.springcourse.FirstRestApp.Project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khalilov.springcourse.FirstRestApp.Project3.models.Sensors;
import ru.khalilov.springcourse.FirstRestApp.Project3.repositories.SensorsRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService implements SensorsServiceInterface {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional
    @Override
    public void save(Sensors sensor) {
        sensorsRepository.save(sensor);
    }

    @Override
    public Optional<Sensors> findByName(String name) {
        return sensorsRepository.findByName(name);
    }


}
