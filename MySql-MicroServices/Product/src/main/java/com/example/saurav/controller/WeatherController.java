package com.example.saurav.controller;


import com.example.saurav.VO.ResponseTemplateVO;
import com.example.saurav.entity.Weather;
import com.example.saurav.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/")
    public Weather saveWeather(@RequestBody Weather weather){
        log.info("Inside saveWeather of WeatherController");
        return weatherService.saveWeather(weather);
    }

//    @GetMapping("/{productId}")
//    public Product findByProductId(@PathVariable("productId") Long productId){
//        log.info("Inside findByProductId of ProductController");
//        return productService.findByProductId(productId);
//    }

    @GetMapping("/{locationId}")
    public ResponseTemplateVO getWeatherWithName(@PathVariable("locationId") Long locationId){
        log.info("Inside findByLocationId of WeatherController");
        return weatherService.getWeatherWithName(locationId);
    }

//        @GetMapping("/")
//        public String getWeather(){
//            return productService.getWeatherWithName();
//        }


}
