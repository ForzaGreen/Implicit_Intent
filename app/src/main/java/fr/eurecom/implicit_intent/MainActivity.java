package fr.eurecom.implicit_intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.provider.MediaStore;
import android.widget.EditText;
import android.app.Activity;
import android.graphics.Bitmap;
import android.content.ContentUris;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.ContactsContract;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    public void callIntent(View view) {
        System.out.print("I am in callIntent");
        Intent intent = null;
        switch (view.getId()) {
            case R.id.Call_Browser:
                EditText textBrow = (EditText) findViewById(R.id.edit_url);
                System.out.print("I am in button2");
                String url = textBrow.getText().toString();
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://" + url));
                startActivity(intent);

                break;

            case R.id.Call_Someone:
                EditText numb = (EditText) findViewById(R.id.phone_number);
                String numbe = numb.getText().toString();
                intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+numbe));
                startActivity(Intent.createChooser(intent, "Question 3 answer"));

                break;

            case R.id.Dial:
                intent=new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
                break;
            case R.id.Show_Map:
                String geoUriString = getResources().getString(R.string.map_location);
                intent = new Intent(Intent.ACTION_VIEW,  Uri.parse(geoUriString));
                startActivity(intent);
                break;
            case R.id.Search_on_Map:
                EditText pos = (EditText) findViewById(R.id.map_place);
                String pos2 = pos.getText().toString();
                intent = new Intent(Intent.ACTION_VIEW,  Uri.parse("geo:0,0?q="+pos2));
                startActivity(intent);
                break;
            case R.id.Take_Picture:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 4);
                break;
            case R.id.Show_Contact:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(intent);
                break;
            case R.id.Edit_first_Contact:
                intent = new Intent(Intent.ACTION_EDIT);
                intent.setData(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, 1));
                startActivity(intent);
                break;
            case R.id.Add_to_calendar:
                intent = new Intent(Intent.ACTION_INSERT, Events.CONTENT_URI);
                Calendar cal = Calendar.getInstance();
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis());
                intent.putExtra(Events.TITLE, "Question 4 answer");
                intent.putExtra(Events.EVENT_LOCATION, "Eurecom");
                startActivity(intent);

                break;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri imageURI=null;
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getApplicationContext().getContentResolver(), imageURI);
            }catch (java.io.IOException e){}
        }


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