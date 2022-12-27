package ru.khalilov.springcourse.FirstRestApp.Project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khalilov.springcourse.FirstRestApp.Project3.models.Measurements;

/**
 * @author Neil Alishev
 */
@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

}
