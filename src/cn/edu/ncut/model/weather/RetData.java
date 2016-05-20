package cn.edu.ncut.model.weather;

import java.io.Serializable;
import java.util.List;

/**
 * @author NikoBelic
 * @create 23:38
 */
public class RetData implements Serializable
{
    private String city;
    private Today today;
    private List<Forecast> forecast;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    @Override
    public String toString() {
        return "RetData{" +
                "city='" + city + '\'' +
                ", today=" + today +
                ", forecast=" + forecast +
                '}';
    }
}
