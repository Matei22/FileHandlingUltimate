package com.company;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static class JavaFileHandlingExample
    {
        public static void main(String args[]) {
            System.out.println("Please select one of the below operations");
            System.out.println(" w for write mode ");
            System.out.println(" r for read mode ");
            System.out.println(" a for append mode ");
            Scanner in =new Scanner(System.in);
            String s=in.nextLine();
            while (!s.equalsIgnoreCase("q")) {
                if (s.equalsIgnoreCase("r")) {
                    Scanner inputs=new Scanner(System.in);
                    System.out.println("Please choose your next operation after reading the file: ");
                    s = inputs.nextLine();
                    new FReading();
                } else if (s.equalsIgnoreCase("w") || s.equalsIgnoreCase("a")) {
                    Scanner inputs=new Scanner(System.in);
                    System.out.println("Please choose your next operation after writing/appending in the file: ");
                    s = inputs.nextLine();
                    writingToFile(s);
                } else {
                    Scanner inputs=new Scanner(System.in);
                    System.out.println("Please choose from \"q\", \"r\", \"w\", \"a\": ");
                    s = inputs.nextLine();
                }
            }
        }

        public static void writingToFile(String s)
        {
            Scanner in=null;
            try
            {
                String source = "";
                File f=new File("file1.txt");

                BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));FileWriter f0 =null;
                if(s.equalsIgnoreCase("w"))
                {
                    f0 = new FileWriter(f,false);
                    System.out.println("CAUTION >> Please understand it will overwrite the content of the file ");
                    System.out.println("Type 'no' to exit");
                    System.out.println("Do you want to proceed :type 'yes' ");
                    in=new Scanner(System.in);
                    String s1=in.nextLine();
                    if(s1.equals("no"))
                        System.exit(0);
                    System.out.println("Write 'stop' when you finish writing file ");
                    f.delete();
                    f.createNewFile();
                    while(!(source=bf.readLine()).equalsIgnoreCase("stop") && !source.equalsIgnoreCase("q")){
                        f0.write(source + System.getProperty("line.separator"));
                    }
                }
                else
                {  f0 = new FileWriter(f,true);
                    System.out.println("Write 'stop' when you finish appending file ");
                    while(!(source=bf.readLine()).equalsIgnoreCase("stop")){
                        f0.append(source).append(System.getProperty("line.separator"));
                    }
                }
                f0.close();
            }
            catch(Exception e){
                System.out.println("Error : " );
                e.printStackTrace();
            }
        }
    }
    static class FReading {
        public static String str="";

        public FReading() {

            try{
                File f5=new File("file1.txt");
                if(! f5.exists())
                    f5.createNewFile();
                FileReader fl=new FileReader(f5);
                BufferedReader bf=new BufferedReader(fl);
                //For reading till end
                while((str=bf.readLine())!=null){
                    System.out.println(str);
                }
                fl.close();
            }catch(Exception e){
                System.out.println("Error : " );
                e.printStackTrace();
            }
        }
    }
}
