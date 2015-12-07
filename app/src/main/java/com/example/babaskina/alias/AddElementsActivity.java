package com.example.babaskina.alias;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by artem on 11.11.15.
 */
public class AddElementsActivity extends AppCompatActivity {

    private String elemTitleName = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_elements);
        Button addButton = (Button) findViewById(R.id.add_btn_elem_AddElemActivity);
        final EditText titleEditText = (EditText) findViewById(R.id.title_AddElemActivity);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!titleEditText.getText().toString().matches("")) {
                    Intent intent = new Intent();
                    intent.putExtra(elemTitleName, titleEditText.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
