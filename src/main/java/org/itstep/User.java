package org.itstep;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class User {
    final static Scanner scanner = new Scanner(System.in);
    final static Scanner scanner1 = new Scanner(System.in);
    private String fullName;
    private String login;
    private String password;
    private List<Task> tasks;

    public User(String fullName, String login, String password) {
        this(fullName, login, password, new ArrayList<>());
    }

    public User(String fullName, String login, String password, List<Task> tasks) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.tasks = tasks;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", tasks=" + tasks +
                '}';
    }


    public void workWithTask() {

        int n;
        String line = "";
        label:
        while (true) {
            System.out.println("1-Print list of active tasks (sort by significance and deadline)");
            System.out.println("2-Print list of archive tasks(sort by deadline to down)");
            System.out.println("3-Add new task");
            System.out.println("4-Close task (make by archive)");
            System.out.println("5-Remove Task");
            System.out.println("0-Exit");
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    line = "Active";
                    printTasks(line);
                    break;
                case 2:
                    line = "Archive";
                    printTasks(line);
                    break;
                case 3:
                    addTask();
                    break;
                case 4:
                    closeTask();
                    break;
                case 5:
                    removeTask();
                    break;
                case 0:
                    break label;
                default:
                    System.out.println("Вы неправильно ввели данные");
                    break;
            }
        }
    }


    public void printTasks(String line) {
        if (line.equals("Active")) {
            getTasks().stream()
                    .filter(x -> x.getCondition().mean().contains(line))
                    .sorted(new comparatorActiveTask())
                    .forEach(System.out::println);
        }
        if (line.equals("Archive")) {
            getTasks().stream()
                    .filter(x -> x.getCondition().mean().contains(line))
                    .sorted(new comparatorCloseTask())
                    .forEach(System.out::println);
        }
    }


    public void addTask() {
        boolean check = false;
        String line;
        String numberTask;
        while (true) {                                  // 1. numberTask
            System.out.print("Input numberTask >>> ");
            line = scanner1.nextLine();
            for (Task task : getTasks()) {
                if (task.getNumber().equals(line)) {
                    check = true;
                }
            }
            if (check) {
                System.out.println("This numberTask is existing");
            } else {
                numberTask = line;
                break;
            }
        }
        System.out.print("Input name >>> ");
        String nameTask = scanner1.nextLine();           // 2. nameTask

        Significance significanceTask = null;            // 3. significanceTask
        Significance[] significances;
        significances = Significance.values();
        while (true) {
            check = false;
            System.out.print("input significanceTask (1...5) >>> ");
            line = scanner1.nextLine();
            for (Significance significance : significances) {
                if (line.equals(significance.mean())) {
                    significanceTask = significance;
                    check = true;
                }
            }
            if (check) {
                break;
            } else {
                System.out.println("Вы неправильно ввели данные");
            }
        }

        System.out.print("Input deadlineTask (dd:mm:yyyy) >>> ");      // 4. deadlineTask
        String deadlineTask = scanner1.nextLine();

        Category categoryTask = null;                                  // 5. categoryTask
        Category[] categories;
        categories = Category.values();
        while (true) {
            check = false;
            System.out.print("input categoryTask (A...E) >>> ");
            line = scanner1.nextLine();
            for (Category category : categories) {
                if (line.equalsIgnoreCase(category.mean())) {
                    categoryTask = category;
                    check = true;
                }
            }
            if (check) {
                break;
            } else {
                System.out.println("Вы неправильно ввели данные");
            }
        }

        Condition conditionTask = null;
        Condition[] conditions;
        conditions = Condition.values();
        while (true) {                                                      // 6. conditionTask
            check = false;
            System.out.print("input category (Active / Archive) >>> ");
            line = scanner1.nextLine();
            for (Condition condition : conditions) {
                if (line.equalsIgnoreCase(condition.mean())) {
                    conditionTask = condition;
                    check = true;
                }
            }
            if (check) {
                break;
            }
        }

        Task task = new Task(numberTask, nameTask, significanceTask, deadlineTask, categoryTask, conditionTask);
        getTasks().add(task);
    }


    private void removeTask() {
        Task tmp = findTask();
        getTasks().remove(tmp);
    }


    public void closeTask() {
        Task tmp = findTask();
        tmp.setCondition(Condition.ARCHIVE);
    }

    public Task findTask() {
        System.out.print("Input numberTask >>> ");
        String line = scanner1.nextLine();
        Task rezult = null;
        for (Task task : getTasks()) {
            if (task.getNumber().equals(line)) {
                rezult = task;
                break;
            }
        }
        return rezult;
    }


}

class comparatorCloseTask implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        String[] arr1 = ((Task) o1).getDeadline().split(":");
        String[] arr2 = ((Task) o2).getDeadline().split(":");
        if (arr1[2].equals(arr2[2])) {
            if (arr1[1].equals(arr2[1])) {
                return arr2[0].compareTo(arr1[0]);
            } else {
                return arr2[1].compareTo(arr1[1]);
            }
        }
        return arr2[2].compareTo(arr1[2]);
    }
}

class comparatorActiveTask implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        String[] arr1 = ((Task) o1).getDeadline().split(":");
        String[] arr2 = ((Task) o2).getDeadline().split(":");
        if (((Task) o1).getSignificance().equals(((Task) o2).getSignificance())) {
            if (arr1[2].equals(arr2[2])) {
                if (arr1[1].equals(arr2[1])) {
                    return arr1[0].compareTo(arr2[0]);
                } else {
                    return arr1[1].compareTo(arr2[1]);
                }
            } else {
                return arr1[2].compareTo(arr2[2]);
            }
        }
        return ((Task) o1).getSignificance().compareTo(((Task) o2).getSignificance());
    }
}