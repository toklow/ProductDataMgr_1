package entity;

import java.util.Objects;

import static enums.Separators.FIELD_SEPARATOR;

public class Employee {

    private Long id;
    private String name;
    private String lastName;
    private Integer age;


    public Employee(Long id, String name, String lastName, Integer age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return id + FIELD_SEPARATOR.getValue() + name + FIELD_SEPARATOR.getValue() + lastName + FIELD_SEPARATOR.getValue() + age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) &&
                Objects.equals(this.name, employee.name) &&
                Objects.equals(this.lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), id, name, lastName);
    }

}
