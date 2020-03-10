package weatherdata;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Measurement implements Comparable<Measurement> {
    LocalDate date;
    LocalTime time;
    Float temperature;
    char approvalcolor;

    public Measurement(LocalDate date, LocalTime time, String temperature, String approvalColor){
        this.date = date;
        this.time = time;
        this.temperature = Float.parseFloat(temperature.trim());
        this.approvalcolor = approvalColor.charAt(0);
    }

    public boolean isValid(){
        if (this.approvalcolor == 'G') {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Measurement other = (Measurement) obj;
        return this != null && date.equals(other.date) && time.equals((other.time));
    }

    @Override
    public String toString(){
        return this.date + " " + this.time + " " + this.temperature + " " + this.approvalcolor;
    }

    @Override
    public int compareTo(Measurement otherMeasurement){
        if (this.temperature > otherMeasurement.temperature) {
            return 1;
        } else if (this.temperature < otherMeasurement.temperature) {
            return -1;
        } else {
            return 0;
        }
    }
}
