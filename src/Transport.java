public abstract sealed class Transport permits Car, ElectricCar, Ship, Airplane, Bicycle {
    protected final String name;
    protected final Engine engine;
    protected boolean engineRunning;

    public Transport(String name, Engine engine) {
        this.name = name;
        this.engine = engine;
        this.engineRunning = false;
    }

    public void startEngine() {
        if (engine == null) {
            System.out.println(name + ": Нет двигателя");
            return;
        }

        if (engineRunning) {
            System.out.println(name + ": Двигатель уже запущен");
        } else {
            engineRunning = true;
            System.out.println(name + ": Двигатель запущен");
        }
    }

    public void stopEngine() {
        if (engine == null) {
            System.out.println(name + ": Нет двигателя");
            return;
        }

        if (!engineRunning) {
            System.out.println(name + ": Двигатель уже остановлен");
        } else {
            engineRunning = false;
            System.out.println(name + ": Двигатель остановлен");
        }
    }

    public abstract String getInfo();

    public String getName() { return name; }

    public String getEngineStatus() {
        if (engine == null) {
            return "нет двигателя";
        }
        return engineRunning ? "запущен" : "остановлен";
    }
}