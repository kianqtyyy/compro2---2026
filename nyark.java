
import java.util.*;
public class nyark{
    public static void main(String [] args){
        Scanner nyek = new Scanner(System.in);
        int array[] = {1, 2, 3, 4};
        int index = -1;

        for (int i = 0; i <= array.length -1; i++) {
            System.out.println(array[i]);
        }
         System.out.println("Put a Number ");
         int chosen = nyek.nextInt();
         for (int i = 0;i <= array.length - 1; i++){
            if( array[i] == chosen){
                System.out.println(array[i]);
            }
         }
    }
} 