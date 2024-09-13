public abstract class City {
    private String name;
    private String timeZone;
    private double meridian;
    private double parallel;

    public City(String name, String timeZone, double meridian, double parallel) {
        this.name = name;
        this.timeZone = timeZone;
        this.meridian = meridian;
        this.parallel = parallel;
    }

    public String getName() {
        return name;
    }
    public String getTimeZone() {
        return timeZone;
    }
    public double getMeridian() {
        return meridian;
    }
    public double getParallel() {
        return parallel;
    }

    public void setCity(String name, String timeZone, double meridian, double parallel) {
        this.name = name;
        this.timeZone = timeZone;
        this.meridian = meridian;
        this.parallel = parallel;
    }
}
