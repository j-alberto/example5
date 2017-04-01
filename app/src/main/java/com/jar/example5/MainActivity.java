package com.jar.example5;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String MIS_DATOS = "Mis datos";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    EditText etName;
    EditText etEmail;
    TextView tvPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        tvPreference = (TextView) findViewById(R.id.tvPreference);
    }

    public void makeFile(View view) {

        try(FileOutputStream fos = openFileOutput("miArchivo.txt", Context.MODE_PRIVATE)) {
            fos.write(etName.getText().toString().getBytes());
            Toast.makeText(this, "Archivo creado",Toast.LENGTH_LONG).show();
            etName.setText("");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"No se encontro el archivo",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"No se pudo leer el archivo",Toast.LENGTH_SHORT).show();
        }
    }

    public void showPreference(View view) {
        SharedPreferences prefs = getSharedPreferences(MIS_DATOS, Context.MODE_PRIVATE);
        String name = prefs.getString(NAME,"Sin nombre");
        String email = prefs.getString(EMAIL,"Sin email");

        tvPreference.setText(String.format("Name:\t%s \nEmail:\t%s",name,email));
    }

    public void savePreference(View view) {
        SharedPreferences prefs = getSharedPreferences(MIS_DATOS, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefs.edit();

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

        prefEditor.putString(NAME, name);
        prefEditor.putString(EMAIL, email);

        prefEditor.commit();

        Toast.makeText(this, "Preferencias compartidas guardadas",Toast.LENGTH_LONG).show();


    }
}
