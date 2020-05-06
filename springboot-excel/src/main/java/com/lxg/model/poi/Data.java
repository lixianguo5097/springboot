package com.lxg.model.poi;

import java.util.Date;

/**
 * @author LXG
 * @date 2020-4-28
 */
@lombok.Data
public class Data {
    private Date observeTime;
    private String stationName;
    private String stationCode;
    private Double tempChangeValue;
    private Double tempRawValue;
    private Double pressChangeValue;
    private Double pressRawValue;
    private Double humidityChangeValue;
    private Double humidityRawValue;
    private Double rainChangeValue;
    private Double rainRawValue;
    private Double visualChangeValue;
    private Double visualRawValue;
    private Double windSpeedChangeValue;
    private Double windSpeedRawValue;
    private Double windDirChangeValue;
    private Double windDirRawValue;

    public Data(Date observeTime, String stationName, String stationCode, Double tempChangeValue, Double tempRawValue, Double pressChangeValue, Double pressRawValue, Double humidityChangeValue, Double humidityRawValue, Double rainChangeValue, Double rainRawValue, Double visualChangeValue, Double visualRawValue, Double windSpeedChangeValue, Double windSpeedRawValue, Double windDirChangeValue, Double windDirRawValue) {
        this.observeTime = observeTime;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.tempChangeValue = tempChangeValue;
        this.tempRawValue = tempRawValue;
        this.pressChangeValue = pressChangeValue;
        this.pressRawValue = pressRawValue;
        this.humidityChangeValue = humidityChangeValue;
        this.humidityRawValue = humidityRawValue;
        this.rainChangeValue = rainChangeValue;
        this.rainRawValue = rainRawValue;
        this.visualChangeValue = visualChangeValue;
        this.visualRawValue = visualRawValue;
        this.windSpeedChangeValue = windSpeedChangeValue;
        this.windSpeedRawValue = windSpeedRawValue;
        this.windDirChangeValue = windDirChangeValue;
        this.windDirRawValue = windDirRawValue;
    }
}
