package com.jumia.exercise.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = {"id", "name", "phone"})
public class Customer {
    @Id
    private Integer id;
    private String name;
    private String phone;
}
