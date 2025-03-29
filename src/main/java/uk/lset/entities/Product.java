package uk.lset.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Table(name = "products")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Product {
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public @NotBlank(message = "Product name is required") String getProductName() {
        return productName;
    }

    public void setProductName(@NotBlank(message = "Product name is required") String productName) {
        this.productName = productName;
    }

    public @NotBlank(message = "Product description is required") String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(@NotBlank(message = "Product description is required") String productDescription) {
        this.productDescription = productDescription;
    }

    @Min(value = 0, message = "Price must be at least 0")
    public double getPrice() {
        return price;
    }

    public void setPrice(@Min(value = 0, message = "Price must be at least 0") double price) {
        this.price = price;
    }

    @Min(value = 0, message = "Quantity must be at least 0")
    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(@Min(value = 0, message = "Quantity must be at least 0") int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public @NotBlank(message = "Inventory id is required") String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(@NotBlank(message = "Inventory id is required") String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public @NotBlank(message = "Category is required") String getCategory() {
        return category;
    }

    public void setCategory(@NotBlank(message = "Category is required") String category) {
        this.category = category;
    }

    @Id
    @UuidGenerator
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    @NotBlank(message = "Product name is required")
    private String productName;

    @Column(name = "product_description")
    @NotBlank(message = "Product description is required")
    private String productDescription;

    @Column(name = "price")
    @Min(value = 0, message = "Price must be at least 0")
    private double price;

    @Column(name = "quantity_available")
    @Min(value = 0, message = "Quantity must be at least 0")
    private int productQuantity;

    @Column(name = "inventory_id" , unique = true)
    @NotBlank(message = "Inventory id is required")
    private String inventoryId;

    @Column(name = "category")
    @NotBlank(message = "Category is required")
    private String category;

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                ", productQuantity=" + productQuantity +
                ", inventoryId='" + inventoryId + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
