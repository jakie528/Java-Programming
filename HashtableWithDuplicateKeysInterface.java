import java.util.List;

/**
 * Algorithm Engineer (AE) Role
 The algorithm engineer extends the HashtableMap implementation of the MapADT interface 
 to provide a collection that can pair one key with multiple values, since MapADT does not allow duplicate keys. 
 This is handled by pairing keys with a list of values rather than single values.  
 Since this role involves less work than they others, the Algorithm Engineer is also responsible 
 for writing the the CHSearchApp.java class that contains the main method (entry point) for this application.

No interface is provided for the CSSearchApp class since it only contains a single static method.  The Java interface for the other class is as follows:
 * @author Administrator
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public interface HashtableWithDuplicateKeysInterface<KeyType, ValueType> extends MapADT<KeyType,List<ValueType>> {
    //public HashtableWithDuplicateKeysInterface(int capacity);
    //public HashtableWithDuplicateKeysInterface();
    public void putOne(KeyType key, ValueType value);
    public void removeOne(KeyType key, ValueType value);
    //the number of key-post pairs
    public int getNumberOfValues();
}
