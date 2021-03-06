package com.sprint3.otters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                /*
                * Creating some sample test data to see what the layout looks like.
                * You should eventually delete this.
                */
        TaskSizeItem item1 = new TaskSizeItem("SMALL", "Small");
        TaskSizeItem item2 = new TaskSizeItem("MEDIUM", "Medium");
        TaskSizeItem item3 = new TaskSizeItem("BIG", "Big");
        final List<TaskSizeItem> sampleData = new ArrayList<TaskSizeItem>();
        sampleData.add(item1);
        sampleData.add(item2);
        sampleData.add(item3);

        // Set up the ArrayAdapter for the feedList
        TaskSizeListAdapter feedListAdapter = new TaskSizeListAdapter(this.getApplicationContext(), sampleData);
        ListView feedList = (ListView) findViewById(R.id.titles);

        feedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                       int pos, long id) {

                Intent i = new Intent(getApplicationContext(), ViewPagerActivity.class); // creates a new intent i, which is how Android passes information between activities, and defines this intent as a way to navigate to the SecondActivity
                i.putExtra("size", sampleData.get(pos).size);
                i.putExtra("start", 0);
                startActivityForResult(i, 1); // tells Android to make the intent active
            };
        });

        feedList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                Intent i = new Intent(getApplicationContext(), MakeTask.class); // creates a new intent i, which is how Android passes information between activities, and defines this intent as a way to navigate to the SecondActivity
                i.putExtra("size", sampleData.get(pos).size);
                startActivity(i); // tells Android to make the intent active

                return true;
            }
        });
        feedList.setAdapter(feedListAdapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){}
            if(resultCode == 4){

                String size = data.getExtras().getString("size");
                int startPage = data.getExtras().getInt("start");
                Intent i = new Intent(getApplicationContext(), ViewPagerActivity.class); // creates a new intent i, which is how Android passes information between activities, and defines this intent as a way to navigate to the SecondActivity
                i.putExtra("size", size);
                i.putExtra("start", startPage);
                startActivityForResult(i, 1); // tells Android to make the intent active

            }
            if (resultCode == 5){
                String size = data.getExtras().getString("size");
                Intent i = new Intent(getApplicationContext(), ListActivity.class);
                i.putExtra("size", size);
                startActivityForResult(i, 1);
            }

            //XXX:This isn't perfect. If you back from List, you go back to the MainActivity.

            if (resultCode == RESULT_CANCELED) {

            }
        }
    }
}
    

