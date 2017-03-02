package uvigo.esei.dm;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PedidoMesaModificar2 extends Activity{
	Button btn;
	TextView nombre;
	EditText cantidad;
	int num_mesa=0;
	SQLiteDatabase db;
	String name="";
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(es.uvigo.esei.dm1516.p45.R.layout.pedido_mesa_modificar2);
		btn = (Button) findViewById(es.uvigo.esei.dm1516.p45.R.id.btn);
		nombre = (TextView) findViewById(es.uvigo.esei.dm1516.p45.R.id.nombre);
		cantidad = (EditText) findViewById(es.uvigo.esei.dm1516.p45.R.id.Cantidad);
        Intent myCallerIntentHandler = getIntent();
		Bundle myBundle = myCallerIntentHandler.getExtras();
		name = myBundle.getString("message");
		nombre.setText(name);
		num_mesa = myBundle.getInt("mesa");
		try{
			openDatabase();
			createTables();
		}catch(Exception e){
			Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
		}
        btn.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			changueData(name,num_mesa,cantidad.getText().toString());
    			finish();
    		}	
        }); 
	}//onCreate
	
	protected void onPause(){
    	super.onPause();
    	try{
    		db.close();
    	}catch(Exception e){
    		Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
    	}
	}//onPause
	
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
	
	private void changueData(String producto,int num_mesa,String cantidad){
		int cant = Integer.parseInt(cantidad);
    	String [] whereArgs = {producto};	
    	ContentValues updValues = new ContentValues();
    	updValues.put("producto", producto);
    	updValues.put("cantidad", cant);
    	updValues.put("mesa", num_mesa);

    	int recAffected =	db.update( "Pedido", 
    									updValues, 
    									"producto=? AND mesa="+num_mesa, 
    									whereArgs );
    	Toast.makeText(this, "Total7: " + recAffected, Toast.LENGTH_LONG).show();
	}
}
