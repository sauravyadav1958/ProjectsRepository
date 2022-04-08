package com.example.saurav.service;

import com.example.saurav.VO.ResponseTemplateVO;
import com.example.saurav.VO.WeatherExternal;
import com.example.saurav.entity.Weather;
import com.example.saurav.repository.WeatherRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private RestTemplate restTemplate;


    public Weather saveWeather(Weather weather) {
        log.info("Inside saveWeather of WeatherService");
        return weatherRepository.save(weather);
    }


    public Weather findBylocationId(Long locationId) {
        log.info("Inside findByProductId of ProductService");
        return weatherRepository.findBylocationId(locationId);
    }

    public ResponseTemplateVO getWeatherWithName(Long locationId){
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Weather weather = weatherRepository.findBylocationId(locationId);
        WeatherExternal weatherExternal =  restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q=" + weather.getLocationName() + "&appid=0786a968c14e6c8d7ee567c4b1b7c99d", WeatherExternal.class);

        vo.setProduct(weather);
        vo.setWeather(weatherExternal);

        return vo;

    }

}
