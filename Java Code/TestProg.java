


/* Caroline Lee
   8-24-2016
   Review Labs
   This is a test program that uses methods to find and return the average of 
   data in an array.
*/
import java.util.Scanner;
public class TestProg {
    
    public static void main(String[] args) {
        
        double array [] = new double[5];//test program: array length will be 5
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 5 numbers: ");//get array values from user
        for(int i = 0;  i < array.length; i++) {
            array[i] = input.nextDouble();//fill array with values 
        }
        
        System.out.println(average(array));//invoke method   
    }
    
    
    
    //method to find average if input values are integers
    public static int average(int[]arr) {
        int averageOfArray = (arr[0] + arr[1] + arr[2] + arr[3] + arr[4])/ arr.length;
        
        return averageOfArray; 
    }
    
    //overload 'average' method to find average if input values are doubles
    public static double average(double[]arr) {
        double averageOfArray = (arr[0] + arr[1] + arr[2] + arr[3] + arr[4])/ arr.length;
        
        return averageOfArray;  
    }
}
