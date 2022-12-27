package ru.khalilov.springcourse.FirstRestApp.DTO;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SensorsDTO {

    @Size(min = 3, max = 30, message = "name should have 3-30 characters")
    private String name;
}
