package esei.uvigo.es.dm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Opciones extends Activity {
	
	Button btn;
	TextView opciones;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opciones);
        btn = (Button) findViewById(R.id.btn);
        opciones = (TextView) findViewById(R.id.opciones);
        btn.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			try{
    				Intent myIntentNumMesas = new Intent(Opciones.this,NumMesas.class);
    				startActivity(myIntentNumMesas);
    			}catch(Exception e){
        			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

    			}
    		}
    	}	
    ); 
	}
}
