package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static class JavaFileHandlingExample {
        public static void main(String[] args) throws IOException {

            textForMainContext();
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();

            while (!s.equalsIgnoreCase("quit")) {
                switch (s) {
                    case "files" -> {
                        System.out.println("You chose to see the current file names in ascending order: ");
                        filesInAscendingOrder("./Users");
                        textForMainContext();
                        s = in.nextLine();
                    }
                    case "user" -> {
                        textForUserUtilities();
                        s = in.nextLine();
                        while (!s.equalsIgnoreCase("back")) {
                            switch (s) {
                                case "add" -> {
                                    findWhichUser("add", in);
                                    textForUserUtilities();
                                }
                                case "delete" -> {
                                    findWhichUser("delete", in);
                                    textForUserUtilities();
                                }
                                case "search" -> {
                                    findWhichUser("search", in);
                                    textForUserUtilities();
                                }
                                case "back" -> {
                                }
                                default -> System.out.println("Please choose from \"add\", \"delete\", \"search\", \"back\": ");
                            }
                            s = in.nextLine();
                        }
                        textForMainContext();
                        s = in.nextLine();
                    }
                    case "quit" -> System.out.println("You chose to quit the program");
                    default -> {
                        System.out.println("Please choose from \"files\", \"user\", \"quit\": ");
                        s = in.nextLine();
                    }
                }
            }
        }

        public static void findWhichUser(String s, Scanner in) throws IOException {

            System.out.println("Please choose from which user do you want to " + s + " a file: ");
            Path p = Paths.get("./Users");
            List<Path> listOfPaths = new ArrayList<>();
            List<String> stringList = new ArrayList<>();
            Files.list(p).forEach(listOfPaths::add);
            for (Path listOfPath : listOfPaths) {
                stringList.add(listOfPath.getName(2).toString());
            }
            String inputTheUser = in.nextLine();
            while(!stringList.contains(inputTheUser)){
                System.out.println("Please choose between: ");
                System.out.println(stringList);
                inputTheUser = in.nextLine();
            }
            System.out.println("Which file do you want to " + s + ": ");
            if (Objects.equals(s, "add")) {
                addFile(inputTheUser, in);
            }
        }

        public static void addFile(String user, Scanner in) throws IOException {
            String fileName = in.nextLine();
            Path p1 = Paths.get("./Users/" + user + "/" + fileName);

            if(!Files.exists(p1)) {
                Files.createFile(p1);
            }else System.out.println("The file already exists");

        }

        public static void textForUserUtilities() {
            System.out.println("Please select one of the below operations: ");
            System.out.println("1) \"add\" to add a user specified file to the application");
            System.out.println("2) \"delete\" to delete a user specified file from the application");
            System.out.println("3) \"search\" to search a user specified file from the application ");
            System.out.println("4) \"back\" to navigate back to the main context");
        }

        public static void textForMainContext() {
            System.out.println("Please select one of the below operations: ");
            System.out.println("1) \"files\" to return the current file names in ascending order");
            System.out.println("2) \"user\" for the details of the user interface");
            System.out.println("3) \"quit\" to close the application");
        }

        public static void deleteFile() {

        }

        public static void searchFile(String fileName, String user) throws IOException {
            Path p = Paths.get("./Users/" + user);
            Files.find(
                    p,
                    Integer.MAX_VALUE,
                    (path, fa) -> path.getFileName().toString().endsWith(fileName)
            ).forEach((System.out::println));
        }

        public static void filesInAscendingOrder(String s) {
            Path p1 = Paths.get(s);
            System.out.println(p1.getNameCount());
            for (int i = 0; i < p1.getNameCount(); i++) {
                System.out.println(i + " " + p1.getName(i));
            }
        }
    }
}
