package com.shipan.ecommerce.entity.exception;

/*
@EqualsAndHashCode(但是这里不需要)
* 	equals(): Compares the current instance with another object to see if they are “equal” based on the values of their fields.
	hashCode(): Generates a hash code for the instance, typically used in hashed collections (like HashMap and HashSet) for efficient retrieval and storage.
*   callSuper = true: This option tells Lombok to include equals() and hashCode() calculations from the superclass (RuntimeException in this case).
* */


public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
