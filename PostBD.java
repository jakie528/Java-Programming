
public class PostBD implements PostInterface{
private String post;

//	private String title;
//	private String url;
//	private String body;
//	
//    public PostBD(String title, String url, String body) {
//    	this.title = title;
//    	this.url = url;
//    	this.body = body;
//    	
//    }
//
	public PostBD(String post) {
		this.post = post;
	}



	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return post.split(",")[0];
//		return "title1 title2";
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return post.split(",")[1];
//		return "url1/url2";
	}

	@Override
	public String getBody() {
		// TODO Auto-generated method stub
		return post.split(",")[2];
//		return "body1 body2";
	}

}
