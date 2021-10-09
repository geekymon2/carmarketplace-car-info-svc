package com.geekymon2.carmarketplace.carinfoservice.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.geekymon2.carmarketplace.core.CarMakeName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
