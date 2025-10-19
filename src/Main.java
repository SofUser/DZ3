import java.util.*;

public class Main {
    private static final List<Transport> transports = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            runTransportSystem();
        } catch (Exception e) {
            System.out.println("Критическая ошибка: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Программа завершена.");
        }
    }

    private static void runTransportSystem() {
        while (true) {
            try {
                showMenu();
                int choice = getMenuChoice();

                if (choice == 6) {
                    System.out.println("Выход из системы...");
                    return;
                }

                executeMenuChoice(choice);

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Создать транспорт");
        System.out.println("2. Показать весь транспорт");
        System.out.println("3. Запустить двигатель");
        System.out.println("4. Остановить двигатель");
        System.out.println("5. Показать статус двигателей");
        System.out.println("6. Выход");
        System.out.print("Выберите действие: ");
    }

    private static int getMenuChoice() {
        while (true) {
            try {
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice >= 1 && choice <= 6) {
                        return choice;
                    } else {
                        System.out.print("Неверный выбор! Введите число от 1 до 6: ");
                    }
                } else {
                    System.out.print("Ошибка! Введите целое число: ");
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.print("Ошибка ввода! Попробуйте снова: ");
                scanner.nextLine();
            }
        }
    }

    private static void executeMenuChoice(int choice) {
        switch (choice) {
            case 1 -> createTransport();
            case 2 -> showTransports();
            case 3 -> startEngine();
            case 4 -> stopEngine();
            case 5 -> showEngineStatus();
        }
    }

    private static void createTransport() {
        try {
            System.out.println("\n=== Создание транспорта ===");
            System.out.println("1. Автомобиль (бензиновый)");
            System.out.println("2. Электромобиль");
            System.out.println("3. Корабль (дизельный)");
            System.out.println("4. Самолет (реактивный)");
            System.out.println("5. Велосипед");
            System.out.print("Выберите тип транспорта: ");

            int type = getValidatedIntInput(1, 5);
            System.out.print("Введите название транспорта: ");

            String name = getNonEmptyInput();

            Transport transport = createTransportByType(type, name);
            if (transport != null) {
                transports.add(transport);
                System.out.println("Транспорт создан: " + transport.getInfo());
            }

        } catch (Exception e) {
            System.out.println("Ошибка при создании транспорта: " + e.getMessage());
        }
    }

    private static Transport createTransportByType(int type, String name) {
        return switch (type) {
            case 1 -> new Car(name, new Engine(EngineType.PETROL, 120));
            case 2 -> new ElectricCar(name);
            case 3 -> new Ship(name, new Engine(EngineType.DIESEL, 5000));
            case 4 -> new Airplane(name, new Engine(EngineType.JET, 10000));
            case 5 -> new Bicycle(name);
            default -> throw new IllegalArgumentException("Неизвестный тип транспорта");
        };
    }

    private static void showTransports() {
        if (transports.isEmpty()) {
            System.out.println("Транспорта нет");
            return;
        }

        System.out.println("\n=== ВЕСЬ ТРАНСПОРТ ===");
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
        System.out.print("Выберите транспорт для запуска двигателя: ");

        int index = getTransportIndex();
        transports.get(index).startEngine();
    }

    private static void stopEngine() {
        if (transports.isEmpty()) {
            System.out.println("Транспорта нет");
            return;
        }

        showTransports();
        System.out.print("Выберите транспорт для остановки двигателя: ");

        int index = getTransportIndex();
        transports.get(index).stopEngine();
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

    // Вспомогательные методы для валидации ввода
    private static int getTransportIndex() {
        while (true) {
            try {
                int index = getValidatedIntInput(1, transports.size()) - 1;
                if (index >= 0 && index < transports.size()) {
                    return index;
                }
                System.out.print("Неверный индекс! Попробуйте снова: ");
            } catch (Exception e) {
                System.out.print("Ошибка! Введите корректный номер: ");
            }
        }
    }

    private static int getValidatedIntInput(int min, int max) {
        while (true) {
            try {
                if (scanner.hasNextInt()) {
                    int input = scanner.nextInt();
                    scanner.nextLine();
                    if (input >= min && input <= max) {
                        return input;
                    } else {
                        System.out.printf("Ошибка! Введите число от %d до %d: ", min, max);
                    }
                } else {
                    System.out.print("Ошибка! Введите целое число: ");
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.print("Ошибка ввода! Попробуйте снова: ");
                scanner.nextLine();
            }
        }
    }

    private static String getNonEmptyInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    return input;
                }
                System.out.print("Ошибка! Название не может быть пустым. Введите снова: ");
            } catch (Exception e) {
                System.out.print("Ошибка ввода! Попробуйте снова: ");
                scanner.nextLine();
            }
        }
    }
}