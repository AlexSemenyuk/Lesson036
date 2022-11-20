package org.itstep;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class User {

    final static Scanner scanner = new Scanner(System.in);
    final static Scanner scanner1 = new Scanner(System.in);
    private String login;
    private String password;
    private String fullName;

    public User(String login, String password, String fullName) {
        this.login = login;
        this.password = password;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        return fullName + "-" + login + ":" + password + "\n";
    }


    static void writeUsers(String line) {
        try (OutputStream outputStream = new FileOutputStream("users.txt")) {
            outputStream.write(line.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String readUsers() {
        String rezult = "";
        try (InputStream in = new FileInputStream("users.txt")) {
            byte[] buffer = new byte[in.available()];
            int count = in.read(buffer);
            rezult = new String(buffer, 0, count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rezult;
    }

    static boolean checkUsers(String line) {
        boolean rezult = false;
        String file = readUsers();
        if (file.contains(line)) {
            rezult = true;
        }
        return rezult;
    }

    static void addUsers() {
        while (true) {
            System.out.print("Input login >>> ");
            String loginUser = scanner.nextLine();
            System.out.print("Input password >>> ");
            String passwordUser = scanner.nextLine();
            System.out.print("Input fullName >>> ");
            String fullNameUser = scanner.nextLine();
            User user = new User(loginUser, passwordUser, fullNameUser);
            String total = loginUser + ":" + passwordUser;
            if (!checkUsers(total)){
                String file = readUsers() + user.toString();
                writeUsers(file);
                System.out.println("Addition of user is sucsecfull");
                break;
            } else {
                System.out.println("user is existing");
            }
        }
    }

}
