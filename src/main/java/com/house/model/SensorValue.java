package com.house.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "DHT")
public class SensorValue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Max(50)
    @Min(-10)
    private double temp;
    @Max(100)
    @Min(0)
    private double humid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public SensorValue() {
    }

    public SensorValue(@Max(50) @Min(-10) double temp, @Max(100) @Min(0) double humid, Date time) {
        this.temp = temp;
        this.humid = humid;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumid() {
        return humid;
    }

    public void setHumid(double humid) {
        this.humid = humid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
