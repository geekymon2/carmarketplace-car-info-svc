package com.geekymon2.carmarketplace.carinfoservice.repository;

import com.geekymon2.carmarketplace.core.CarMakeName;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    Long count();
    List<T> findByName(CarMakeName name);
    T findOneByName(CarMakeName name);
}
