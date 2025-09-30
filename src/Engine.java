public class Engine {
    private final EngineType type;
    private final double power;

    public Engine(EngineType type, double power) {
        this.type = type;
        this.power = power;
    }

    public String getInfo() {
        return String.format("%s (%.1f л.с.)", type, power);
    }
}