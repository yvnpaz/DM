package esei.uvigo.es.dm;

import android.app.Activity;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NumMesas extends Activity {
	
	EditText numMesas;
	Button btn;
	SQLiteDatabase db;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView( R.layout.num_mesas);
		numMesas = (EditText) findViewById( R.id.numMesas);
		btn = (Button) findViewById( R.id.btn);
		try{
			openDatabase();
			createTables();
		}catch(Exception e){
			Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
		}
        btn.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			delete();
    			insertData(numMesas.getText().toString());
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
    
    private void delete(){
    	db.beginTransaction();
		try {
			//create table
			db.execSQL("DELETE FROM Opciones;");
			//commit your changes
    		db.setTransactionSuccessful();
    		
			
		}
		catch (SQLException e1) {			
			Toast.makeText(this, e1.getMessage(), Toast.LENGTH_LONG).show();
		}
		finally {
    		db.endTransaction();
    	}
    }//delete
    
    private void insertData(String data){
    	int numMesas = Integer.parseInt(data);
    	db.beginTransaction();
    	try {

    		//insert rows
    		db.execSQL( "insert into Opciones(num_mesas) "
    					         + " values ('"+numMesas+"');" );
    		
    		//commit your changes
    		db.setTransactionSuccessful();
    	}
    	catch (SQLiteException e2) {
    		//report problem 
    		Toast.makeText(this, e2.getMessage(), Toast.LENGTH_LONG).show();
    	}
    	finally {
    		db.endTransaction();
    	}
    }//insertData
    
    
	
	

}
