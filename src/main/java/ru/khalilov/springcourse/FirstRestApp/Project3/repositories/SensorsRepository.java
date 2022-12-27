package ru.khalilov.springcourse.FirstRestApp.Project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khalilov.springcourse.FirstRestApp.Project3.models.Sensors;

import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Repository
public interface SensorsRepository extends JpaRepository<Sensors, Integer> {
   Optional <Sensors> findByName(String name);
}
