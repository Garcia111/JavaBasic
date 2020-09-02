package interview.interview_20191113;


import lombok.*;

@Data
@ToString
@NoArgsConstructor
public class Weather implements Comparable<Weather> {

    private double temperature;

    private Integer windLevel;


    public Weather(double temperature, Integer windLevel) {
        this.temperature = temperature;
        this.windLevel = windLevel;
    }



    @Override
    public int compareTo(Weather o) {
        if(this.temperature >o.temperature){
            return 1;
        }
        return -1;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Integer getWindLevel() {
        return windLevel;
    }

    public void setWindLevel(Integer windLevel) {
        this.windLevel = windLevel;
    }
}
