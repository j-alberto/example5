package com.jar.example5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
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
}
