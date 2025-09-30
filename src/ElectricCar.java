public final class ElectricCar extends Transport {
    public ElectricCar(String name) {
        super(name, new Engine(EngineType.ELECTRIC, 150));
    }

    @Override
    public String getInfo() {
        return String.format("Электромобиль %s - %s", name, engine.getInfo());
    }
}