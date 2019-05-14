package ru.kpfu.itis.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.model.Country;
import ru.kpfu.itis.repository.CountryRepository;

@Component
public class StringToCountryConvert implements Converter<String, Country> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country convert(String s) {
        if (s.equals("NONE")) {
            return null;
        }
        return countryRepository.findById(Long.parseLong(s)).get();
    }
}
