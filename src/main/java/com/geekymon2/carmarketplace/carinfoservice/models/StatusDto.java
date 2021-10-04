package com.geekymon2.carmarketplace.carinfoservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    String environment;
    String version;
    String hostname;  
    String description;  
}
