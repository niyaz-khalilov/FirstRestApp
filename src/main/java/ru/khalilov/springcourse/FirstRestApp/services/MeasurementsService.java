package ru.khalilov.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khalilov.springcourse.FirstRestApp.models.Measurements;
import ru.khalilov.springcourse.FirstRestApp.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Service
@Transactional(readOnly = true)
public class MeasurementsService implements MeasurementsServiceInterface {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsServiceInterface sensorService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsServiceInterface serviceInterface) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = serviceInterface;
    }

    public List<Measurements> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void save(Measurements measurements) {
        enrichMeasurement(measurements);
        measurementsRepository.save(measurements);
    }

    private void enrichMeasurement(Measurements measurements) {
        measurements.setMeasurementDateTime(LocalDateTime.now());
        measurements.setSensor(sensorService.findByName(measurements.getSensor().getName()).get());
    }
}
