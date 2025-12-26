
package theatretroupe;

public class Actor implements Comparable<Actor> {
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private String role;
    private int experienceYears;
    private double salary;
    
    // Конструктор
    public Actor(int id, String lastName, String firstName, int age, 
                 String role, int experienceYears, double salary) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.role = role;
        this.experienceYears = experienceYears;
        this.salary = salary;
    }
    
    // Геттеры и сеттеры
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFullName() {
        return lastName + " " + firstName;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public int getExperienceYears() {
        return experienceYears;
    }
    
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d, Фамилия: %s, Имя: %s, Возраст: %d, " +
                           "Роль: %s, Опыт: %d лет, Зарплата: %.2f",
                           id, lastName, firstName, age, role, experienceYears, salary);
    }
    
    @Override
    public int compareTo(Actor other) {
        return this.lastName.compareTo(other.lastName);
    }
}