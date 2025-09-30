public final class Car extends Transport {
    public Car(String name, Engine engine) {
        super(name, engine);
    }

    @Override
    public String getInfo() {
        return String.format("Автомобиль %s - %s", name, engine.getInfo());
    }
}