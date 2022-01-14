package com.jumia.exercise.repository;

import com.jumia.exercise.model.Country;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CountryRepository {
    private static Map<String, Country> countries;

    //put all countries in a map to get it later in constant time
    static {
        Map<String, Country> map = new HashMap<>();
        map.put("+237", new Country("+237", "Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$"));
        map.put("+251", new Country("+251", "Ethiopia", "\\(251\\)\\ ?[1-59]\\d{8}$"));
        map.put("+212", new Country("+212", "Morocco", "\\(212\\)\\ ?[5-9]\\d{8}$"));
        map.put("+258", new Country("+258", "Mozambique", "\\(258\\)\\ ?[28]\\d{7,8}$"));
        map.put("+256", new Country("+256", "Uganda", "\\(256\\)\\ ?\\d{9}$"));
        countries = Collections.unmodifiableMap(map);
    }

    public Country getByCode(String code) {
        return countries.get(code);
    }

    public List<Country> getAll() {
        return (List<Country>) countries.values();
    }

}
