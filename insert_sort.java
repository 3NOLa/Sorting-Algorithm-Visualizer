public class insert_sort extends Sorts{
    
    public insert_sort(int[] parameters)
    {
        super(parameters, parameters.length,"Insert Sort");
    }

    @Override
    public void sort()
    {
        startTime = System.nanoTime();
        int i,j;
        for(i=0;i<size;i++)
        {
            int key = parameters[i];
            j = i-1;

            while (j>=0 && parameters[j] > key)
            {
                parameters[j+1] = parameters[j--];
                duration = System.nanoTime() - startTime;
                saveTimeState();
                saveState();
            }
            
            parameters[j+1] = key;
            duration = System.nanoTime() - startTime;
            saveTimeState();
            saveState();
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
    }
}
