
package com.Munivel.SpringBoot.Controller;

import com.Munivel.SpringBoot.Model.Laptopmodel;
import com.Munivel.SpringBoot.Model.Product;
import com.Munivel.SpringBoot.Model.SamsungModel;
import com.Munivel.SpringBoot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
@RestController
@CrossOrigin

@RequestMapping("/api")
public class ProductController {// with the help of hte responentity we can also send the status

    @Autowired
    private ProductService service;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }


    @GetMapping("/product_list/{id}")
    public ResponseEntity<?> getProductss(@PathVariable int id){
        switch (id){
            case 1:
                return new ResponseEntity<>(service.getSamsungProducts(),HttpStatus.OK);
            case 2:
                return new ResponseEntity<>(service.getLaptopProducts(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/Mobile/{id}")
    public ResponseEntity<SamsungModel> getProduct(@PathVariable int id){
      SamsungModel samsungModel=service.getSamsungModelByById(id);

        if(samsungModel != null)
            return new ResponseEntity<>(samsungModel, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/Laptop/{id}")
    public ResponseEntity<Laptopmodel> getProducts(@PathVariable int id){
    Laptopmodel laptop=service.getLaptopsById(id);
        if(laptop != null)
            return new ResponseEntity<>(laptop, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){

        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageDate();
        System.out.println(Arrays.toString(imageFile));
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = service.searchProducts(keyword);
        System.out.println("searching with " + keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


//
//    @DeleteMapping("/product/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable int id){
//        Product product = service.getProductById(id);
//        if(product != null) {
//            service.deleteProduct(id);
//            return new ResponseEntity<>("Deleted", HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
//
//    }




//    @GetMapping("/products/search")
//    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
//        List<Product> products = service.searchProducts(keyword);
//        System.out.println("searching with " + keyword);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }


}
