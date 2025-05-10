package com.Munivel.SpringBoot.Service;

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
