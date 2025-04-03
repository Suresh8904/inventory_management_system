package uk.lset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.lset.entities.Orders;
import uk.lset.entities.Product;
import uk.lset.repository.OrdersRepository;
import uk.lset.repository.ProductRepository;

import java.util.Optional;

@Service
public class OrdersService {


    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, ProductService productService, ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    private final ProductService productService;
    private final ProductRepository productRepository;


    public Orders addNewOrder(String productId, int quantity) {
        if(!productRepository.existsById(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with id " + productId + " does not exists." );
        }
        Orders order = new Orders();
        Product orderProduct = productService.getProductById(productId);
        order.setProduct_id(orderProduct.getInventoryId());
        order.setProduct_price(orderProduct.getPrice());
        order.setEmail(order.getProduct_id());
        order.setOrder_value(quantity * orderProduct.getPrice());

        return ordersRepository.save(order);
    }
}
