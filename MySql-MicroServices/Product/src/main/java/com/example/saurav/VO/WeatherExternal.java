package com.example.saurav.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherExternal {


    public Object coord;
    private Main main;
    private long timezone;
    private Sys sys;
    private String name;
    private Wind wind;


}
