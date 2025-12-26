
package theatretroupe;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static TheatreTroupe troupe = new TheatreTroupe();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== УПРАВЛЕНИЕ ТЕАТРАЛЬНОЙ ТРУППОЙ ===");
        
        while (true) {
            printMenu();
            int choice = getIntInput("Выберите действие: ");
            
            switch (choice) {
                case 1:
                    showAllActors();
                    break;
                case 2:
                    addNewActor();
                    break;
                case 3:
                    removeActor();
                    break;
                case 4:
                    findActor();
                    break;
                case 5:
                    sortActors();
                    break;
                case 6:
                    showStatistics();
                    break;
                case 7:
                    showHighestPaidActors();
                    break;
                case 8:
                    System.out.println("Программа завершена. До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
            
            System.out.println("\nНажмите Enter для продолжения...");
            scanner.nextLine();
        }
    }
    
    private static void printMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Показать всех актеров");
        System.out.println("2. Добавить нового актера");
        System.out.println("3. Удалить актера");
        System.out.println("4. Найти актера");
        System.out.println("5. Сортировать актеров");
        System.out.println("6. Показать статистику");
        System.out.println("7. Актеры с самой высокой зарплатой");
        System.out.println("8. Выход");
    }
    
    private static void showAllActors() {
        System.out.println("\n=== ВСЕ АКТЕРЫ ТРУППЫ ===");
        troupe.printAllActors();
    }
    
    private static void addNewActor() {
        System.out.println("\n=== ДОБАВЛЕНИЕ НОВОГО АКТЕРА ===");
        
        scanner.nextLine(); // очистка буфера
        
        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();
        
        int age = getIntInput("Введите возраст: ");
        
        System.out.print("Введите роль: ");
        String role = scanner.nextLine();
        
        int experience = getIntInput("Введите опыт работы (лет): ");
        
        double salary = getDoubleInput("Введите зарплату: ");
        
        troupe.addNewActor(lastName, firstName, age, role, experience, salary);
    }
    
    private static void removeActor() {
        System.out.println("\n=== УДАЛЕНИЕ АКТЕРА ===");
        System.out.println("1. Удалить по ID");
        System.out.println("2. Удалить по фамилии");
        int choice = getIntInput("Выберите способ удаления: ");
        
        switch (choice) {
            case 1:
                int id = getIntInput("Введите ID актера для удаления: ");
                if (troupe.removeActorById(id)) {
                    System.out.println("Актер с ID " + id + " удален.");
                } else {
                    System.out.println("Актер с ID " + id + " не найден.");
                }
                break;
            case 2:
                scanner.nextLine();
                System.out.print("Введите фамилию актера для удаления: ");
                String lastName = scanner.nextLine();
                if (troupe.removeActorByLastName(lastName)) {
                    System.out.println("Актер(ы) с фамилией " + lastName + " удален(ы).");
                } else {
                    System.out.println("Актер с фамилией " + lastName + " не найден.");
                }
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }
    
    private static void findActor() {
        System.out.println("\n=== ПОИСК АКТЕРА ===");
        System.out.println("1. Найти по ID");
        System.out.println("2. Найти по фамилии");
        System.out.println("3. Найти по роли");
        System.out.println("4. Найти по опыту (более N лет)");
        int choice = getIntInput("Выберите тип поиска: ");
        
        switch (choice) {
            case 1:
                int id = getIntInput("Введите ID актера: ");
                Actor actor = troupe.findActorById(id);
                if (actor != null) {
                    System.out.println("Найден актер: " + actor);
                } else {
                    System.out.println("Актер с ID " + id + " не найден.");
                }
                break;
            case 2:
                scanner.nextLine();
                System.out.print("Введите фамилию: ");
                String lastName = scanner.nextLine();
                List<Actor> actorsByLastName = troupe.findActorsByLastName(lastName);
                printSearchResults(actorsByLastName, "по фамилии " + lastName);
                break;
            case 3:
                scanner.nextLine();
                System.out.print("Введите роль: ");
                String role = scanner.nextLine();
                List<Actor> actorsByRole = troupe.findActorsByRole(role);
                printSearchResults(actorsByRole, "с ролью " + role);
                break;
            case 4:
                int years = getIntInput("Введите минимальный опыт (лет): ");
                List<Actor> experiencedActors = troupe.findActorsWithExperienceMoreThan(years);
                printSearchResults(experiencedActors, "с опытом более " + years + " лет");
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }
    
    private static void sortActors() {
        System.out.println("\n=== СОРТИРОВКА АКТЕРОВ ===");
        System.out.println("1. По фамилии (А-Я)");
        System.out.println("2. По возрасту (молодые - старые)");
        System.out.println("3. По опыту (больше опыт - меньше опыт)");
        System.out.println("4. По зарплате (высокая - низкая)");
        System.out.println("5. По полному имени");
        int choice = getIntInput("Выберите тип сортировки: ");
        
        switch (choice) {
            case 1:
                troupe.sortByLastName();
                System.out.println("Сортировка по фамилии выполнена.");
                break;
            case 2:
                troupe.sortByAge();
                System.out.println("Сортировка по возрасту выполнена.");
                break;
            case 3:
                troupe.sortByExperience();
                System.out.println("Сортировка по опыту выполнена.");
                break;
            case 4:
                troupe.sortBySalary();
                System.out.println("Сортировка по зарплате выполнена.");
                break;
            case 5:
                troupe.sortByFullName();
                System.out.println("Сортировка по полному имени выполнена.");
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }
        
        System.out.println("\nРезультат сортировки:");
        troupe.printAllActors();
    }
    
    private static void showStatistics() {
        troupe.printStatistics();
    }
    
    private static void showHighestPaidActors() {
        System.out.println("\n=== АКТЕРЫ С САМОЙ ВЫСОКОЙ ЗАРПЛАТОЙ ===");
        List<Actor> highestPaid = troupe.getActorsWithHighestSalary();
        
        if (highestPaid.isEmpty()) {
            System.out.println("В труппе нет актеров.");
        } else {
            System.out.println("Количество: " + highestPaid.size());
            for (Actor actor : highestPaid) {
                System.out.println(actor);
            }
        }
    }
    
    private static void printSearchResults(List<Actor> actors, String searchCriteria) {
        if (actors.isEmpty()) {
            System.out.println("Актеры " + searchCriteria + " не найдены.");
        } else {
            System.out.println("Найдено " + actors.size() + " актер(ов) " + searchCriteria + ":");
            for (Actor actor : actors) {
                System.out.println(actor);
            }
        }
    }
    
    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = scanner.nextInt();
                scanner.nextLine(); // очистка буфера
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Введите целое число.");
                scanner.nextLine(); // очистка буфера
            }
        }
    }
    
    private static double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = scanner.nextDouble();
                scanner.nextLine(); // очистка буфера
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Введите число.");
                scanner.nextLine(); // очистка буфера
            }
        }
    }
}
