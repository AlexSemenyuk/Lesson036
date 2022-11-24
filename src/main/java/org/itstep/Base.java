package org.itstep;

import java.io.*;
import java.util.*;

public class Base {
    final static Scanner scanner = new Scanner(System.in);
    final static Scanner scanner1 = new Scanner(System.in);
    private List<User> users;

    public Base() {
        this(new ArrayList<>());
    }

    public Base(List<User> users) {
        this.users = users;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Base{" +
                "users=" + users +
                '}';
    }

    public static Base createBaseFromFile(String fileUsers, String fileTasks) {
        Base rezult = new Base();
        List<User> userList;
        List<Task> taskList;
        // file users
        try (InputStream inUsers = new FileInputStream(fileUsers)) {
            int sizeUsers = inUsers.available();
            if (sizeUsers > 0) {
                byte[] buffer = new byte[sizeUsers];
                int countUser = inUsers.read(buffer);
                if (countUser == sizeUsers) {
                    String content = new String(buffer);
                    userList = Arrays.stream(content.split("\\n"))
                            .map(s -> s.split(","))
                            .map(arr -> new String[]{String.valueOf(arr[0]), String.valueOf(arr[1]), String.valueOf(arr[2])})
                            .map(arr -> new User(arr[0], arr[1], arr[2]))
                            .toList();
                    for (User user : userList) {
                        rezult.getUsers().add(user);
                    }
//                    rezult.setUsers(userList);        // Прошу объяснить почему так не работает - есть ограничения
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // file taskList
        try (InputStream inTasks = new FileInputStream(fileTasks)) {
            int sizeTasks = inTasks.available();
            if (sizeTasks > 0) {
                byte[] buffer = new byte[sizeTasks];
                int countTasks = inTasks.read(buffer);
                if (countTasks == sizeTasks) {
                    String content = new String(buffer);
                    Object [] array = Arrays.stream(content.split("\\n"))
                            .toArray();

                    String [] arr;
                    Significance significanceTask = null;
                    Category categoryTask = null;
                    Condition conditionTask = null;
                    Significance [] significances;
                    Category[] categories;
                    Condition[] conditions;

                    Task tmpTask;
                    for (int i = 0; i < array.length ; i++) {
                        arr = array[i].toString().split(",");

                        significances = Significance.values();
                        for (Significance significance: significances){
                            if (arr[3].equals(significance.mean())){
                                significanceTask = significance;
                            }
                        }

                        categories = Category.values();
                        for (Category category: categories){
                            if (arr[5].equals(category.mean())){
                                categoryTask = category;
                            }
                        }

                        conditions = Condition.values();
                        for (Condition condition: conditions){
                            if (arr[6].contains(condition.mean())){     // Потому что в конце \n
                                conditionTask = condition;
                            }
                        }

                        tmpTask = new Task(arr[1], arr[2], significanceTask, arr[4], categoryTask, conditionTask);
                        for (User user: rezult.getUsers()){
                           if (Integer.parseInt(arr[0]) == user.hashCode()){
                               user.getTasks().add(tmpTask);
                           }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rezult;
    }

    public void createFilesFromBase(String fileUsers, String fileTasks) {
        // file users
        try (OutputStream out = new FileOutputStream(fileUsers)) {
            for (User user : users) {
                out.write((user.getFullName() + "," + user.getLogin() + "," + user.getPassword() + "\n").getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // file tasks
        try (OutputStream out = new FileOutputStream(fileTasks)) {
            for (User user : users) {
                for (Task task : user.getTasks()) {
                    out.write((user.hashCode() + "," + task.getNumber() + "," + task.getName() + "," + task.getSignificance() +
                            "," + task.getDeadline() + "," + task.getCategory() + "," + task.getCondition() + "\n").getBytes());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void addUser() {
        boolean rezult = false;
        System.out.print("Input fullName >>> ");
        String fullNameUser = scanner.nextLine();
        String[] loginAndPassword = InputLoginAndPassword();
        String loginUser = loginAndPassword[0];
        String passwordUser = loginAndPassword[1];
        User user = new User(fullNameUser, loginUser, passwordUser);
        boolean check = checkUser(loginAndPassword);
        if (!check) {
            rezult = getUsers().add(user);
            if (rezult) {
                System.out.println("Add user is successful");
            } else {
                System.out.println("Add user isn't successful");
            }
        } else {
            System.out.println("User with such login and password is existing");
        }
    }


    public void removeUser(User tmp) {
        boolean rezult;
        rezult = getUsers().remove(tmp);
        if (rezult) {
            System.out.println("Remove user is successful");
        } else {
            System.out.println("Remove user isn't successful");
        }
    }


    public static String[] InputLoginAndPassword() {
        String[] rezult = new String[2];
        System.out.print("Input login >>> ");
        rezult[0] = scanner1.nextLine();
        System.out.print("Input password >>> ");
        rezult[1] = scanner1.nextLine();
        return rezult;
    }


    public boolean checkUser(String[] loginAndPassword) {
        boolean rezult = false;
        String loginUser = loginAndPassword[0];
        String passwordUser = loginAndPassword[1];
        for (User user : users) {
            if (user.getLogin().equals(loginUser) && user.getPassword().equals(passwordUser)) {
                rezult = true;
                break;
            }
        }
        return rezult;
    }

    public User findUser(String[] loginAndPassword) {
        User tmp = null;
        String loginUser = loginAndPassword[0];
        String passwordUser = loginAndPassword[1];
        for (User user : this.getUsers()) {
            if (loginUser.equals(user.getLogin()) && passwordUser.equals(user.getPassword())) {
                tmp = user;
                break;
            }
        }
        return tmp;
    }


}
