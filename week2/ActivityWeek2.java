
public class ActivityWeek2 {
    public static void main(String [] args){
       int []theaterRow = new int[8];

      theaterRow[3] = 1;
       int availableSeats = 0;
    for (int i = 1; i < theaterRow.length; i++){
            System.out.println("Seat" + i +": " + theaterRow[i]);
        
            if( theaterRow [i] == 0){
            availableSeats++;
        
        }
             System.out.println("Available seats" );
    } 
    }
}