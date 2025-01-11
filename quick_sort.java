public class quick_sort extends Sorts{
    
    public quick_sort(int[] parameters)
    {
        super(parameters, parameters.length, "Quick Sort");
    }

    @Override
    public void sort()
    {
        startTime = System.nanoTime();
        quick(0, size-1);
        endTime = System.nanoTime();
        duration = endTime - startTime;
    }

    public void quick(int low, int high)
    {
        if (low < high) {
            int index = partition(low, high);
            quick(low, index-1);
            quick(index+1, high);
        }
    }
    
    public int partition(int low, int high)
    {   
        int pivot = parameters[high];
        int first = low, end = low;
        for(;end<high;end++)
        {
            if (parameters[end] < pivot) {
                swap(first++, end);
                duration = System.nanoTime() - startTime;
                saveTimeState();
                saveState();
            }
        }
        swap(first, high);
        duration = System.nanoTime() - startTime;
        saveTimeState();
        saveState();
        return first;
    }
}
