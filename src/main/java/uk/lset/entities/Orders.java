package uk.lset.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "orders")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Validated
public class Orders {

    @Id
    @UuidGenerator
    @Column(name = "order_id")
    private String order_id;

    @Column(name = "client_name")
    @NotBlank(message = "Name is required")
    private String client_name;

    @Column(name = "email")
    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @Column(name = "inventory_id")
    @NotBlank(message  = "Inventory id is required")
    private String product_id;

    @Column(name = "quantity")
    @NotBlank(message = "Quantity is required")
    @Min(value = 1, message = "Order quantity must be 1")
    private int quantity;

    @Column(name = "product_price")
    @NotBlank
    private double product_price;

    @Column(name = "order_value")
    @NotBlank
    private double order_value;

    @Column(name = "delivery_address")
    @NotBlank(message = "Delivery address is required")
    private String delivery_address;

    @Column(name = "status")
    private String status;
}
