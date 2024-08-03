package com.geekymon2.carmarketplace.carinfoservice.controller;

import com.geekymon2.carmarketplace.carinfoservice.entities.CarMake;
import com.geekymon2.carmarketplace.carinfoservice.entities.CarModel;
import com.geekymon2.carmarketplace.carinfoservice.entities.CarModelType;
import com.geekymon2.carmarketplace.carinfoservice.models.CarMakeDto;
import com.geekymon2.carmarketplace.carinfoservice.models.CarModelDto;
import com.geekymon2.carmarketplace.carinfoservice.serviceimpl.CarInfoServiceImpl;
import com.geekymon2.carmarketplace.core.common.ApiStatus;
import com.geekymon2.carmarketplace.core.models.StatusDto;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/car")
public class CarInfoController {

    private final CarInfoServiceImpl service;
    private final ModelMapper mapper;

    public CarInfoController(CarInfoServiceImpl service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Get the health status of this API", description = "Returns environment, API version, hostname and description")
    @GetMapping(value = "/status")
    public StatusDto getStatus() {
        return new ApiStatus().getStatus();
    }

    @Operation(summary = "Get all car makes from different manufactures", description = "Returns a list of car makes")
    @GetMapping(value = "/makes")
    public List<CarMakeDto> getCarMakes() {
        return service.getAllCarMakes().stream().map(this::makeToDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get car make by id", description = "Returns a car make")
    @GetMapping(value = "/makes/{id}")
    public CarMakeDto getCarMakeById(@PathVariable("id") long id) {
        return makeToDto(service.getCarMakeById(id));
    }

    @Operation(summary = "Get the count of car makes", description = "Returns the count of car makes")
    @GetMapping(value = "/makes/count")
    public ResponseEntity<Long> getCarMakesCount() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCarMakesCount());
    }

    @Operation(summary = "Get all car models for a particular make", description = "Returns a list of car models for a particular make")
    @GetMapping(value = "/models")
    public List<CarModelDto> getCarModelsByMakeAndType(@RequestParam(name = "make", required = true) String make, @RequestParam(name = "type") String type) {
        return service.getCarModels(make, type).stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get the car model by id", description = "Returns a car model")
    @GetMapping(value = "/models/{id}")
    public CarModelDto getCarModelById(@PathVariable("id") long id) {
        return modelToDto(service.getCarModelById(id));
    }

    @Operation(summary = "Get the count of car models", description = "Returns the count of all car models")
    @GetMapping(value = "/models/count")
    public ResponseEntity<Long> getCarModelsCount() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCarModelsCount());
    }

    @Operation(summary = "Get the different body types of cars", description = "Returns the different body types. e.g. SEDAN, SUV etc.")
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
