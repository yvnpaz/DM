package uvigo.esei.dm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PedidoMesaAnadir extends Activity {

	Button btnBebida;
	Button btnPlato;
	Button btnPostre;
	int num_mesa=0;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.pedido_mesa_anadir);
	        
	        btnBebida = (Button) findViewById(R.id.anadirBebida);
	        
	        Intent myCallerIntentHandler = getIntent();
			Bundle myBundle = myCallerIntentHandler.getExtras();
			num_mesa = myBundle.getInt("mesa");
	        
	        btnBebida.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			try{
	    				Intent myIntentBebida = new Intent(PedidoMesaAnadir.this,PedidoMesaAnadirBebida.class);
		    			Bundle toSend = new Bundle();
		    			toSend.putInt("mesa", num_mesa);
		    			myIntentBebida.putExtras(toSend);
	    				startActivity(myIntentBebida);
	    			}catch(Exception e){
	        			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

	    			}
	    		}
	    	}	
	    );  
	        
	        btnPlato = (Button) findViewById(R.id.anadirPlato);
	        
	        
	        
	        btnPlato.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			try{
	    				Intent myIntentPlato = new Intent(PedidoMesaAnadir.this,PedidoMesaAnadirPlato.class);
		    			Bundle toSend = new Bundle();
		    			toSend.putInt("mesa", num_mesa);
		    			myIntentPlato.putExtras(toSend);
	    				startActivity(myIntentPlato);
	    			}catch(Exception e){
	        			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

	    			}
	    		}
	    	}
	    );
	        btnPostre = (Button) findViewById(R.id.anadirPostre);
	        
	      
	        
	        btnPostre.setOnClickListener(new OnClickListener(){
	    		public void onClick(View v){
	    			try{
	    				Intent myIntentPostre = new Intent(PedidoMesaAnadir.this,PedidoMesaAnadirPostre.class);
		    			Bundle toSend = new Bundle();
		    			toSend.putInt("mesa", num_mesa);
		    			myIntentPostre.putExtras(toSend);
	    				startActivity(myIntentPostre);
	    			}catch(Exception e){
	        			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

	    			}
	    		}
	    	}	
	    );  
	        
	    }
	    


	}
