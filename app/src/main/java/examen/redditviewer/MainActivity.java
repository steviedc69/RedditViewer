package examen.redditviewer;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if in portrait mode, inflate fragment into framgent container
        //this is not needed in landscape mode, since we don't have to replace fragments and we can declare the fragments staticly in the XML layout
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //when switching from portrait->landscape->portrait, don't re-add the fragment
            Fragment fragment = getFragmentManager().findFragmentByTag("POSTS_FRAGMENT");
            if(fragment == null) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.portrait_fragment_container, new PostsFragment(), "POSTS_FRAGMENT");
                ft.commit();
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
