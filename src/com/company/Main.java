package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("---------------------------------------");
        System.out.println("Name of the prototype: File Handler");
        System.out.println("---------------------------------------");
        System.out.println("Name of the developer: Matei Havarneanu");
        System.out.println("Address: Iasi, jud. Iasi, Romania");
        System.out.println("Age: 23");
        System.out.println("---------------------------------------");

        textForMainContext();
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        while (!s.equalsIgnoreCase("quit")) {
            switch (s) {
                case "files" -> {
                    theMainOptions("files", in);
                    textForMainContext();
                    s = in.nextLine();
                }
                case "user" -> {
                    textForUserUtilities();
                    s = in.nextLine();
                    while (!s.equalsIgnoreCase("back")) {
                        switch (s) {
                            case "add" -> {
                                theMainOptions("add", in);
                                textForUserUtilities();
                            }
                            case "delete" -> {
                                theMainOptions("delete", in);
                                textForUserUtilities();
                            }
                            case "search" -> {
                                theMainOptions("search", in);
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

    public static List<String> listOfUsers() throws IOException{
        Path p = Paths.get("./Users");
        List<Path> listOfPaths = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        Files.list(p).forEach(listOfPaths::add);
        for (Path listOfPath : listOfPaths) {
            stringList.add(listOfPath.getName(2).toString());
        }
        return stringList;
    }

    public static void theMainOptions(String s, Scanner in) throws IOException {

        List<String> stringList = listOfUsers();

        if(Objects.equals(s, "add") || Objects.equals(s, "delete")) {
            System.out.println("Please choose from which user do you want to " + s + " a file " +
                    "(You can choose between: " + stringList + "): ");

            String inputTheUser = in.nextLine();
            while (!stringList.contains(inputTheUser)) {
                System.out.println("Please choose between: ");
                System.out.println(stringList);
                inputTheUser = in.nextLine();
            }
            System.out.println("Which file do you want to " + s + ": ");
            if (Objects.equals(s, "add")) {
                addFile(inputTheUser, in);
            }
            if (Objects.equals(s, "delete")) {
                deleteFile(inputTheUser, in);
            }

        }else if(Objects.equals(s, "files")) {
            List<Element> list = new ArrayList<>();
            ArrayList<String> fileNames = new ArrayList<>(filesInAscendingOrder());
            for(String name: fileNames){
                list.add(new Element(name));
            }
            System.out.println();
            System.out.println("Sorted with BubbleSort:");
            System.out.print("Not sorted: ");
            list.forEach(e -> System.out.print("\"" + e.getId() + "\" "));
            bubbleSortArrayList(list);
            System.out.println();
            System.out.print("Sorted: ");
            list.forEach(e -> System.out.print("\"" + e.getId() + "\" "));

            System.out.println();
            System.out.println();
            System.out.println("Sorted with Collection.sort(): ");
            ArrayList<String> nameInAscendingOrder = new ArrayList<>(filesInAscendingOrder());

            System.out.println("Not sorted yet: " + nameInAscendingOrder);
            Collections.sort(nameInAscendingOrder);
            System.out.println("Sorted in ascending order: " + nameInAscendingOrder);
            System.out.println();
        }else if(Objects.equals(s, "search")){
            List<String> fileNames = new ArrayList<>(filesInAscendingOrder());
            System.out.println("Please choose which file do you want to search: ");
            String inputTheUser = in.nextLine();
            if(fileNames.contains(inputTheUser)){
                System.out.println("File: \"" + inputTheUser + "\" exist");
            }else{
                System.out.println("File: \"" + inputTheUser + "\" doesn't exist");
            }
        }
    }

    public static Collection<String> filesInAscendingOrder() throws IOException {
        Collection<String> fileNames = new TreeSet<>(Collator.getInstance());
        for(String user: listOfUsers()){
            File dir = new File("./Users/" + user + "/");
            File[] files = dir.listFiles();

            assert files != null;
            Arrays.sort(files, File::compareTo);

            for (File file : files) {
                if (!file.isHidden()) {
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    }
                }
            }
        }
        return fileNames;
    }

    public static void addFile(String user, Scanner in) throws IOException {
        String fileName = in.nextLine();
        Path p1 = Paths.get("./Users/" + user + "/" + fileName);

        if(!Files.exists(p1)) {
            System.out.println("Created successfully");
            Files.createFile(p1);
        }else System.out.println("The file already exists");
    }

    public static void deleteFile(String user,  Scanner in) throws IOException {
        String fileName = in.nextLine();
        Path p1 = Paths.get("./Users/" + user + "/" + fileName);
        if(Files.deleteIfExists(p1))
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
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

    public static void bubbleSortArrayList(List<Element> list) {
        Element temp;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size()-1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }

}
