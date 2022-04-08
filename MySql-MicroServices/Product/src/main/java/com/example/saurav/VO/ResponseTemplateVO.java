package com.example.saurav.VO;

import com.example.saurav.entity.Weather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTemplateVO {

    private Weather product;
    private WeatherExternal weather;



}
