package ru.khalilov.springcourse.FirstRestApp.Project3.DTO;


import java.util.List;

public class MeasurementsResponse {
    List<MeasurementsDTO> measurements;

    public MeasurementsResponse(List<MeasurementsDTO> measurements) {this.measurements = measurements;
    }

    public List<MeasurementsDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementsDTO> measurements) {
        this.measurements = measurements;
    }
}
