public final class Ship extends Transport {
    public Ship(String name, Engine engine) {
        super(name, engine);
    }

    @Override
    public String getInfo() {
        return String.format("Корабль %s - %s", name, engine.getInfo());
    }
}