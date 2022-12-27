package ru.khalilov.springcourse.FirstRestApp.Project3Client.DTO;


import java.util.List;

public class MeasurementsResponseClient {
    List<MeasurementsDTOClient> measurements;

    public MeasurementsResponseClient(List<MeasurementsDTOClient> measurements) {this.measurements = measurements;
    }

    public List<MeasurementsDTOClient> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementsDTOClient> measurements) {
        this.measurements = measurements;
    }
}
