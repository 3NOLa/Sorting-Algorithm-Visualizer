public class HeapSort extends Sorts{


    public HeapSort(int[] parameters)
    {
        super(parameters, parameters.length, "Heap Sort");
    }

    public void sort()
    {
        startTime = System.nanoTime();
        heap_sort(parameters);
        endTime = System.nanoTime();
        duration = endTime-startTime;
    }

    public void heapify(int arr[],int n,int i)
    {
        int largest = i;//index of the node(root)
        int left = i*2+1;//index of the left kid of the node
        int right = i*2+2;//index of the right kid of the node
        
        if(left < n && arr[left] > arr[largest]) largest = left;

        if(right < n && arr[right] > arr[largest]) largest = right;

        if(largest != i)//means the largest is one of the kids and not the root
        {
            swap(i, largest);
            duration = System.nanoTime() - startTime;
            saveTimeState();
            saveState();
            heapify(arr, n, largest);
        }
    }

    public void heap_sort(int arr[])
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
        {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--)
        {
            swap(0, i);
            duration = System.nanoTime() - startTime;
            saveTimeState();
            saveState();

            heapify(arr, i, 0);
        }
    }

}
