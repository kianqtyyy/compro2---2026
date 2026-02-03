import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CsvApp {
    static String[] names;
    static double[][] grades;

    public static void main(String[] args) {
        names = new String[5];
        grades = new double[5][3];

        Scanner sc = new Scanner(System.in);
         




        for (int r = 0; r < 5; r++) {
            System.out.print("Subject: ");
            names[r] = sc.nextLine();

            System.out.print("Prelim: ");
            try {
                grades[r][0] = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            }

            System.out.print("Midterm: ");
            try {
                grades[r][1] = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            }
            System.out.println("Finals: ");
            try { 
                grades[r][2] = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            }

            sc.nextLine();
            System.out.println();
        }
        writeData();

    }

    public static void writeData() {
        StringBuilder sb = new StringBuilder();

        sb.append("Subject,Prelims,midterm,finals\n");
        for (int r = 0; r < names.length; r++) {
            if(names[r] == null) 
                break;

            sb.append(names[r]);
            for (int c = 0; c < grades[r].length; c++) {
                sb.append(", ").append(grades[r][c]);
            }
            sb.append("\n");
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data.csv"))){
            bw.write(sb.toString());
            bw.flush();
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(sb.toString());
    }
}