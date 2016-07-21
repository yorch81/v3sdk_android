package net.yorch.v3test;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import net.yorch.v3sdk.V3SDK;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        V3SDK v3 = V3SDK.getInstance("https://v3-yorch.rhcloud.com/", "lYltuNtYYbYRFC7QWwHn9b5aH2UJMk1234567890");

        JSONObject json = new JSONObject();

        try {
            json.put("field1", "myvalue");
            json.put("field2", 666);
        }
        catch (Exception e) {

        }

        JSONObject result = v3.newObject("demo", json);

        String id = V3SDK.getId(result);

        json = new JSONObject();

        try {
            json.put("field3", "myvalue3");
            json.put("field2", 777);
        }
        catch (Exception e) {

        }

        v3.updateObject("demo", id, json);

        result = v3.findObject("demo", id);

        result = v3.query("demo", json);

        Iterator<String> iter = result.keys();

        while (iter.hasNext()) {
            String key = iter.next();

            try {
                JSONObject value = (JSONObject) result.get(key);

                System.out.println(value.toString());
            } catch (JSONException e) {
            }
        }

        //v3.deleteObject("demo", id);

        TextView txt = (TextView) findViewById(R.id.txtLabel);
        txt.setText(result.toString());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
