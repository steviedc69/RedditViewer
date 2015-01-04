package examen.redditviewer;



import examen.redditviewer.model.Posts;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Dries on 4/01/2015.
 */
public interface RedditApiService {
    @GET("/r/funny.json")
    void getPosts(Callback<Posts> callback);
}
