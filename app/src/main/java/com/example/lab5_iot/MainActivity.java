package com.example.lab5_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    public void irHomeActivity(View view) {

        EditText codigo = findViewById(R.id.editTextText2);
        String codigoStr = codigo.getEditableText().toString();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("codigo",codigoStr);
        //iniciar activity
        startActivity(intent);


    }

}