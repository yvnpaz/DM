package com.example.yvanp.ventanas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btVer    = (Button) this.findViewById( R.id.btVer );
        final EditText edText = (EditText) this.findViewById( R.id.edText1);

        btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos el intent
                Intent intent = new Intent(MainActivity.this, Segunda.class);

                //Creamos la informacion a pasar entre actividades
                Bundle bun = new Bundle();
                bun.putString("NOMBRE", edText.getText().toString() );

                //añadimos la informacion al intent
                intent.putExtras(bun);

                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
    }
}
