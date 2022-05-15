package com.example.romankolindatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText editText1key;
    EditText editText2value;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1key = findViewById(R.id.editText1key);
        editText2value = findViewById(R.id.editText2value);

        database = new Database(this, "dbapp", null, 1);
    }

    public void onclickselect(View select)
    {
        if (editText1key.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Print a non-empty key", Toast.LENGTH_SHORT).show();
        else
        {
            String selk = editText1key.getText().toString();
            String selv = database.select(selk);

            if (selv.equals("This entry doesn't exists"))
                Toast.makeText(getApplicationContext(), "This entry doesn't exists", Toast.LENGTH_SHORT).show();
            else
            {
                editText2value.setText(selv);
                editText2value.setEnabled(false);
                editText2value.setTextColor(Color.parseColor("#4B0000"));
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void onclickclear(View clear)
    {
        editText1key.setEnabled(true);
        editText2value.setEnabled(true);
        editText1key.setText("");
        editText2value.setText("");
    }

    public void onclickinsert(View select)
    {
        if (editText1key.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Print a non-empty key", Toast.LENGTH_SHORT).show();
        else
        {
            String selk = editText1key.getText().toString();
            String selv = database.select(selk);

            if (!selv.equals("This entry doesn't exists"))
                Toast.makeText(getApplicationContext(), "This entry already exists", Toast.LENGTH_SHORT).show();
            else
            {
                String insk = editText1key.getText().toString();
                String insv = editText2value.getText().toString();
                database.insert(insk, insv);
                Toast.makeText(getApplicationContext(), "This entry has been inserted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onclickupdate(View select)
    {
        if (editText1key.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Print a non-empty key", Toast.LENGTH_SHORT).show();
        else
        {
            String selk = editText1key.getText().toString();
            String selv = database.select(selk);

            if (selv.equals("This entry doesn't exists"))
                Toast.makeText(getApplicationContext(), "This entry doesn't exists", Toast.LENGTH_SHORT).show();
            else
            {
                String updk = editText1key.getText().toString();
                String updv = editText2value.getText().toString();
                database.update(updk, updv);
                Toast.makeText(getApplicationContext(), "This entry has been updated", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void onclickdelete(View select)
    {
        if (editText1key.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Print a non-empty key", Toast.LENGTH_SHORT).show();
        else
        {
            String selk = editText1key.getText().toString();
            String selv = database.select(selk);

            if (selv.equals("This entry doesn't exists"))
                Toast.makeText(getApplicationContext(), "This entry doesn't exists", Toast.LENGTH_SHORT).show();
            else
            {
                String delk = editText1key.getText().toString();
                AlertDialog.Builder del = new AlertDialog.Builder(this).setTitle("Entry's delete");
                del.setMessage("Do you wanna delete this entry?");
                del.setPositiveButton("No", ((dialogInterface, n) -> dialogInterface.cancel()));
                del.setNegativeButton("Yes", ((dialogInterface, y) ->
                {
                    database.delete(delk);
                    Toast.makeText(getApplicationContext(), "This entry has been deleted", Toast.LENGTH_SHORT).show();
                    editText1key.setText("");
                    editText2value.setText("");
                }));
                del.show();
            }
        }
    }
}