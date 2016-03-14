package contactsmanager.lab04.pdsd.systems.cs.pub.ro.contactsmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ContactManagerActivity extends ActionBarActivity {

    class OnClickListenerCustom implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view instanceof Button) {
                switch (view.getId()) {
                    case R.id.detailsBtn: {

                        break;
                    }
                    case R.id.cancelBtn: {
//                        finish();
                        setResult(Activity.RESULT_CANCELED, new Intent());
                        break;
                    }
                    case R.id.saveBtn: {

                        String name = ((EditText)findViewById(R.id.name)).getText().toString();
                        String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
                        String email = ((EditText)findViewById(R.id.email)).getText().toString();
                        String address = ((EditText)findViewById(R.id.address)).getText().toString();
                        String jobTitle = ((EditText)findViewById(R.id.jobTitle)).getText().toString();
                        String company = ((EditText)findViewById(R.id.company)).getText().toString();
                        String website = ((EditText)findViewById(R.id.website)).getText().toString();
                        String im = ((EditText)findViewById(R.id.im)).getText().toString();

                        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                        if (name != null) {
                            intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                        }
                        if (phone != null) {
                            intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                        }
                        if (email != null) {
                            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                        }
                        if (address != null) {
                            intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                        }
                        if (jobTitle != null) {
                            intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
                        }
                        if (company != null) {
                            intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                        }
                        ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                        if (website != null) {
                            ContentValues websiteRow = new ContentValues();
                            websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                            websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                            contactData.add(websiteRow);
                        }
                        if (im != null) {
                            ContentValues imRow = new ContentValues();
                            imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                            imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                            contactData.add(imRow);
                        }
                        intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
//                        startActivity(intent);
                        startActivityForResult(intent, 1001);

                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);


        Button details = (Button) findViewById(R.id.detailsBtn);
        Button save = (Button) findViewById(R.id.saveBtn);
        Button cancel = (Button) findViewById(R.id.cancelBtn);

        OnClickListenerCustom listenerCustom = new OnClickListenerCustom();
        details.setOnClickListener(listenerCustom);
        save.setOnClickListener(listenerCustom);
        cancel.setOnClickListener(listenerCustom);

        Intent intent = getIntent();
        if (intent != null) {
            String phone = intent.getStringExtra("contactsmanager.lab04.pdsd.systems.cs.pub.ro.contactsmanager.PHONE_NUMBER_KEY");
            EditText phoneText = (EditText)findViewById(R.id.phone);
            phoneText.setText(phone);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_manager, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 1001:
                setResult(resultCode, new Intent());
                finish();
                break;
        }
    }
}
