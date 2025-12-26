
package theatretroupe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TheatreTroupe {
    private List<Actor> actors;
    private int nextId;
    
    // Конструктор
    public TheatreTroupe() {
        actors = new ArrayList<>();
        nextId = 1;
        
        // Добавим несколько актеров для примера
        addActor(new Actor(nextId++, "Иванов", "Иван", 35, "Главная роль", 10, 50000));
        addActor(new Actor(nextId++, "Петрова", "Анна", 28, "Второстепенная роль", 5, 30000));
        addActor(new Actor(nextId++, "Сидоров", "Петр", 45, "Главная роль", 20, 70000));
        addActor(new Actor(nextId++, "Кузнецова", "Мария", 32, "Эпизодическая роль", 8, 25000));
        addActor(new Actor(nextId++, "Смирнов", "Алексей", 40, "Главная роль", 15, 60000));
    }
    
    // Добавить актера
    public void addActor(Actor actor) {
        actors.add(actor);
    }
    
    // Добавить нового актера с автоматическим ID
    public void addNewActor(String lastName, String firstName, int age, 
                           String role, int experienceYears, double salary) {
        Actor actor = new Actor(nextId++, lastName, firstName, age, role, experienceYears, salary);
        actors.add(actor);
        System.out.println("Актер успешно добавлен!");
    }
    
    // Удалить актера по ID
    public boolean removeActorById(int id) {
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getId() == id) {
                actors.remove(i);
                return true;
            }
        }
        return false;
    }
    
    // Удалить актера по фамилии
    public boolean removeActorByLastName(String lastName) {
        boolean removed = false;
        for (int i = actors.size() - 1; i >= 0; i--) {
            if (actors.get(i).getLastName().equalsIgnoreCase(lastName)) {
                actors.remove(i);
                removed = true;
            }
        }
        return removed;
    }
    
    // Найти актера по ID
    public Actor findActorById(int id) {
        for (Actor actor : actors) {
            if (actor.getId() == id) {
                return actor;
            }
        }
        return null;
    }
    
    // Найти актеров по фамилии
    public List<Actor> findActorsByLastName(String lastName) {
        List<Actor> result = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor.getLastName().equalsIgnoreCase(lastName)) {
                result.add(actor);
            }
        }
        return result;
    }
    
    // Найти актеров по роли
    public List<Actor> findActorsByRole(String role) {
        List<Actor> result = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor.getRole().equalsIgnoreCase(role)) {
                result.add(actor);
            }
        }
        return result;
    }
    
    // Найти актеров с опытом больше указанного
    public List<Actor> findActorsWithExperienceMoreThan(int years) {
        List<Actor> result = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor.getExperienceYears() > years) {
                result.add(actor);
            }
        }
        return result;
    }
    
    // Сортировка по фамилии (естественная)
    public void sortByLastName() {
        Collections.sort(actors);
    }
    
    // Сортировка по возрасту
    public void sortByAge() {
        Collections.sort(actors, new Comparator<Actor>() {
            @Override
            public int compare(Actor a1, Actor a2) {
                return Integer.compare(a1.getAge(), a2.getAge());
            }
        });
    }
    
    // Сортировка по опыту
    public void sortByExperience() {
        Collections.sort(actors, new Comparator<Actor>() {
            @Override
            public int compare(Actor a1, Actor a2) {
                return Integer.compare(a2.getExperienceYears(), a1.getExperienceYears()); // по убыванию
            }
        });
    }
    
    // Сортировка по зарплате
    public void sortBySalary() {
        Collections.sort(actors, new Comparator<Actor>() {
            @Override
            public int compare(Actor a1, Actor a2) {
                return Double.compare(a2.getSalary(), a1.getSalary()); // по убыванию
            }
        });
    }
    
    // Сортировка по полному имени (фамилия + имя)
    public void sortByFullName() {
        Collections.sort(actors, new Comparator<Actor>() {
            @Override
            public int compare(Actor a1, Actor a2) {
                return a1.getFullName().compareTo(a2.getFullName());
            }
        });
    }
    
    // Получить всех актеров
    public List<Actor> getAllActors() {
        return new ArrayList<>(actors);
    }
    
    // Получить количество актеров
    public int getActorsCount() {
        return actors.size();
    }
    
    // Вывести всех актеров
    public void printAllActors() {
        if (actors.isEmpty()) {
            System.out.println("В труппе нет актеров.");
            return;
        }
        
        System.out.println("=== СПИСОК ВСЕХ АКТЕРОВ ===");
        for (Actor actor : actors) {
            System.out.println(actor);
        }
        System.out.println("Всего актеров: " + actors.size());
    }
    
    // Получить статистику
    public void printStatistics() {
        if (actors.isEmpty()) {
            System.out.println("В труппе нет актеров.");
            return;
        }
        
        double totalSalary = 0;
        int totalExperience = 0;
        int maxAge = Integer.MIN_VALUE;
        int minAge = Integer.MAX_VALUE;
        
        for (Actor actor : actors) {
            totalSalary += actor.getSalary();
            totalExperience += actor.getExperienceYears();
            maxAge = Math.max(maxAge, actor.getAge());
            minAge = Math.min(minAge, actor.getAge());
        }
        
        System.out.println("=== СТАТИСТИКА ТРУППЫ ===");
        System.out.println("Всего актеров: " + actors.size());
        System.out.printf("Средняя зарплата: %.2f%n", totalSalary / actors.size());
        System.out.printf("Средний опыт: %.1f лет%n", (double) totalExperience / actors.size());
        System.out.println("Самый молодой актер: " + minAge + " лет");
        System.out.println("Самый старший актер: " + maxAge + " лет");
        System.out.printf("Общий фонд зарплат: %.2f%n", totalSalary);
    }
    
    // Получить актеров с самой высокой зарплатой
    public List<Actor> getActorsWithHighestSalary() {
        if (actors.isEmpty()) {
            return new ArrayList<>();
        }
        
        sortBySalary();
        double highestSalary = actors.get(0).getSalary();
        List<Actor> result = new ArrayList<>();
        
        for (Actor actor : actors) {
            if (actor.getSalary() == highestSalary) {
                result.add(actor);
            } else {
                break;
            }
        }
        
        return result;
    }
}