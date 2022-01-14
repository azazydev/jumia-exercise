package com.jumia.exercise.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = {"id", "name", "phone"})
public class CustomerDto {
    private Integer id;
    private String name;
    private String phone;
    private String country;
    private String countryCode;
    private StateDto state;

}
