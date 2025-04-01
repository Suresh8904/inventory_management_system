package uk.lset.service;



import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import uk.lset.dto.ProductDTO;
import uk.lset.entities.Product;
import uk.lset.repository.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addNewProduct(Product product) {
        if(productRepository.existsByInventoryId(product.getInventoryId())) {
            throw new RuntimeException("Inventory ID already exists.");
        }

        return productRepository.save(product);
    }


    @Transactional(readOnly = true)
    public List<Product> getAllProducts(Product product){
        return productRepository.findAll();
    }

	/*public Product getProductById(String id) {
		return productRepository.findById(id).orElse(null);
	}*/


    //Service for sorted products and pagination
    @Transactional(readOnly = true)
    public Page<Product> getAllProductsSorted(int page, int size, Sort sort, String category) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }


    public Optional<Product> getProductByProductId(String id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product updateProduct(Product product) {
        if(!productRepository.existsByProductId(product.getProductId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with id " + product.getProductId() + " does not exists." );
        }
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct (String id) {
        if(!productRepository.existsByProductId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with id " + id + " does not exists." );
        }
        productRepository.deleteById(id);
    }

    public ProductDTO getProductStock(String id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with id " + id + " does not exists." );
        }
        return new ProductDTO(
                product.getProductId(),
                product.getProductName(),
                product.getProductQuantity());
    }
}
