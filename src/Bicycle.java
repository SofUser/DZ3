public final class Bicycle extends Transport {
    public Bicycle(String name) {
        super(name, null);
    }

    @Override
    public void startEngine() {
        System.out.println(name + ": У велосипеда нет двигателя");
    }

    @Override
    public void stopEngine() {
        System.out.println(name + ": У велосипеда нет двигателя");
    }

    @Override
    public String getInfo() {
        return "Велосипед " + name;
    }
}