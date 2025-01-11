import java.util.ArrayList;
import java.util.Collections;

public class bucket_sort extends Sorts{
    

    public bucket_sort(int[] parameters)
    {
        super(parameters, parameters.length, "Bucket Sort");
    }

    public void sort()
    {
        startTime = System.nanoTime();
        int i;
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for(i=0;i<10;i++)
            buckets[i] = new ArrayList<>();
        
        int max = parameters[0];
        int min = parameters[0];
        for(i=0;i<size;i++)
        {
            if(max < parameters[i])max = parameters[i];
            if(min > parameters[i])min = parameters[i];
        }
        duration = System.nanoTime() - startTime;
        saveTimeState();
        saveState();
        // If all elements are the same, handle this case
        if (max == min) {
            return;  // No sorting is necessary
        }

        for(i=0;i<size;i++)
        {
            int bucket = (parameters[i] - min)*9 / (max-min);
            buckets[bucket].add(parameters[i]); 
            duration = System.nanoTime() - startTime;
            saveTimeState();
            saveState();
        }

        duration = System.nanoTime() - startTime;
        saveTimeState();
        saveState();

        for(i=0;i<10;i++)
        {
            Collections.sort(buckets[i]);
        }

        int k=0;
        for(i=0;i<10;i++)
        {
            while(!buckets[i].isEmpty())
            {
                parameters[k++] = buckets[i].remove(0);
                duration = System.nanoTime() - startTime;
                saveTimeState();
                saveState();
            }
        }
        duration = System.nanoTime() - startTime;
        saveTimeState();
        saveState();

    }
}
