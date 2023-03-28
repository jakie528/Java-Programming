/**
 * The data wrangler writes code defining a single post object, 
 * and also code to load an entire file of such objects. 
 * The java interfaces for these classes follow:
 * @author Administrator
 *
 */
public interface PostInterface {
    // public PostInterface(String title, String url, String body);
    public String getTitle();
    public String getUrl();
    public String getBody();
}

