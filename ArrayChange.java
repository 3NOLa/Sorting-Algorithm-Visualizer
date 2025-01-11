public class ArrayChange {
    final int index;
    final int newValue;
    final int oldValue;
    final long timestamp;
    
    ArrayChange(int index, int newValue,int oldValue , long timestamp) {
        this.index = index;
        this.newValue = newValue;
        this.timestamp = timestamp;
        this.oldValue = oldValue;
    }
}