package ru.khalilov.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.khalilov.springcourse.FirstRestApp.DTO.SensorsDTO;
import ru.khalilov.springcourse.FirstRestApp.models.Sensors;

import ru.khalilov.springcourse.FirstRestApp.services.SensorsServiceInterface;
import ru.khalilov.springcourse.FirstRestApp.utils.ErrorsUtil;
import ru.khalilov.springcourse.FirstRestApp.utils.MeasurementOrSensorException;
import ru.khalilov.springcourse.FirstRestApp.utils.MeasurementOrSensorExceptionResponse;
import ru.khalilov.springcourse.FirstRestApp.utils.SensorsValidator;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsServiceInterface sensorsService;
    private final ModelMapper modelMapper;
    private final SensorsValidator sensorsValidator;

    @Autowired
    public SensorsController(SensorsServiceInterface sensorsService, ModelMapper modelMapper, SensorsValidator sensorsValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorsValidator = sensorsValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity <HttpStatus> registration(@RequestBody @Valid SensorsDTO sensorsDTO,
                                                    BindingResult bindingResult) {
        Sensors sensor = convertToSensor(sensorsDTO);
        sensorsValidator.validate(sensor, bindingResult);

        if(bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        sensorsService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensors convertToSensor(SensorsDTO sensorsDTO) {
        return modelMapper.map(sensorsDTO, Sensors.class);
    }

    @ExceptionHandler
    private ResponseEntity <MeasurementOrSensorExceptionResponse> exceptionHandler (MeasurementOrSensorException e) {
        MeasurementOrSensorExceptionResponse response = new MeasurementOrSensorExceptionResponse(e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
