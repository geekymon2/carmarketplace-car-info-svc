package com.geekymon2.carmarketplace.carinfoservice.entities;

import com.geekymon2.carmarketplace.core.entities.CarMakeName;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "make")
public class CarMake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @Enumerated(EnumType.STRING)
    private CarMakeName name;
    private String country;
    @OneToMany(mappedBy = "make")
    private Set<CarModel> models;


    public CarMake(Long id, CarMakeName name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}
