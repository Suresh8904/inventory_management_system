package uk.lset.controller;

import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uk.lset.dto.ProductDTO;
import uk.lset.entities.Product;
import uk.lset.service.ProductService;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Add new Product

    @PostMapping(path = "addNewProduct")
    public ResponseEntity<?> addNewProduct(@RequestBody Product product){
        try {
            return ResponseEntity.ok(productService.addNewProduct(product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }


    //Request all products

    @GetMapping(path = "all")
    public @ResponseBody List<Product> getAllProducts(){
        Product product = null;
        return productService.getAllProducts(product);
    }



    //Get all products sorted
    //localhost:8001/products/sort?sortBy=?&ascending=?
    @GetMapping(path = "/sort")
    public Page<Product> sortProducts(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam (required = false) String category,
                                      @RequestParam(defaultValue = "name") String sortBy,
                                      @RequestParam(defaultValue = "true") boolean ascending) {

        Sort sort = ascending? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return productService.getAllProductsSorted(page, size, sort, category);
    }


    //get products by productId
    @GetMapping(path = "/{id}")
    public Optional<Product> productsById(@PathVariable String id){
        Optional<Product> optionalProduct = productService.getProductByProductId(id);
        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return productService.getProductByProductId(id);
    }

    //Update product
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable String id) {
        try {
            Optional<Product> productOptional = productService.getProductByProductId(id);
            if (productOptional.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
            }
            Product updatedProduct = null;
            Product productToUpdate = productOptional.get();
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setProductDescription(product.getProductDescription());
            productToUpdate.setProductQuantity(product.getProductQuantity());
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setInventoryId(product.getInventoryId());
            updatedProduct = productService.updateProduct(productToUpdate);
            return ResponseEntity.ok(updatedProduct);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product. " + e.getMessage());
        }
    }



    //Delete product by supplierCode
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id){
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product. " + e.getMessage());
        }
    }

    @GetMapping(path = "/stocks/{id}")
    public @ResponseBody ProductDTO getProductStocks(@PathVariable String id){
        return productService.getProductStock(id);
    }

    @PutMapping(path = "/updateStock/{productId}")
    public ResponseEntity<Product> updateStock(@PathVariable String productId, @RequestParam int newQuantity){
        Product updateProductQuantity = productService.updateStock(productId, newQuantity);
        return ResponseEntity.ok(updateProductQuantity);
    }

    @PostMapping(path = "addStock/{productId}")
    public ResponseEntity<Product> addStock(@PathVariable String productId, @RequestParam @Min(0) int newQuantity){
        Product newProductQuantity = productService.addStock(productId, newQuantity);
        return ResponseEntity.ok(newProductQuantity);
    }
}
