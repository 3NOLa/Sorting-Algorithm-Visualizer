public class selection_sort extends Sorts{
    
    public selection_sort(int[] parameters)
    {
        super(parameters, parameters.length,"Selection Sort");
    }

    @Override
    public void sort()
    {
        startTime = System.nanoTime();
        int index;
        int i,j;
        for(i=0;i<size-1;i++)
        {
            index = i;
            int min = parameters[i];
            for(j=i+1;j<size;j++)
            {
                if (min > parameters[j]) 
                {
                    min = parameters[j];
                    index = j;
                    saveState();
                }
            }
            if(i != index)
            {
                swap(i, index);
                duration = System.nanoTime() - startTime;
                saveTimeState();
                saveState();
            }
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
    }
}
