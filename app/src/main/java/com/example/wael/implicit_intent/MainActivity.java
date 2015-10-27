package com.example.wael.implicit_intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public void callIntent(View view) {
        System.out.print("I am in callIntent");
        Intent intent = null;
        switch (view.getId()) {
            case R.id.button1:
                EditText textBrow = (EditText) findViewById(R.id.edit_url);
                String url = textBrow.getText().toString();
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://" + url));
                startActivity(intent);

                break;

            case R.id.button2:
                System.out.print("I am in button2");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
