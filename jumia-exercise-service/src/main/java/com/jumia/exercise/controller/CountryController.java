package com.jumia.exercise.controller;

import com.jumia.exercise.dto.CountryDto;
import com.jumia.exercise.model.Country;
import com.jumia.exercise.modelmapper.CountryMapper;
import com.jumia.exercise.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ahmed Al3zazy
 * country controller
 */
@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
@CrossOrigin
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @GetMapping
    public ResponseEntity<Page<CountryDto>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size) {

        List<Country> countries = countryService.getAll();
        List<CountryDto> countryDtos = countryMapper.toCountryDto(countries);
        Page<CountryDto> customerDtoPage = new PageImpl<>(countryDtos, PageRequest.of(page, size), countryDtos.size());

        return ResponseEntity.ok(customerDtoPage);
    }


}
