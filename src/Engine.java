public class Engine {
    private final EngineType type;
    private final double power;

    public Engine(EngineType type, double power) {
        if (type == null) {
            throw new IllegalArgumentException("Тип двигателя не может быть null");
        }
        if (power <= 0) {
            throw new IllegalArgumentException("Мощность двигателя должна быть положительной");
        }
        this.type = type;
        this.power = power;
    }

    public String getInfo() {
        return String.format("%s (%.1f л.с.)", type, power);
    }
}