package ru.kpfu.itis.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.model.City;
import ru.kpfu.itis.repository.CityRepository;

@Component
public class StringToCityConvert implements Converter<String, City> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City convert(String s) {
        if (s.equals("NONE")) {
            return null;
        }
        return cityRepository.findById(Long.parseLong(s)).get();
    }
}
