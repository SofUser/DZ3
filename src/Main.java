import java.util.*;

public class Main {
    private static final List<Transport> transports = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Система управления транспортом ===");
            System.out.println("1. Создать транспорт");
            System.out.println("2. Показать весь транспорт");
            System.out.println("3. Запустить двигатель");
            System.out.println("4. Остановить двигатель");
            System.out.println("5. Показать статус двигателей");
            System.out.println("6. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createTransport();
                case 2 -> showTransports();
                case 3 -> startEngine();
                case 4 -> stopEngine();
                case 5 -> showEngineStatus();
                case 6 -> { return; }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void createTransport() {
        System.out.println("\nВыберите тип транспорта:");
        System.out.println("1. Автомобиль");
        System.out.println("2. Электромобиль");
        System.out.println("3. Корабль");
        System.out.println("4. Самолет");
        System.out.println("5. Велосипед");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите название: ");
        String name = scanner.nextLine();

        Transport transport = switch (type) {
            case 1 -> new Car(name, new Engine(EngineType.PETROL, 120));
            case 2 -> new ElectricCar(name);
            case 3 -> new Ship(name, new Engine(EngineType.DIESEL, 5000));
            case 4 -> new Airplane(name, new Engine(EngineType.JET, 10000));
            case 5 -> new Bicycle(name);
            default -> null;
        };

        if (transport != null) {
            transports.add(transport);
            System.out.println("Транспорт создан: " + transport.getInfo());
        }
    }

    private static void showTransports() {
        if (transports.isEmpty()) {
            System.out.println("Транспорта нет");
            return;
        }

        System.out.println("\n=== Весь транспорт ===");
        for (int i = 0; i < transports.size(); i++) {
            Transport transport = transports.get(i);
            String status = transport.getEngineStatus();
            System.out.printf("%d. %s [Двигатель: %s]%n",
                    i + 1, transport.getInfo(), status);
        }
    }

    private static void startEngine() {
        if (transports.isEmpty()) {
            System.out.println("Транспорта нет");
            return;
        }

        showTransports();
        System.out.print("Выберите транспорт для запуска: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < transports.size()) {
            transports.get(index).startEngine();
        } else {
            System.out.println("Неверный индекс!");
        }
    }

    private static void stopEngine() {
        if (transports.isEmpty()) {
            System.out.println("Транспорта нет");
            return;
        }

        showTransports();
        System.out.print("Выберите транспорт для остановки: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < transports.size()) {
            transports.get(index).stopEngine();
        } else {
            System.out.println("Неверный индекс!");
        }
    }

    private static void showEngineStatus() {
        if (transports.isEmpty()) {
            System.out.println("Транспорта нет");
            return;
        }

        System.out.println("\n=== Статус двигателей ===");
        for (int i = 0; i < transports.size(); i++) {
            Transport transport = transports.get(i);
            System.out.printf("%d. %s - Двигатель: %s%n",
                    i + 1, transport.getName(), transport.getEngineStatus());
        }
    }
}