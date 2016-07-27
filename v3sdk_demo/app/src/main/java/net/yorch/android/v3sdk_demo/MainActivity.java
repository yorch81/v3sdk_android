package net.yorch.android.v3sdk_demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.os.StrictMode;
import android.widget.TextView;
import org.json.JSONObject;
import org.json.JSONException;
import net.yorch.android.V3SDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Get Instance of V3ctor Warehouse SDK
        V3SDK v3 = V3SDK.getInstance("https://v3-yorch.rhcloud.com/", "lYltuNtYYbYRFC7QWwHn9b5aH2UJMk1234567890");

        JSONObject json = new JSONObject();

        try {
            json.put("field1", "myvalue");
            json.put("field2", 666);

            // Create Object
            JSONObject result = v3.newObject("demo", json);

            // Get Id
            String id = V3SDK.getId(result);

            json = new JSONObject();
            json.put("field3", "myvalue3");
            json.put("field2", 777);

            // Update Object
            v3.updateObject("demo", id, json);

            // Find Object
            result = v3.findObject("demo", id);

            // Delete Object
            v3.deleteObject("demo", "5797df24409b6028a08b4574");

            // Show Object
            TextView txt = (TextView) findViewById(R.id.txtlabel);
            txt.setText(result.toString());

        } catch (JSONException e) {
            e.printStackTrace();
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
