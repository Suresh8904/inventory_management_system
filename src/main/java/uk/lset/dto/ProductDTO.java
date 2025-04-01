package uk.lset.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {
    private String productId;
    private String productName;
    private int productQuantity;


    public ProductDTO(String productId, String productName, int productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

}
