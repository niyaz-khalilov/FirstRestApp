package ru.khalilov.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khalilov.springcourse.FirstRestApp.models.Measurements;

/**
 * @author Neil Alishev
 */
@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

}
