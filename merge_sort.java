public class merge_sort extends Sorts{
    
    public merge_sort(int[] parameters)
    {
        super(parameters, parameters.length, "Merge Sort");
    }    

    @Override
    public void sort()
    {
        startTime = System.nanoTime();
        main_merge(0, size-1);
        endTime = System.nanoTime();
        duration = endTime - startTime;
    }

    public void main_merge(int low,int high)
    {
        if(low < high)
        {
            int mid = low + (high-low)/2;
            main_merge(low, mid);
            main_merge(mid+1, high);
            merge(low, high);
        }
    }

    public void merge(int low,int high)
    {
        int mid = low + (high-low)/2;
        int n1 = mid - low+1;
        int n2 = high - mid;
        int[] first = new int[n1];
        int[] seconed = new int[n2];
        	
        int i;
        for(i=0;i<n1;i++)
            first[i] = parameters[low +i];
        for(i=0;i<n2;i++)
            seconed[i] = parameters[mid + 1 + i];

        int j=0,k=low;
        i=0;
        while(i<n1 && j<n2)
        {
            if(first[i] < seconed[j])
                parameters[k++] = first[i++];
            else
                parameters[k++] = seconed[j++];
            duration = System.nanoTime() - startTime;
            saveTimeState();    
            saveState();
        }

        while (i<n1) {
            parameters[k++] = first[i++];
            duration = System.nanoTime() - startTime;
            saveTimeState();
            saveState();
        }
        while (j<n2) {
            parameters[k++] = seconed[j++];
            duration = System.nanoTime() - startTime;
            saveTimeState();
            saveState();
        }
    }
}
