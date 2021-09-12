package com.geekymon2.carmarketplace.carinfoservice.controller;

import com.geekymon2.carmarketplace.carinfoservice.entities.CarMake;
import com.geekymon2.carmarketplace.carinfoservice.entities.CarModel;
import com.geekymon2.carmarketplace.carinfoservice.entities.CarModelType;
import com.geekymon2.carmarketplace.carinfoservice.models.CarMakeDto;
import com.geekymon2.carmarketplace.carinfoservice.models.CarModelDto;
import com.geekymon2.carmarketplace.carinfoservice.models.StatusDto;
import com.geekymon2.carmarketplace.carinfoservice.serviceimpl.CarInfoServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/car")
public class CarInfoController {

    private final CarInfoServiceImpl service;
    private final ModelMapper mapper;
    private final String UNKNOWN_LABEL = "unknown";

    public CarInfoController(CarInfoServiceImpl service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/status")
    public StatusDto getStatus() {
        String hostname = "";
        String environment = "";
        String version = "0.0.0";

        try {
            hostname = java.net.InetAddress.getLocalHost().getHostName();
            environment = System.getenv("ENVIRONMENT");
            version = Files.readString(Paths.get("/version.properties")).split("=")[1];
        }
        catch (UnknownHostException uhx) {
            hostname = UNKNOWN_LABEL;
            log.error(String.format("Error getting hostname: %s", uhx));
        }
        catch (IOException iox) {
            version = UNKNOWN_LABEL;
            log.error(String.format("Error getting version: %s", iox));
        }

        return new StatusDto(environment, version, hostname);
    }

    @GetMapping(value = "/makes")
    public List<CarMakeDto> getCarMakes() {
        return service.getAllCarMakes().stream().map(this::makeToDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/makes/{id}")
    public CarMakeDto getCarMakeById(@PathVariable("id") long id) {
        return makeToDto(service.getCarMakeById(id));
    }

    @GetMapping(value = "/makes/count")
    public ResponseEntity<Long> getCarMakesCount() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCarMakesCount());
    }

    @GetMapping(value = "/models")
    public List<CarModelDto> getCarModelsByMakeAndType(String make, String type) {
        return service.getCarModels(make, type).stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/models/{id}")
    public CarModelDto getCarModelById(@PathVariable("id") long id) {
        return modelToDto(service.getCarModelById(id));
    }

    @GetMapping(value = "/models/count")
    public ResponseEntity<Long> getCarModelsCount() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCarModelsCount());
    }

    @GetMapping(value = "/types")
    public List<CarModelType> getCarModelTypes() {
        return service.getCarModelTypes();
    }

    private CarMakeDto makeToDto(CarMake make) {
        return mapper.map(make, CarMakeDto.class);
    }

    private CarModelDto modelToDto(CarModel model) {
        CarModelDto dto = mapper.map(model, CarModelDto.class);
        dto.setId(model.getId());
        dto.setType(model.getType().toString());
        return dto;
    }    
}
