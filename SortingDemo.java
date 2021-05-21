import java.util.Random;
import java.util.Arrays;

public class SortingDemo
{
    public static void main (String args[])
    {
        QuickSort quickSort = new QuickSort();
        Random random = new Random();
        
        int arrayLength = 1000;
        int maxIntValue = 10000;
        
        int[] randomArray = new int[arrayLength];
        int[] sortedArray = new int[arrayLength];
        
        // array of random values
        for (int i = 0; i < arrayLength; i++)
        {
            randomArray[i] = random.nextInt(maxIntValue);
        }
        
        // array of sorted values
        for (int i = 0; i < arrayLength; i++)
        {
            sortedArray[i] = i;
        }
        
        System.out.println(Arrays.toString(randomArray));
        System.out.println(Arrays.toString(sortedArray));
        System.out.println();        
        
        long comparisons1 = quickSort.sort(randomArray, 3);
        long comparisons2 = quickSort.sort(sortedArray, 3);
        
        System.out.println(Arrays.toString(randomArray));
        System.out.println("Comparisons: " + comparisons1);
        
        System.out.println(Arrays.toString(sortedArray));
        System.out.println("Comparisons: " + comparisons2);        
        
    }
    
    
    
}
