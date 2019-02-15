package com.api.bookstore.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADRESS")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Long id;
    @Column(name="neighborhood")
    private String neighborhood;
    @Column(name="street")
    private String street;
    @Column(name="number")
    private Integer number;
    @Column(name="zipCode")
    private Integer zipCode;
    @Column(name="reference")
    private String reference;

    public Address() {
    }

    
    
    public Address(String neighborhood, String street, Integer number, Integer zipCode, String reference) {
		super();
		this.neighborhood = neighborhood;
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.reference = reference;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
