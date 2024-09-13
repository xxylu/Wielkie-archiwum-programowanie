public class Main {
    public static void main(String[] args) {
        Clock cl = new DigitalClock(9,12,12,true);
        System.out.println(cl);
        cl.setTime(0,0,0);
        System.out.println(cl);
        cl.setCurrentTime();

        System.out.println(cl);
    }
}