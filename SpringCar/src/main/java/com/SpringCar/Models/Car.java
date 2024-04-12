package com.SpringCar.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="Cars")
public class Car {

    @Id
    @Column (name = "carId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String make;
    private String color;

    public Car() {

    }

    public Car(int id, String model, String make, String color) {
        this.id = id;
        this.model = model;
        this.make = make;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Objects.equals(model, car.model) && Objects.equals(make, car.make) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, make, color);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}