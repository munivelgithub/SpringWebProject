package com.Munivel.SpringBoot.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    private int Id;// primary key
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private String category;
    //2024-01-14T18:30:00.000+00:00 in the front end the date will be displayed like that but it should not be
    // wthe the help of the jackson library we can do it
    //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-mm-yyyy")
    private Date releaseDate;
    private boolean available;
    private int quantity;
//    // image data
    private String imageName;
    private  String imageType;
//    // how you will get an image
//    // you can store an image on any cloud storage then get an link of that image
//
//    // it is an simple way
//    // when ever you use an array  to store in the database should use an large object
    @Lob
   // @Column(columnDefinition = "LONGBLOB")
    private byte[] imageDate;

}

