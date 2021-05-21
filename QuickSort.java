import java.util.Random;
import java.util.Arrays;

public class QuickSort
{
    //**Notes: Comparisons as field member ?
    public long comparisons;
    
    // Constructor
    public QuickSort()
    {
        comparisons = 0;
    }

    public long sort(int[] a, int sortType)
    {
        comparisons = 0;
        quickSort(a, 0, a.length - 1, sortType);
        
        return comparisons;
    }

    //**Notes: Sort Type ?
    public void quickSort(int[] a, int lo, int hi, int sortType)
    {
        
        int p;
        int initialPivot = -1;
                    
        Random rand = new Random();
        
        if (hi > lo)
        {
            // **Note: Create methods for each case ?
            switch(sortType)
            {
                
                // 1 = Deterministic sort, initial pivot is at 'hi'
                case 1:
                    initialPivot = hi;
                    break;
                    
                // 2 = Random sort, initial pivot is chosen randomly
                case 2:
                    initialPivot = lo + rand.nextInt(hi - lo + 1);
                    break;    

                // 3 = Median Sample
                case 3:
                {
                    QuickSort subQuick = new QuickSort();
                    
                    int sampleSize;
                    int subArrayLength = hi - lo;

                    if (subArrayLength < 10)
                    {
                        initialPivot = lo + rand.nextInt(hi - lo + 1);
                    }
                    else
                    {
                        if (subArrayLength < 100)
                            sampleSize = 3;
                        else if (subArrayLength < 1000)
                            sampleSize = 10;
                        else if (subArrayLength < 10000)
                            sampleSize = 15;
                        else if (subArrayLength < 100000)
                            sampleSize = 20;
                        else if (subArrayLength < 1000000)
                            sampleSize = 25;
                        else
                            sampleSize = 50;

                        int[] tempIndexArray = null;
                        int[] tempValueArray = null;
                        
                        tempIndexArray = new int[sampleSize];       // stores the indexes
                        tempValueArray = new int[sampleSize];       // stores the values of the elements at the index
                        
                        // Get a sample
                        for(int i = 0; i < sampleSize; i++)
                        {
                            int randIndex = lo + rand.nextInt(hi - lo + 1);
                            tempIndexArray[i] = randIndex;
                            tempValueArray[i] = a[tempIndexArray[i]];
                            comparisons++;
                        }
                        
                        long additionalRunTime = subQuick.sort(tempValueArray, 2);
                        comparisons += additionalRunTime;
                        
                        // get the median
                        int midIndex = (tempValueArray.length - 1) / 2;
                        int median = tempValueArray[midIndex];
                        
                        int counter = 0;
                        
                        // our very primitive method of finding the index of the original array that contains the median value
                        while (!(a[tempIndexArray[counter]] == median))
                        {
                            counter++;
                        }

                        initialPivot = tempIndexArray[counter];
                    }
                    
                    break;
                }
                default:

            }
                    
            p = partition(a, lo, hi, initialPivot);    // parition the array, p = correct pivot placement index
            quickSort(a, lo, p - 1, sortType);        // sort lower half
            quickSort(a, p + 1, hi, sortType);        // sort bigger half
        }
    }
    
    private int partition(int[] a, int lo, int hi, int r)
    {
        int j;  // index for 'bigger' half
        int i;  // index for 'lower' half
        int x;  // pivot element value
        
        swap(a, r, hi);     // swap pivot with 'hi'
        j = hi - 1;
        i = lo;
        x = a[hi];
        while (i <= j)
        {
            comparisons++;
            
            if (a[i] <= x)
            {
                i++;
            } else
            {
                swap(a, i, j);
                j--;
            }
        }
        swap(a, hi, j + 1);
        
        return (j + 1);
    }
    
    private void swap(int[] array, int y, int z)
    {
        int temp = array[y];
        
        array[y] = array[z];
        array[z] = temp;
    }
}