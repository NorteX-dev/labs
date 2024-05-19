package com.nortexdev.lab4.classes;

import java.io.Serializable;

public class Car implements Serializable {
	private String brand;
	private String model;
	private int year;
	private double price;
	private boolean isUsed;

	public Car() {
		this.brand = "";
		this.model = "";
		this.year = 0;
		this.price = 0.0;
		this.isUsed = false;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(boolean used) {
		isUsed = used;
	}

	@Override
	public String toString() {
		return "Car{" +
			"brand='" + brand + '\'' +
			", model='" + model + '\'' +
			", year=" + year +
			", price=" + price +
			", isUsed=" + isUsed +
			'}';
	}
}
