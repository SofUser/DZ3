public final class Airplane extends Transport {
    public Airplane(String name, Engine engine) {
        super(name, engine);
    }

    @Override
    public String getInfo() {
        return String.format("Самолет %s - %s", name, engine.getInfo());
    }
}