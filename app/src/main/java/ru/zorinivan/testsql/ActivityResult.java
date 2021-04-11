package ru.zorinivan.testsql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityResult extends AppCompatActivity {
TextView textView;
DBHelper dbHelper = new DBHelper(this);
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null,
                null,null,null,null,null);
        cursor.moveToFirst();

            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);

        textView = findViewById(R.id.textView);

        textView.setText("ID = "+cursor.getInt(idIndex)+
                ", name = "+cursor.getString(nameIndex)+
                ", email = "+cursor.getString(emailIndex));
        cursor.moveToNext();
        cursor.close();
    }
}