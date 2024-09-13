import java.time.LocalTime;

public abstract class Clock {
    private int hour;
    private int minute;
    private int second;

    public Clock(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getCurrHour() {
        return hour;
    }
    public int getCurrMinute() {
        return minute;
    }
    public int getCurrSecond() {
        return second;
    }

    public void setCurrentTime() {
        LocalTime now = LocalTime.now();
        this.hour = now.getHour();
        this.minute = now.getMinute();
        this.second = now.getSecond();
    }

    public void setTime(int hour, int minute, int second) {
        if (hour > 23 || minute > 59 || second > 59 || hour < 0 || minute < 0 || second < 0) {
            System.out.println("Invalid time values");
        } else {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
    }

    public String toString(){
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
