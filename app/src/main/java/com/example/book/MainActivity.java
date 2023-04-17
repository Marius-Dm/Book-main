package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private EditText titlu;
    private EditText autor;
    private EditText comentariu;
    private RadioGroup rating;
    private CheckBox checkbox_fantasy;
    private CheckBox checkbox_horror;
    private CheckBox checkbox_detective;
    private CheckBox checkbox_romance;

    private Button adauga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlu=(EditText)findViewById(R.id.titlu);
        autor=(EditText)findViewById(R.id.autor);
        comentariu=(EditText)findViewById(R.id.comentariu);
        rating=(RadioGroup) findViewById(R.id.rating);
        checkbox_fantasy=(CheckBox) findViewById(R.id.checkbox_fantasy);
        checkbox_horror=(CheckBox) findViewById(R.id.checkbox_horror);
        checkbox_detective=(CheckBox) findViewById(R.id.checkbox_detectiv);
        checkbox_romance=(CheckBox) findViewById(R.id.checkbox_romance);
        adauga=(Button) findViewById(R.id.adauga);

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB db=new DB(MainActivity.this);
                int id=rating.getCheckedRadioButtonId();
                StringBuilder sb = new StringBuilder();
                if (checkbox_fantasy.isChecked()) {
                    sb.append("Fantasy / ");
                }
                if (checkbox_horror.isChecked()) {
                    sb.append("Horror / ");
                }
                if (checkbox_detective.isChecked()) {
                    sb.append("Detectiv / ");
                }
                if (checkbox_romance.isChecked()) {
                    sb.append("Romnace / ");
                }

                String gen = sb.toString().trim();
                if (gen.endsWith("/")) {
                    gen = gen.substring(0,gen.length() - 2);
                }
                RadioButton but=(RadioButton)findViewById(id);
                db.addBook(new Book(titlu.getText().toString(),autor.getText().toString(),comentariu.getText().toString(),but.getText().toString(),gen));
                Intent intent=new Intent(MainActivity.this,BookList.class);
                startActivity(intent);
            }
        });
    }
}