package com.jumia.exercise.service;

import com.jumia.exercise.model.Country;
import com.jumia.exercise.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {
    private CountryService countryService;
    @Mock
    private CountryRepository countryRepository;

    @BeforeEach
    public void init() {
        countryService = new CountryService(countryRepository);
    }

    @Test
    public void isValidNumber() {
        String code = "+212";
        String validPhoneNumber = "(212) 698054317";
        String invalidPhoneNumber = "(212) 6546545369";
        Mockito.when(countryRepository.getByCode(any())).thenReturn(createTestCountry());
        //valid
        assertEquals(true, countryService.isValidNumber(code, validPhoneNumber));
        //invalid
        assertEquals(false, countryService.isValidNumber(code, invalidPhoneNumber));
    }

    private Country createTestCountry() {
        Country country = new Country("+212", "Morocco", "\\(212\\)\\ ?[5-9]\\d{8}$");
        return country;
    }
}
