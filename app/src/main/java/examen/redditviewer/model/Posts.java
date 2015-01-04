package examen.redditviewer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dries on 4/01/2015.
 */
public class Posts {
    private Data data;

    private class Data{
        public List<DataChildren> children;
    }

    private class DataChildren{
        public Post data;
    }

    public List<Post> getPosts(){
        List<Post> out = new ArrayList<Post>();
        for(DataChildren dc : data.children){
            out.add(dc.data);
        }
        return out;
    };
}
