package org.itstep;

import java.util.Scanner;

/**
 * Java. Lesson036. Task01
 * Personal list of task
 * @author Semenyuk Alexander
 * Date 20.11.2022
 */
public class Menu01 {
    final static Scanner scanner = new Scanner(System.in);
    final static Scanner scanner1 = new Scanner(System.in);

    public static void main(String[] args) {
        String fileUsers = "users.txt";
        String fileTasks = "tasks.txt";
        int n;
        Base base;
        User tmp = null;
        String[] arr;
//        Task task = new Task("№45", "Стройка", Significance.THREE, "01.02.2023", Category.C, Condition.ACTIVE);
//        System.out.println(task);
//        User user1 = new User("Пахомов Андрей Алексеевич", "pahomov_a", "12345");
//        User user2 = new User("Иванова Анна Петровна", "ivanova_a", "33333");

        // Work with users
        while (true) {
            System.out.print("1-sign in, 2-add user, 3-remove user, 0-exit >>> ");
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    base = Base.createBaseFromFile(fileUsers, fileTasks);
                    arr = Base.InputLoginAndPassword();
                    if (base.checkUser(arr)) {
                        tmp = base.findUser(arr);
                        System.out.println("Your sin in is successful");
                        tmp.workWithTask();                // work with tasks
                    }
                    base.createFilesFromBase(fileUsers, fileTasks);
                    break;
                case 2:
                    base = Base.createBaseFromFile(fileUsers, fileTasks);
                    base.addUser();
                    base.createFilesFromBase(fileUsers, fileTasks);
                    break;
                case 3:
                    base = Base.createBaseFromFile(fileUsers, fileTasks);
                    arr = Base.InputLoginAndPassword();
                    tmp = base.findUser(arr);
                    base.removeUser(tmp);
                    base.createFilesFromBase(fileUsers, fileTasks);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Вы неправильно ввели данные");
                    break;
            }
        }


    }

}
