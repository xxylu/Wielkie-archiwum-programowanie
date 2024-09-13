public class DigitalClock extends Clock {
    private boolean format24;
    public DigitalClock(int hour, int minute, int second, boolean format24) {
        super(hour, minute, second);
        this.format24 = format24;
    }

    public void setFormat24h(boolean format24) {

    }

    public boolean getFormat24h() {
        return format24;
    }

}

public String toString(){
    if(hour < 13){
       return String.format("%2d:%02d:%02d AM", hour, minute, second);
    } else {
        return String.format("%2d:%02d:%02d PM", hour-12, minute, second);
    }
}