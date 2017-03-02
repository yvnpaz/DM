package esei.uvigo.es.dm;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarPlato2 extends Activity implements AdapterView.OnItemSelectedListener {
	
	TextView txtMsg;
	String[] items = {"Bebida","Plato","Postre"};
	Button btn;
	EditText nombrePlato;
	EditText precioPlato;
	SQLiteDatabase db;
	String paramString="";
	
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		setContentView( R.layout.modificar_plato2 );
		txtMsg = (TextView) findViewById( R.id.txtMsg );
		Spinner spin = (Spinner) findViewById( R.id.my_spinner );
		spin.setOnItemSelectedListener(this);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item
				,items);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);
		
		btn = (Button) findViewById( R.id.btn );
		nombrePlato = (EditText) findViewById( R.id.nombrePlato );
		precioPlato = (EditText) findViewById( R.id.precioPlato );
		try{
			openDatabase();
			createTables();
		}catch(Exception e){
			Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
		}
        btn.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			changueData(nombrePlato.getText().toString(),precioPlato.getText().toString(),txtMsg.getText().toString(),paramString);
    			finish();
    		}	
        }); 
	}//onCreate
	
	public void onItemSelected(AdapterView<?> parent,View v,int position,long id){
		txtMsg.setText(items[position]);
	}
	public void onNothingSelected(AdapterView<?> parent){
		txtMsg.setText("");
	}
	
    private void openDatabase() {
        try {        	
            // this provides the 'real' path name to the SD card
            String SDcardPath = Environment.getExternalStorageDirectory().getPath();
            String myDbPath = SDcardPath + "/" + "DataBase.db";          	
        	db = SQLiteDatabase.openDatabase(
        			myDbPath,
    				null,
    				SQLiteDatabase.CREATE_IF_NECESSARY);       	
        	
        	//Toast.makeText(this, "DataBase was opened!", Toast.LENGTH_LONG).show();
        }
        catch (SQLiteException e) {
        	 Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();        	
        }
    }//createDatabase
    
    private void createTables() {
    	//create tables
    	db.beginTransaction();
		try {
			//create tables
			db.execSQL("create table if not exists Producto ("
					+ " id integer PRIMARY KEY autoincrement, " 
			        + " nombre  text, " 
			        + " precio  float, "
			        + " tipo text );  ");
			db.execSQL("create table if not exists Pedido ("
					+ " id integer PRIMARY KEY autoincrement, " 
			        + " producto  text, " 
			        + " cantidad  integer, "
			        + " mesa  integer);");
			db.execSQL("create table if not exists Cuenta ("
					+ " id integer PRIMARY KEY autoincrement, " 
			        + " fk_pedidos integer,"
					+ " FOREIGN KEY(fk_pedidos) REFERENCES Pedido(id) ON DELETE CASCADE);");
			db.execSQL("create table if not exists Opciones (" 
			        + " num_mesas integer PRIMARY KEY );  ");
			//commit your changes
    		db.setTransactionSuccessful();
    		
			//Toast.makeText(this, "Tables was created", Toast.LENGTH_LONG).show();
			
		}
		catch (SQLException e1) {			
			Toast.makeText(this, e1.getMessage(), Toast.LENGTH_LONG).show();
		}
		finally {
    		db.endTransaction();
    	}
    }//createTables
    
    private void changueData(String nombreP,String precio,String tipo,String clave ){
    	precio=precio.replaceAll(",", ".");
    	float precioP = Float.parseFloat(precio);
    	String [] whereArgs = {clave};	
    	ContentValues updValues = new ContentValues();
    	updValues.put("nombre", nombreP);
    	updValues.put("precio", precioP);
    	updValues.put("tipo", tipo);

    	int recAffected =	db.update( "Producto", 
    									updValues, 
    									"nombre=?", 
    									whereArgs );
    	Toast.makeText(this, "Total7: " + recAffected, Toast.LENGTH_LONG).show();
    }//changueData
    
	protected void onPause(){
    	super.onPause();
    	try{
    		db.close();
    	}catch(Exception e){
    		Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
    	}
	}
	
	protected void onStart() {
		super.onStart();
		Intent myCallerIntentHandler = getIntent();
		Bundle myBundle = myCallerIntentHandler.getExtras();
		paramString = myBundle.getString("message");
		nombrePlato.setText(paramString);
	}
}
