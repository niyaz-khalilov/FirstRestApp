package ru.khalilov.springcourse.FirstRestApp.Project3.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Sensors")
public class Sensors {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "name should have 3-30 characters")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurements> measurementsList;
}
