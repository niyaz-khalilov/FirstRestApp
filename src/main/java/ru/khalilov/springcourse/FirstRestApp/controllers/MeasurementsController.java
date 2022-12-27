package ru.khalilov.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.khalilov.springcourse.FirstRestApp.DTO.MeasurementsDTO;
import ru.khalilov.springcourse.FirstRestApp.DTO.MeasurementsResponse;
import ru.khalilov.springcourse.FirstRestApp.models.Measurements;
import ru.khalilov.springcourse.FirstRestApp.services.MeasurementsService;
import ru.khalilov.springcourse.FirstRestApp.services.MeasurementsServiceInterface;
import ru.khalilov.springcourse.FirstRestApp.utils.ErrorsUtil;
import ru.khalilov.springcourse.FirstRestApp.utils.MeasurementOrSensorException;
import ru.khalilov.springcourse.FirstRestApp.utils.MeasurementOrSensorExceptionResponse;
import ru.khalilov.springcourse.FirstRestApp.utils.MeasurementsValidator;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @author Neil Alishev
 */
@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsServiceInterface measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementsValidator measurementsValidator;

    @Autowired
    public MeasurementsController(MeasurementsServiceInterface measurementsService, ModelMapper modelMapper, MeasurementsValidator measurementsValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementsValidator = measurementsValidator;
    }

    @GetMapping()
    public MeasurementsResponse findAll() {
        return new MeasurementsResponse(measurementsService.findAll().stream().map(this::convertToMeasurementsDTO).
                collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public long findCountOfRainyDays() {
        return measurementsService.findAll().stream().filter(Measurements::isRaining).count();
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> measurementRegistration(@RequestBody @Valid MeasurementsDTO measurementsDTO,
                                                              BindingResult bindingResult) {
        Measurements measurements = convertToMeasurement(measurementsDTO);
        measurementsValidator.validate(measurements, bindingResult);
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);
        measurementsService.save(measurements);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurements convertToMeasurement(MeasurementsDTO measurementsDTO) {
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    private MeasurementsDTO convertToMeasurementsDTO(Measurements measurements) {
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity <MeasurementOrSensorExceptionResponse> exceptionHandler (MeasurementOrSensorException e) {
        MeasurementOrSensorExceptionResponse response = new MeasurementOrSensorExceptionResponse(e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
