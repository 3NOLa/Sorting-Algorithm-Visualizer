import java.util.ArrayList;

public class radix_sort extends Sorts{

    
    public radix_sort(int[] parameters)
    {
        super(parameters, parameters.length, "Radix Sort");
    }

    public void sort()
    {
        startTime = System.nanoTime();
        int i;
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for(i=0;i<10;i++)
            buckets[i] = new ArrayList<>();

        int max = parameters[0];
        for(int number : parameters)
        {
            if(number > max)max = number;
        }
        duration = System.nanoTime() - startTime;
        saveTimeState();
        saveState();

        int exp =1;
        while(max / exp > 0)
        {    
            for(i=1;i<size;i++)
            {
                int radixIndex = (parameters[i] / exp) % 10;
                buckets[radixIndex].add(parameters[i]);
                duration = System.nanoTime() - startTime;
                saveTimeState();
                saveState();
            }

            int k=0;
            for(ArrayList<Integer> bucket : buckets)
            {
                while(!bucket.isEmpty())
                    parameters[k++] = bucket.remove(0);
                    duration = System.nanoTime() - startTime;
                    saveTimeState();
                    saveState();
            }

            exp *=10;
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
    }
}