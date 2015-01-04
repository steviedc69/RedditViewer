package examen.redditviewer;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import examen.redditviewer.model.Post;
import examen.redditviewer.model.Posts;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    private RedditApiService rapi;
    private List<Post> postsList;
    private PostAdapter adapter;
    private View v;

    @InjectView(R.id.posts_list)
    ListView list;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Butterknife Fragment injection
        v = inflater.inflate(R.layout.fragment_posts, container, false);
        ButterKnife.inject(this,v);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Portrait mode: replace fragment
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    DetailFragment dfragment = new DetailFragment();
                    dfragment.setPost(postsList.get(position));

                    //Important! Alwas set a TAG for your fragment, or backstack and replace won't work correctly
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.portrait_fragment_container, dfragment, "DETAILS_FRAGMENT");
                    ft.addToBackStack("DETAILS_FRAGMENT");
                    ft.commit();
                }
                else{
                    //Landscape mode: broadcast intent with post to details-fragment
                    Intent intent = new Intent("SHOW_DETAILS");
                    intent.putExtra("post",(Serializable) postsList.get(position));
                    getActivity().sendBroadcast(intent);
                }
            }
        });

        //Create rest service
        rapi = new RestAdapter.Builder().setEndpoint("http://www.reddit.com").build().create(RedditApiService.class);

        //Get list of posts
        rapi.getPosts(new Callback<Posts>() {
            @Override
            public void success(Posts posts, Response response) {
                postsList = posts.getPosts();
                adapter = new PostAdapter(postsList);
                list.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("PostsFragment", error.toString());
            }
        });
        return v;
    }

}
