package com.geekymon2.carmarketplace.carinfoservice.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.geekymon2.carmarketplace.carinfoservice.entities.CarMake;
import com.geekymon2.carmarketplace.carinfoservice.entities.CarModel;
import com.geekymon2.carmarketplace.carinfoservice.entities.CarModelType;
import com.geekymon2.carmarketplace.carinfoservice.repository.CarMakeRepository;
import com.geekymon2.carmarketplace.carinfoservice.repository.CarModelRepository;
import com.geekymon2.carmarketplace.carinfoservice.service.CarInfoService;
import com.geekymon2.carmarketplace.carinfoservice.validation.CarInfoValidator;
import com.geekymon2.carmarketplace.core.entities.CarMakeName;
import com.geekymon2.carmarketplace.core.exception.RecordNotFoundException;
import com.geekymon2.carmarketplace.core.exception.InvalidParameterException;

import org.springframework.stereotype.Service;

@Service
public class CarInfoServiceImpl implements CarInfoService {

    private final CarMakeRepository carMakeRepository;
    private final CarModelRepository carModelRepository;
    private final CarInfoValidator validator;

    public CarInfoServiceImpl(CarMakeRepository carMakeRepository, CarModelRepository carModelRepository, CarInfoValidator validator) {
        this.carMakeRepository = carMakeRepository;
        this.carModelRepository = carModelRepository;
        this.validator = validator;
    }

    @Override
    public List<CarMake> getAllCarMakes() {
        List<CarMake> makes = new ArrayList<>();
        carMakeRepository.findAll().forEach(makes::add);
        return makes;
    }

    @Override
    public CarMake getCarMakeById(long id) {
        Optional<CarMake> make;
        make  = carMakeRepository.findById(id);

        if (make.isEmpty()) {
            throw new RecordNotFoundException(String.format("Make with id '%d' not found", id));
        }

        return make.get();
    }

    @Override
    public List<CarModel> getCarModels(String makeName, String typeName) {
        CarMake make = null;
        CarMakeName name = validator.validateMakeName(makeName);
        CarModelType type = validator.validateType(typeName);

        //after validation if both make name and type are null then return all.
        if (name == null && type == null) {
            List<CarModel> models = new ArrayList<>();
            carModelRepository.findAll().forEach(models::add);
            return models;
        }
        else {
            if (name != null) {
                make = carMakeRepository.findOneByName(name);
                if (make == null) {
                    throw new InvalidParameterException(String.format("Invalid make '%s'", makeName));
                }
            }

            return carModelRepository.findByMakeIdAndType((make != null) ? make.getId() : null, type);          
        }
    }

    @Override
    public CarModel getCarModelById(long id) {
        Optional<CarModel> model;
        model  = carModelRepository.findById(id);

        if (model.isEmpty()) {
            throw new RecordNotFoundException(String.format("Make with id '%d' not found", id));
        }

        return model.get();
    }

    @Override
    public long getCarMakesCount() {
        return carMakeRepository.count();
    }

    @Override
    public long getCarModelsCount() {
        return carModelRepository.count();
    }

    @Override
    public List<CarModelType> getCarModelTypes() {
        return Arrays.stream(CarModelType.values()).collect(Collectors.toList());
    }
}
