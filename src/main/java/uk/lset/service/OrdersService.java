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
import java.util.UUID;

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


/*
    public Orders addNewOrder(String productId, int quantity) {
        if(!productRepository.existsById(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with id " + productId + " does not exists." );
        }

        Product orderProduct = productService.getProductById(productId);
        Orders order = new Orders();

        order.setProductId(orderProduct.getInventoryId());
        order.setProductPrice(orderProduct.getPrice());
        order.setQuantity(quantity);
        order.setOrderValue(quantity * orderProduct.getPrice());
        order.setCorder(UUID.randomUUID().toString());
(
        order.setClient_Name(client_Name);
        order.setEmail(email);
        order.setDeliveryAddress(deliveryAddress);
        order.setStatus(status);
        return ordersRepository.save(order);
    }

    private String generateCoder(){
        return "C-" + UUID.randomUUID().toString().substring(0, 8);
    }
    */

}
