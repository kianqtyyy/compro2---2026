import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;

public class Main{
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        
        try(Scanner sc = new Scanner(System.in)){
            System.out.print("Enter first name: ");
            sb.append("First name: ");
            sb.append(sc.nextLine());
            sb.append("\n");

            System.out.print("Enter last name: ");
            sb.append("Last name: ");
            sb.append(sc.nextLine());
            sb.append("\n");

            System.out.print("Age: ");
            sb.append("Age: ");
            sb.append(sc.nextLine());
            sb.append("\n");

            System.out.print("Enter gmail: ");
            sb.append("gmail: ");
            sb.append(sc.nextLine());
            sb.append("\n");

            System.out.print("Enter Phone num: ");
            sb.append("Phone num: ");
            sb.append(sc.nextLine());
            sb.append("\n");

        }catch(InputMismatchException e){
            System.out.println("Invalid input");
        }


        try(FileWriter fw = new FileWriter("data.txt")){
            fw.write(sb.toString());
            System.out.println("Data is saved...");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}