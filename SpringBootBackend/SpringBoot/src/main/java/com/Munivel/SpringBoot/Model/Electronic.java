package com.Munivel.SpringBoot.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Electronic {
    @jakarta.persistence.Id
    private int Id;// primary key
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private boolean available;
    private int quantity;
    private String imageName;
    private  String imageType;

    @Lob
    private byte[] imageDate;
}
