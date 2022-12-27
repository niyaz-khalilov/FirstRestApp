package ru.khalilov.springcourse.FirstRestApp.Project3Client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.khalilov.springcourse.FirstRestApp.Project3Client.DTO.MeasurementsDTOClient;
import ru.khalilov.springcourse.FirstRestApp.Project3Client.DTO.MeasurementsResponseClient;


import java.util.*;
import java.util.stream.Collectors;

public class RequestClass {
    public static void main(String[] args) {
        String name = "T2000";
        registerSensor(name);
        int minTemperature = -100;
        int maxTemperature = 100;
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            double temperature = minTemperature + random.nextDouble() * (maxTemperature - minTemperature);
            boolean rainy = random.nextBoolean();
            registerMeasurement(temperature, rainy, name);
        }
    }

    private static void registerMeasurement(double temperature, boolean rainy, String name) {
        final String URL = "http://localhost:8080/measurements/add";

        Map<String, Object> measurementJSON = new HashMap<>();
        measurementJSON.put("value", temperature);
        measurementJSON.put("raining", rainy);
        measurementJSON.put("sensor", Map.of("name", name));
        doPostRequest(URL, measurementJSON);
    }

    private static void registerSensor(String name) {
        final String URL = "http://localhost:8080/sensors/registration";
        Map<String, Object> sensorJSON = new HashMap<>();
        sensorJSON.put("name", name);
        doPostRequest(URL, sensorJSON);
    }

    private static void doPostRequest(String url, Map<String, Object> sensorJSON) {
        RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(sensorJSON, headers);
        try {
            HttpStatus httpStatus = restTemplate.postForObject(url, entity, HttpStatus.class);
            System.out.println("Запрос успешно выполнен" + httpStatus.toString());
        } catch (HttpClientErrorException e) {
            System.out.println("Запрос не прошел" + e.getMessage());
        }
    }

    private static void doGetRequest() {
        final String URL = "http://localhost:8080/measurements";
        RestTemplate restTemplate = new RestTemplate();
        MeasurementsResponseClient forObject = restTemplate.getForObject(URL, MeasurementsResponseClient.class);
        List<Double> collect = forObject.getMeasurements().stream().map(MeasurementsDTOClient::getValue).
                collect(Collectors.toList());
        if(collect.size() == 0)
            System.out.println("Нет показаний датчика");
        for (Double aDouble : collect) {
            System.out.println(aDouble);
        }
    }
}
