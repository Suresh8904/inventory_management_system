package uk.lset.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.lset.entities.Orders;
import uk.lset.service.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {


    private OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
/*
    @PostMapping("/addNewOrder/{productId}")
    public ResponseEntity<Orders> addNewOrder( @PathVariable String productId,@RequestParam int quantity){
        Orders newOrder = ordersService.addNewOrder(productId, quantity);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
    */
}

