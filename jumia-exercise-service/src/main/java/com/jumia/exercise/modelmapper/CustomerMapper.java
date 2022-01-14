package com.jumia.exercise.modelmapper;

import com.jumia.exercise.dto.CustomerDto;
import com.jumia.exercise.dto.StateDto;
import com.jumia.exercise.model.Customer;
import com.jumia.exercise.service.CountryService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CustomerMapper {
    @Autowired
    private CountryService countryService;

    public abstract CustomerDto toCustomerDto(Customer customer);

    public abstract List<CustomerDto> toCustomerDto(List<Customer> customers);

    @AfterMapping
    public void toDtoAfterMapping(Customer customer, @MappingTarget CustomerDto dto) {
        String countryCode = null;
        String[] phoneParts = customer.getPhone().split(" ");
        if (phoneParts.length > 0)
            countryCode = phoneParts[0].replace("(", "+").replace(")", "");
        dto.setCountryCode(countryCode);

        dto.setCountry(countryService.getByCode(dto.getCountryCode()).getName());
        boolean isValid = countryService.isValidNumber(dto.getCountryCode(), dto.getPhone());
        if (isValid) {
            dto.setState(StateDto.VALID);
        } else {
            dto.setState(StateDto.INVALID);
        }

    }
}
