package com.disney.poc.microservices.products;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	protected Long id;
	protected Long value;
	protected String name;
	protected String details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return this.id + "-" + this.name + "-" + this.value;
	}

}
