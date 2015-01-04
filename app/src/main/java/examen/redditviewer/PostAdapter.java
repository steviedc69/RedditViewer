package examen.redditviewer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import examen.redditviewer.model.Post;

/**
 * Created by Dries on 4/01/2015.
 */
public class PostAdapter extends BaseAdapter {
    private List<Post> postList;

    public PostAdapter(List<Post> postList){
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.post_item,parent,false);
        }

        TextView title =(TextView) convertView.findViewById(R.id.post_title);
        TextView author =(TextView) convertView.findViewById(R.id.post_author);

        title.setText(
                postList.get(position).getTitle()
        );

        author.setText(
                postList.get(position).getAuthor()
        );

        return convertView;
    }
}
