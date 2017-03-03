package com.example.yvanp.ventanas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Segunda extends AppCompatActivity {

    TextView edText2;
    Button btBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        edText2 = (TextView) this.findViewById(R.id.edText2);
        btBack = (Button) this.findViewById(R.id.btVolver);

        Bundle bun = this.getIntent().getExtras();

        edText2.setText("Bienvenido "+ bun.getString("NOMBRE"));

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent datosRetorno = new Intent();
                datosRetorno.putExtra("nombre", edText2.getText().toString() );
                Segunda.this.setResult( RESULT_OK, datosRetorno );
                Segunda.this.finish();
            }
        });

    }

    public boolean onCreateOptionMenu(Menu menu)
    {
        return true;
    }

    public boolean onOptionsItemsSelected(MenuItem item)
    {
        int id = item.getItemId();



        return super.onOptionsItemSelected( item );
    }

}
