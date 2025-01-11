public class bubble_sort extends Sorts{

    boolean swap;

    public bubble_sort(int[] parameters)
    {
        super(parameters, parameters.length,"Bubble Sort");
        swap = true;
    }

    @Override
    public void sort()
    {
        startTime = System.nanoTime();
        int i,j;
        for(i=0;i<super.size-1;i++)
        {
            for(j=0;j<super.size-i-1;j++)
            {
                if(parameters[j] > parameters[j+1])
                {
                    super.swap(j, j+1);
                    duration = System.nanoTime() - startTime;
                    saveTimeState();
                    saveState();
                    swap = true;
                }

            }
            if(!swap)break;
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
    }
}