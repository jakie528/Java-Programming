import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This CHSearchBackendBD class load data from posts and find posts by words
 *
 */
public class CHSearchBackendBD implements CHSearchBackendInterface {

	HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable = null;
	PostReaderInterface postReader = null;

	/**
	 * constructor
	 * 
	 * @param hashtable
	 * @param postReader
	 */
	public CHSearchBackendBD(HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable,
			PostReaderInterface postReader) {
		this.hashtable = hashtable;
		this.postReader = postReader;
	}

	/**
	 * load data from postreader and split keywords from every posts
	 */
	@Override
	public void loadData(String filename) throws FileNotFoundException {

		List<PostInterface> list = postReader.readPostsFromFile(filename);

		for (int i = 0; i < list.size(); i++) { // list contains many posts
			PostInterface post = list.get(i); // get a post

			String title = post.getTitle(); // title1 title2

			String body = post.getBody(); // body1 body2

			// get words
			String[] titleParts = title.split(" "); // get the key parts

			String[] bodyParts = body.split(" "); // get the body parts

			// store every possible search key-value pair in the HashTable
			for (int j = 0; j < titleParts.length; j++) {
				hashtable.putOne(titleParts[j], post);
			}

			for (int j = 0; j < bodyParts.length; j++) {
				hashtable.putOne(bodyParts[j], post);
			}

		}

	}

	/**
	 * find all the posts associate with the title keyword
	 */
	@Override
	public List<String> findPostsByTitleWords(String words) {
		// Return the whole list of posts
		List<String> list = new ArrayList<>();
		// delete the bracket
		String word = words.replace("[", "").replace("]", "");

		if (hashtable.containsKey(word)) {
			List<PostInterface> resultPosts = hashtable.get(word);

			for (PostInterface post : resultPosts) {
				String result = post.getTitle() + post.getUrl() + post.getBody(); // this step call from post
				list.add(result);
			}
		}
		return list;
	}

	/**
	 * find all the posts associate with the body keyword
	 */
	@Override
	public List<String> findPostsByBodyWords(String words) {

		List<String> list = new ArrayList<>();
		// delete the bracket
		String word = words.replace("[", "").replace("]", "");

		if (hashtable.containsKey(word)) {
			List<PostInterface> resultPosts = hashtable.get(word);

			for (PostInterface post : resultPosts) {
				String result = post.getTitle() + post.getUrl() + post.getBody();
				list.add(result);
			}

		}
		return list;
	}

	/**
	 * find all the posts associate with the body or title keyword
	 */
	@Override
	public List<String> findPostsByTitleOrBodyWords(String words) {
		List<String> list = new ArrayList<>();
		// delete the bracket
		String word = words.replace("[", "").replace("]", "");

		if (hashtable.containsKey(word)) {
			List<PostInterface> resultPosts = hashtable.get(word);

			for (PostInterface post : resultPosts) {
				String result = post.getTitle() + post.getUrl() + post.getBody();
				list.add(result);
			}

		}
		return list;
	}

	/**
	 * get statistics insight from dataset
	 */
	@Override
	public String getStatisticsString() {
		// the number of word-post pairs stored in the dataset

		int keyPostPairs = hashtable.getNumberOfValues();

		return Integer.toString(keyPostPairs);

	}

}
