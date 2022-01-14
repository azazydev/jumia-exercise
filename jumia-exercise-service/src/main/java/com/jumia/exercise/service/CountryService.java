package com.jumia.exercise.service;

import com.jumia.exercise.model.Country;
import com.jumia.exercise.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAll() {
        return countryRepository.getAll();
    }

    public boolean isValidNumber(String code, String phoneNumber) {
        Country country = countryRepository.getByCode(code);
        return country.getPattern().matcher(phoneNumber).matches();
    }

    public Country getByCode(String code) {
        return countryRepository.getByCode(code);
    }


}
