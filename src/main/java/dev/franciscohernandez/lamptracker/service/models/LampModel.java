package dev.franciscohernandez.lamptracker.service.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Document("lamps")
public class LampModel {
    
    @Id
    private String id;
    
    @Nullable
    private String barcode;

    @Nullable
    private String model;
    
    @Nullable
    private Date installedDate;
    
    @Nullable
    private Date lastMaintenanceDate;

    private Integer department;
    private Integer district;
    private String status;

    @Nullable
    private String type;
   
    @Nullable
    private String power;
    
    @Nullable
    private String brand;
    
    @Nullable
    private String provider;
    
    private Integer chageCounter;
    private List<Float> coordinates;
}
