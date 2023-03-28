import java.io.FileNotFoundException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class PostReaderBD implements PostReaderInterface{

//Data wrangler writes code defining a single post object, and also code to load an entire file of such objects.  
	@Override
	public List<PostInterface> readPostsFromFile(String filename) throws FileNotFoundException {

		
		List<PostInterface> postList = new ArrayList<>();
		
		PostBD post1 = new PostBD("TITLE:title1 title2,URL:url1/url2,BODY:body1 body2");
//		PostBD post1 = new PostBD(filename, filename, filename);

		
		postList.add(post1);
//		postList.add(post2);


	
		return postList;


}
}
