package uk.lset.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Table(name = "suppliers")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Supplier {
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public @NotBlank(message = "Supplier name is required") String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(@NotBlank(message = "Supplier name is required") String supplierName) {
        this.supplierName = supplierName;
    }

    public @NotBlank(message = "Supplier address is required") String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(@NotBlank(message = "Supplier address is required") String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public @NotBlank(message = "Supplier code is required") String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(@NotBlank(message = "Supplier code is required") String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public @NotBlank(message = "Supplier email is required") @Email(message = "Incorrect email") String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(@NotBlank(message = "Supplier email is required") @Email(message = "Incorrect email") String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public @NotBlank(message = "Supplier phone number is required") String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public void setSupplierPhoneNumber(@NotBlank(message = "Supplier phone number is required") String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    @Id
    @UuidGenerator
    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "supplier_name")
    @NotBlank(message = "Supplier name is required")
    private String supplierName;

    @Column(name = "supplier_address")
    @NotBlank(message = "Supplier address is required")
    private String supplierAddress;

    @Column(name = "supplier_code", unique = true)
    @NotBlank(message = "Supplier code is required")
    private String supplierCode;

    @Column(name = "supplier_email")
    @NotBlank(message = "Supplier email is required")
    @Email(message = "Incorrect email")
    private String supplierEmail;

    @Column(name = "supplier_phone_number")
    @NotBlank(message = "Supplier phone number is required")
    private String supplierPhoneNumber;

}
