package org.itstep;

import java.io.*;
import java.util.Scanner;

/**
 * Java. Lesson036. Task01
 * Personal list of task
 *
 * @author Semenyuk Alexander
 * Date 20.11.2022
 */
public class MenuTask01 {
    final static Scanner scanner = new Scanner(System.in);
    final static Scanner scanner1 = new Scanner(System.in);

    public static void main(String[] args) {
        int n;
        // work with users
        while (true) {
            System.out.print("1-add user, 2-remove user, 3-sign in, 0-exit >>> ");
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    User.addUsers();
                    break;
                case 2:
                    break;
                case 3:
                    while (true) {
                        System.out.print("Input login >>> ");
                        String loginUser = scanner1.nextLine();
                        System.out.print("Input password >>> ");
                        String passwordUser = scanner1.nextLine();
                        String total = loginUser + ":" + passwordUser;
                        if (User.checkUsers(total)) {       // work with task
                            System.out.println("Welcome");
                            workWithTask();
                            break;
                        } else {
                            System.out.println("Вы неправильно ввели данные: 1-pereat, 0-exit");
                            n = scanner.nextInt();
                            switch (n) {
                                case 1:
                                    break;
                                case 0:
                                    System.exit(0);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
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

    static void workWithTask() {
        System.out.println("1-List of active tasks (sort by significance and deadline)");
        System.out.println("2-List of archive tasks(sort by deadline to down), add user, 2-remove user, 3-sign in, 0-exit");
        System.out.println("3-Add new task");
        System.out.println("2-Close task (make by archive)");
        System.out.println("5-Remove Task");
        System.out.println("0-Exit");
        int n;
        while (true) {
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
