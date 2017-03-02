package com.example.yvanp.practica1;

import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edLado1 = (EditText) this.findViewById(R.id.edLado1);

        edLado1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.calcula();
            }
        });

        final EditText edLado2 = (EditText) this.findViewById(R.id.edLado2);
        edLado2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    MainActivity.this.calcula();
            }
        });

    }
    private void calcula() {
        EditText edLado1 = (EditText) this.findViewById(R.id.edLado1);
        EditText edLado2 = (EditText) this.findViewById(R.id.edLado2);
        TextView edRes = (TextView) this.findViewById(R.id.edResultado);

        String value1 = edLado1.getText().toString();
        String value2 = edLado2.getText().toString();
        double res1 = Double.parseDouble( value1 );
        double res2 = Double.parseDouble( value2 );

        String res = Double.toString(res1*res2);

        edRes.setText( res );

    }
}
