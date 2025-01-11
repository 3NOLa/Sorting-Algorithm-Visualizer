import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

abstract class Sorts implements Comparable{

    private static int sortscount;
    static{
        sortscount = 0;
    }
    protected int[] parameters;
    protected int size;
    protected String name;
    protected long duration;
    protected int Id;
    protected long startTime;
    protected long endTime;
    protected ArrayList<int[]> steps = new ArrayList<>();
    protected ArrayList<Long> timeSteps = new ArrayList<>();
    //protected Queue<ArrayChange> changes = new LinkedList<ArrayChange>();
    protected int currentStep;


    public Sorts(int[] parameters,int size,String name)
    {
        sortscount++;
        this.currentStep=0;
        this.Id = sortscount;
        this.size = size;
        this.name = name;
        this.duration = 0;
        this.parameters =  new int[this.size];
        for(int i=0;i<size;i++)
        {
            this.parameters[i] = parameters[i];
        }
        steps.add(this.parameters.clone());
    }

    public void swap(int i, int j)
    {
        int temp = parameters[j];
        parameters[j] = parameters[i];
        parameters[i] =temp;
    }

    public abstract void sort();
    
    public static int getsortscount()
    {
        return sortscount;
    }

    public void shuffle()
    {
        Random r = new Random();
        for(int i=size-1;i>=0;i--)
        {
            int rand = r.nextInt(i+1);
            swap(i, rand);
        }
    }

    public int[] getArrayState() {
        return !steps.isEmpty() ? steps.remove(0) : parameters;
    }

    protected void saveState()
    {   
        steps.add(parameters.clone());
    }

    // protected void newChange(ArrayChange change)
    // {
    //     changes.add(change);
    // }
    // public ArrayChange getLastChange()
    // {
    //     return !changes.isEmpty()?changes.remove():null;
    // }

    protected void saveTimeState()
    {
        timeSteps.add(duration);
    }
    public long getCurrenttime()
    {
        return !timeSteps.isEmpty() ? timeSteps.remove(0) : duration;
    }

    public void printlist()
    {
        for(int parm : parameters)
        {
            System.out.printf("[%d]\t",parm);
        }
        System.out.println();
    }

    @Override
    public String toString()
    {
        return "Sort type is: " + name + " durtion to sort the array is: " + duration/ 1_000_000_000.0 + "s" ; 
    }

    @Override
    public int compareTo(Object o)
    {
        if (!(o instanceof Sorts)) {
            throw new ClassCastException();
        }
        Sorts obj = (Sorts) o;
        return Long.compare(this.duration,obj.duration);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(parameters);
        result = prime * result + size;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (int) (duration ^ (duration >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sorts other = (Sorts) obj;
        if (!Arrays.equals(parameters, other.parameters))
            return false;
        if (size != other.size)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (duration != other.duration)
            return false;
        return true;
    }

    // public static void main(String[] args) {
    //     System.out.println(fina());
    //     Scanner s = new Scanner(System.in);
    //     s.equals(s);
    //     int arr[];
    //     Sorts[] listOfSorts = new Sorts[5];
    //     System.out.print("number of values in the unsorted list:");
    //     int amount=0;
    //     while (true) {
    //         try{
    //             amount = s.nextInt();
    //             break;
    //         }
    //         catch(InputMismatchException ime)
    //         {
    //             System.out.println("you didnt enter a number try again");
    //             s.nextLine();
    //         }
    //     }
        
    //     arr = new int[amount]; 
    //     for(int i =0;i<amount;i++)
    //     {
    //         arr[i] = (int)(Math.random() * 1000000);
    //     }
    //     Sorts sort;
    //     System.out.println("choose a sort type");
    //     System.out.print("(1)==bubble sort\n(2)==insert sort\n(3)==selection sort\n(4)==merge sort\n(5)==quicksort: ");
    //     int choice=5;
    //     while (true) {
    //         try{
    //             choice = s.nextInt();
    //             break;
    //         }
    //         catch(InputMismatchException ime)
    //         {
    //             System.out.println("you didnt enter a number try again");
    //             s.nextLine();
    //         }
    //     }
    //     switch (choice) {
    //         case 1:
    //             sort = new bubble_sort(arr);
    //             bubble_sort a= (bubble_sort) sort;
    //             boolean flag = a.swap;
    //             boolean flag2 = ((bubble_sort) sort).swap;
    //             break;
    //         case 2:
    //             sort = new insert_sort(arr);
    //             break;
    //         case 3:
    //             sort = new selection_sort(arr);
    //             break;
    //         case 4:
    //             sort = new merge_sort(arr);
    //             break;
    //         case 5:
    //             sort = new quick_sort(arr);
    //             break;    
    //         default:
    //             sort = new quick_sort(arr);
    //             break;
    //     }
    //     sort.printlist();
    //     sort.sort();
    //     System.out.printf("Sort Type: %s \nduration of the sort: %.10fs\n",sort.name,sort.duration/ 1_000_000_000.0);
    //     sort.printlist();
    //     System.out.println(Sorts.getsortscount());
    //     int a =5;
    //     double b = 7.8;
    //     float d = 4;
    //     int c = (int) (a/b);
    //     float h = (float)(c-b);
    //     listOfSorts[0] = new bubble_sort(arr);
    //     listOfSorts[1] = new insert_sort(arr);
    //     listOfSorts[2] = new selection_sort(arr);
    //     listOfSorts[3] = new merge_sort(arr);
    //     listOfSorts[4] = new quick_sort(arr);
    //     for(Sorts ch : listOfSorts)
    //     {
    //         ch.sort();
    //     }
    //     Arrays.sort(listOfSorts);
    //     for(Sorts ch : listOfSorts)
    //     {
    //         System.out.println(ch);
    //     }
        
    // }

    // public static int fina()
    // {
    //     try{
    //         return 1;
    //     }
    //     catch(ClassCastException e)
    //     {
    //         return 2;
    //     }
    //     finally{
    //         return 3;
    //     }
    // }

}