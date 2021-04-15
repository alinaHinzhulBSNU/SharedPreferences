package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText input;
    Button saveButton, loadButton;
    SharedPreferences sharedPreferences;
    TextView result;

    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Приховати верхню панель
        getSupportActionBar().hide();

        input = findViewById(R.id.input);

        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(this);

        loadButton = findViewById(R.id.load);
        loadButton.setOnClickListener(this);

        result = findViewById(R.id.result);
        sharedPreferences = getPreferences(MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                saveText();
                break;
            case R.id.load:
                loadText();
                break;
            default:
                break;
        }
    }

    private void saveText() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String inputValue = input.getText().toString();

        // Не записувати пусті рядки
        if(!inputValue.trim().isEmpty()){
            input.setError(null);

            editor.putString(SAVED_TEXT, inputValue);
            editor.apply();

            Toast.makeText(MainActivity.this, "Text saved!", Toast.LENGTH_LONG).show();
        }else{
            input.setError("Input is empty!");
        }
    }

    private void loadText() {
        String savedText = sharedPreferences.getString(SAVED_TEXT, "");
        result.setText(savedText);

        Toast.makeText(MainActivity.this, "Text loaded!", Toast.LENGTH_LONG).show();
    }
}