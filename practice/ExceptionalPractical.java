package practice;


import java.util.*;
public class ExceptionalPractical {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter A Number");
     try{
        int Number = sc.nextInt();
     }catch(Exception e){
        System.out.println("Invalid Number.");
     }
        System.out.println("Program shut down.");
        
    
    }
}
