package org.itstep;

import java.io.*;

public class Task {
    private String name;                  //    Название
    private Significance significance;    //    Важность (от 1 до 5)
    private String deadline;              //    Срок выполнения (опционально)
    private Category category;            //    Категория (опционально)
    private Condition condition;          //    Архивирование выполненных задач (после архивирования исчезают из списка)

    public Task(String name, Significance significance, String deadline, Category category, Condition condition) {
        this.name = name;
        this.significance = significance;
        this.deadline = deadline;
        this.category = category;
        this.condition = condition;
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
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", significance=" + significance +
                ", deadline='" + deadline + '\'' +
                ", category=" + category +
                ", condition=" + condition +
                '}';
    }

    static void writeTasks(String line) {
        try (OutputStream outputStream = new FileOutputStream("tasks.txt")) {
            outputStream.write(line.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String readTasks() {
        String rezult = "";
        try (InputStream in = new FileInputStream("tasks.txt")) {
            byte[] buffer = new byte[in.available()];
            int count = in.read(buffer);
            rezult = new String(buffer, 0, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rezult;
    }



}




