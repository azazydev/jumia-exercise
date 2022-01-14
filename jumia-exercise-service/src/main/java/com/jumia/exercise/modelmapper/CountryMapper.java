package com.jumia.exercise.modelmapper;

import com.jumia.exercise.dto.CountryDto;
import com.jumia.exercise.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CountryMapper {

    public abstract CountryDto toCountryDto(Country country);

    public abstract List<CountryDto> toCountryDto(List<Country> countries);


}
