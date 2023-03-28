import java.io.FileNotFoundException;
import java.util.List;

/**
 * The backend developer writes code that makes use of a PostReaderInterface object implemented 
 * by the DW and a HashtableWithDuplicateKeysInterface object developed by the AE to efficiently store 
 * and retrieve information about posts by keywords that are included in their title, or body, or both their title and body.  
 * It also provides a method that retrieves statistics about the dataset that is being searched over.
 * @author Administrator
 *
 */



public interface CHSearchBackendInterface {
//     public CHSearchBackendInterface(HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable, PostReaderInterface postReader);
    public void loadData(String filename) throws FileNotFoundException;

    //loading the hashtable create object iterator the hashtable
    
    
    public List<String> findPostsByTitleWords(String words);
    //key: title
    
    public List<String> findPostsByBodyWords(String words);
    //
    public List<String> findPostsByTitleOrBodyWords(String words);
    
    public String getStatisticsString(); // anything you like

}
