package com.example.book;

import static com.example.book.R.id.titlu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private EditText titlu;
    private EditText autor;
    private EditText comentariu;
    private RadioGroup rating;
    private CheckBox checkbox_fantasy;
    private CheckBox checkbox_horror;
    private CheckBox checkbox_detective;
    private CheckBox checkbox_romance;

    private Button confirma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        titlu=(EditText)findViewById(R.id.titlu);
        autor=(EditText)findViewById(R.id.autor);
        comentariu=(EditText)findViewById(R.id.comentariu);
        rating=(RadioGroup) findViewById(R.id.rating);
        checkbox_fantasy=(CheckBox) findViewById(R.id.checkbox_fantasy);
        checkbox_horror=(CheckBox) findViewById(R.id.checkbox_horror);
        checkbox_detective=(CheckBox) findViewById(R.id.checkbox_detectiv);
        checkbox_romance=(CheckBox) findViewById(R.id.checkbox_romance);
        confirma=(Button) findViewById(R.id.confirma);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            titlu.setText(extras.getString("currentTitlu"));
            autor.setText(extras.getString("currentAutor"));
            comentariu.setText(extras.getString("currentComent"));
            String currentRating = extras.getString("currentRating");
            String currentGen = extras.getString("currentGen");

            RadioGroup radioGroup = findViewById(R.id.rating);
            int numRadios = radioGroup.getChildCount();
            for (int i=0; i < numRadios; i++){
                View view = radioGroup.getChildAt(i);
                if (view instanceof RadioButton) {
                    RadioButton radioButton = (RadioButton) view;
                    if (radioButton.getText().equals(currentRating)){
                        radioButton.setChecked(true);
                        break;
                    }
                }
            }

            String[] genList = currentGen.split(" / ");
            for (int i=0; i<genList.length; i++) {
                switch (genList[i]){
                    case "Fantasy":
                        checkbox_fantasy.setChecked(true);
                        continue;
                    case "Horror":
                        checkbox_horror.setChecked(true);
                        continue;
                    case "Detectiv":
                        checkbox_detective.setChecked(true);
                        continue;
                    case "Romance":
                        checkbox_romance.setChecked(true);
                        continue;
                    default:
                        break;
                }

            }



        }

        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB db=new DB(EditActivity.this);
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
                    sb.append("Romance / ");
                }

                String gen = sb.toString().trim();
                if (gen.endsWith("/")) {
                    gen = gen.substring(0,gen.length() - 2);
                }
                RadioButton radioButton=(RadioButton)findViewById(id);
                db.updateBook(extras.getInt("currentId"),titlu.getText().toString(),autor.getText().toString(),comentariu.getText().toString(),radioButton.getText().toString(), gen);
                Intent intent=new Intent(EditActivity.this,BookList.class);
                startActivity(intent);
            }
        });
    }

}