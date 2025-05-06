package com.Munivel.SpringBoot.Service;

import com.Munivel.SpringBoot.Model.Electronic;
import com.Munivel.SpringBoot.Model.Laptopmodel;
import com.Munivel.SpringBoot.Model.Product;
import com.Munivel.SpringBoot.Model.SamsungModel;
import com.Munivel.SpringBoot.repo.Laptop;
import com.Munivel.SpringBoot.repo.ProductInterface;
import com.Munivel.SpringBoot.repo.SamsungModelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
//
//@Service
//public class ProductService {
//    @Autowired
//    ProductInterface productInterface;
//
//    public List<Product> getallproducts() {
//        return productInterface.findAll();
//    }
//
//// option return means that the particular id there is no any product present in the database it will return nulll
//
//
//
//
//    public Product getProduct(int id) {
//        return productInterface.findById(id).orElse(new Product());
//    }
//
//
//
//    public Product addProduct(Product product, MultipartFile imagefile) throws IOException {
//        // we get an image and convert into the byte formate to store in the data base also we need set an image name type and an image
//        product.setImageName(imagefile.getOriginalFilename());
//        product.setImageType(imagefile.getContentType());
//        product.setImageDate(imagefile.getBytes());// to convert an image to byte we have an method called getBytes();
//        return  productInterface.save(product);// in this only we are sending an product not an image
//    }
//
//
//    public Product updateproduct(int id, Product product, MultipartFile imagefile) throws IOException {
//        product.setImageDate(imagefile.getBytes());
//        product.setImageName(imagefile.getOriginalFilename());
//        product.setImageType(imagefile.getContentType());
//        return productInterface.save(product);
//    }
//
//    public void delete(int id) {
//        productInterface.deleteById(id);
//    }
//
//    public List<Product> searchProduct(String keyword) {
//        return productInterface.searchProduct(keyword);
//    }
//}

@Service
public class ProductService {
    @Autowired
private Laptop laptop;
    @Autowired
    private ProductInterface repo;
    @Autowired
    private SamsungModelInterface samsungModelInterface;


    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public SamsungModel getSamsungModelByById(int id) {
        return samsungModelInterface.findById(id).orElse(null);
    }


    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes()); // store as byte[]
        return repo.save(product);
    }


    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageDate(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }



    public List<SamsungModel> getSamsungProducts() {
          return samsungModelInterface.findAll();
    }

    public List<Laptopmodel> getLaptopProducts(){
        return laptop.findAll();
    }


    public Laptopmodel getLaptopsById(int id) {
        return laptop.findById(id).orElse(null);
    }

    public List<Product> searchProducts(String keyword) {
        return  repo.searchProduct(keyword);
    }


}
