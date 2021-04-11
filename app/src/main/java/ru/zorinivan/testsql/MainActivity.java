package ru.zorinivan.testsql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAdd, buttonRead,buttonClear;
    EditText etName,etEmail;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this);

        buttonRead = (Button) findViewById(R.id.buttonRead);
        buttonRead.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etName = (EditText) findViewById(R.id.etName);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.buttonAdd:
                contentValues.put(DBHelper.KEY_NAME,name);
                contentValues.put(DBHelper.KEY_MAIL,email);

                database.insert(DBHelper.TABLE_CONTACTS,null,contentValues);
                break;


            case R.id.buttonRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null,
                        null,null,null,null,null);

                if (cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    do {
                        Log.d("mLog","ID = "+cursor.getInt(idIndex)+
                                ", name = "+cursor.getString(nameIndex)+
                                ", email = "+cursor.getString(emailIndex));
                    } while (cursor.moveToNext());
                }else
                    Log.d("mLog","0 rows");
                System.out.println(Log.d("mLog","ID = "+cursor.getInt(idIndex)+
                        ", name = "+cursor.getString(nameIndex)+
                        ", email = "+cursor.getString(emailIndex)));

                cursor.close();
                break;

            case R.id.buttonClear:
                database.delete(DBHelper.TABLE_CONTACTS,null,null);
                break;

        }


    }
}