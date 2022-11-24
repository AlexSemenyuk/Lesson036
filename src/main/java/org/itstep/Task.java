package org.itstep;

import java.io.*;
import java.util.Objects;

public class Task {
    private String number;                  //    Номер
    private String name;                    //    Название
    private Significance significance;      //    Важность (от 1 до 5)
    private String deadline;                //    Срок выполнения (опционально)
    private Category category;              //    Категория (опционально)
    private Condition condition;            //    Архивирование выполненных задач (после архивирования исчезают из списка)

    public Task(String number, String name, Significance significance, String deadline, Category category, Condition condition) {
        this.number = number;
        this.name = name;
        this.significance = significance;
        this.deadline = deadline;
        this.category = category;
        this.condition = condition;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Significance getSignificance() {
        return significance;
    }

    public void setSignificance(Significance significance) {
        this.significance = significance;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return number.equals(task.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Task{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", significance=" + significance +
                ", deadline='" + deadline + '\'' +
                ", category=" + category +
                ", condition=" + condition +
                '}';
    }

}




