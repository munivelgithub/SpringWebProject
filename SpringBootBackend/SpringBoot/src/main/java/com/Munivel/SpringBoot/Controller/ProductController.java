
//@RestController
//@CrossOrigin
//@RequestMapping("/api")
//public class ProductController {
//    @Autowired
//    private ProductService service;
//    @RequestMapping("/")
//    public String greet(){
//        return "hii";
//    }
//    //returning an data as well as status
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> productList(){ // bascially we are sending an list of product but we can send asResponseEntity
//        return new ResponseEntity<>(service.getallproducts(), HttpStatus.OK);
//    }
//    @GetMapping("/product/{id}")
//    public ResponseEntity<Product> getProduct(@PathVariable int id){
//
//        Product product=service.getProduct(id);
//        if(product != null)
//            return new ResponseEntity<>(product,HttpStatus.OK);
//        else
//          return   new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        // in this code the prodct from the client and it is fetched from the db and checking if the product exist it will send the product
//        // if it is not exit we have an constructoru not found status will be sended
//    }
//
//
//
//
//
//    //? meands any type of data not sure might return an data or exception
//    // @requestbody accept whole object from json
//    // @requestpart help to dive and gettting an splitter one part and two part
//    // this method for adding an product
//
//    @PostMapping("/product")// if it is  normal product without an any image you can use the RequestBody if it is an image
//  public ResponseEntity<?> addproducts(@RequestPart  Product  product,
//                                      @RequestPart MultipartFile imagefile){ // we are geeting an product object if also give an image means
//        try {
//            Product product1 =service.addProduct(product, imagefile);
//            return new ResponseEntity<>(product1,HttpStatus.CREATED);// need to check actually getting it stored
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//    @GetMapping("/product/{productId}/image")
//    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
//        Product product=service.getProduct(productId);
//        //getting an image
//        byte[] imagefile=product.getImageDate();
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf(product.getImageType())).body(imagefile);
//    }
//    @PutMapping("/product/{id}")
//    public ResponseEntity<String> update(@PathVariable int id,@RequestPart  Product  product,
//                                         @RequestPart MultipartFile imagefile){
//        Product product1= null;
//        try {
//            product1 = service.updateproduct(id,product,imagefile);
//        } catch (IOException e) {
//            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
//        }
//        if(product1 != null){
//            return new ResponseEntity<>("updated",HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
//        }
//
//    }
//    @DeleteMapping("/product/{id}")
//    public ResponseEntity<String> delect(@PathVariable int id){
//        Product product=service.getProduct(id);// check first the product is present or not
//        if(product != null){
//            service.delete(id);
//            return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
//        }
//
//    }
//    @GetMapping("/products/search")// use requestparam to get an string data to map
//    public  ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
//        List<Product> products=service.searchProduct(keyword);
//        return  new ResponseEntity<>(products,HttpStatus.OK);
//
//    }
//}

package com.Munivel.SpringBoot.Controller;

import com.Munivel.SpringBoot.Model.Electronic;
import com.Munivel.SpringBoot.Model.Laptopmodel;
import com.Munivel.SpringBoot.Model.Product;
import com.Munivel.SpringBoot.Model.SamsungModel;
import com.Munivel.SpringBoot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


//    @GetMapping("/api/product_list/1")
//    public ResponseEntity<List<SamsungModel>> getallSamsumgModel(){
//        return new ResponseEntity<>(service.getSamsungProducts(),HttpStatus.OK);
//    }
    @GetMapping("/product_list/{id}")
    public ResponseEntity<?> getProductss(@PathVariable int id){
        switch (id){
            case 1:
                return new ResponseEntity<>(service.getSamsungProducts(),HttpStatus.OK);
            case 2:
                return new ResponseEntity<>(service.getLaptopProducts(),HttpStatus.OK);
            case 3:
                return new ResponseEntity<>(service.getElectronicProducts(),HttpStatus.OK);
        }

//        SamsungModel products = service.getSamsungModelByById(id);
//
//        if(products != null)
//            return new ResponseEntity<>(products, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            ;
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
@GetMapping("/product/Electronic/{id}")
  public ResponseEntity<Electronic> getElectronicProduct(@PathVariable int id){
        Electronic electronic=service.getElectonicModelByById(id);
        if(electronic !=null){
            return new ResponseEntity<>(electronic,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

@GetMapping("/product/{id}/image")
public ResponseEntity<byte[]> getProductImage(@PathVariable int id) {
    Product product = service.getProductById(id);
    if (product != null && product.getImageDate() != null) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(product.getImageType()));
        return new ResponseEntity<>(product.getImageDate(), headers, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

//    @GetMapping("/product/{productId}/image")
//    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
//        Product product = service.getProductById(productId);
//        byte[] imageFile = product.getImageDate();
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf(product.getImageType()))
//                .body(imageFile);
//
//    }

    @CrossOrigin(origins = "http://localhost:5173") // ✅ allow React frontend
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = service.getProductById(productId);

        if (product == null) {
            return ResponseEntity.notFound().build(); // 404 if not found
        }

        byte[] imageFile = product.getImageDate();
        if (imageFile == null || imageFile.length == 0) {
            return ResponseEntity.noContent().build(); // 204 if no image
        }

        MediaType mediaType;
        try {
            mediaType = MediaType.valueOf(product.getImageType());
        } catch (Exception e) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM; // fallback
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(imageFile);
    }
//
//    @PutMapping("/product/{id}")
//    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
//                                                @RequestPart MultipartFile imageFile){
//        Product product1 = null;
//        try {
//            product1 = service.updateProduct(id, product, imageFile);
//        } catch (IOException e) {
//            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
//        }
//        if(product1 != null)
//            return new ResponseEntity<>("Updated", HttpStatus.OK);
//        else
//            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
//    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

    }




    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = service.searchProducts(keyword);
        System.out.println("searching with " + keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
