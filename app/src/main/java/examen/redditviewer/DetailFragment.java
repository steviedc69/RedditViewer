package examen.redditviewer;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import examen.redditviewer.model.Post;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private Post post;
    private IntentFilter filter = new IntentFilter("SHOW_DETAILS");
    private MyReceiver receiver = new MyReceiver();

    @InjectView(R.id.post_detail_title)
    public TextView title;

    @InjectView(R.id.post_detail_author)
    public TextView author;

    @InjectView(R.id.post_detail_image)
    public ImageView image;

    @InjectView(R.id.post_detail_selftext)
    public TextView selfText;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.inject(this,v);
        getActivity().registerReceiver(receiver, filter);
        updateView();
        return v;
    }

    public void updateView(){
        if(post != null) {

            title.setText(post.getTitle());
            author.setText(post.getAuthor());

            Log.i("Detail", post.getUrl());
            if (post.getUrl().matches(".*(jpg|png|gif)$")) {
                selfText.setText("");
                //Image post
                Picasso
                        .with(getActivity())
                        .load(post.getUrl())
                        .into(image);
            } else {
                //Text post
                image.setImageBitmap(null);
                selfText.setText(post.getSelftext());
            }
        }
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public void onResume(){
        super.onResume();
        getActivity().registerReceiver(receiver, filter);
    }

    @Override
    public void onPause(){
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            post = (Post) intent.getExtras().get("post");
            updateView();
        }
    }

}
