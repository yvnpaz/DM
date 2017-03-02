package uvigo.esei.dm;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

import java.util.ArrayList;
import java.util.List;

public class PedidoMesaAnadirPlato extends Activity implements AdapterView.OnItemSelectedListener {
	int num_mesa=0;
	Button btn;
	EditText cantidad;
	SQLiteDatabase db;
	List<String> toshow = new ArrayList<String>();
	TextView txtMsg;
	String[] items = null;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(es.uvigo.esei.dm1516.p45.R.layout.pedido_mesa_anadir_plato);
        Intent myCallerIntentHandler = getIntent();
		Bundle myBundle = myCallerIntentHandler.getExtras();
		num_mesa = myBundle.getInt("mesa");
		
		try{
		openDatabase();
		createTables();
		toshow = query();
		items = new String[toshow.size()];
		for(int i=0;i<toshow.size();i++){
			items[i]=toshow.get(i);
		}
		txtMsg = (TextView) findViewById(es.uvigo.esei.dm1516.p45.R.id.txtMsg);
		Spinner spin = (Spinner) findViewById(es.uvigo.esei.dm1516.p45.R.id.my_spinner);
		spin.setOnItemSelectedListener(this);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item
				,items);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);
		
		cantidad = (EditText) findViewById(es.uvigo.esei.dm1516.p45.R.id.cantidad);
		btn = (Button) findViewById(es.uvigo.esei.dm1516.p45.R.id.btn);
		
		
	}catch(Exception e){
		Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
	}
		
        btn.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			insertData(txtMsg.getText().toString(),cantidad.getText().toString(),num_mesa);
    		}	
        }); 
		
		
	}//onCreate
	
	protected void onPause(){
    	super.onPause();
    	try{
    		db.close();
    		//Toast.makeText(this, "DataBase closed!", Toast.LENGTH_LONG).show();
    	}catch(Exception e){
    		Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
    	}
	}//onPause
	
	public void onItemSelected(AdapterView<?> parent,View v,int position,long id){
		if(items != null){
		txtMsg.setText(items[position]);
		}else{
			txtMsg.setText("");
		}
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
    
    private List<String> query(){
    	try {
    		List<String> toret = new ArrayList<String>();
    		//hard-coded SQL select with no arguments
			String mySQL ="select * from Producto where tipo='Plato' order by nombre";
			Cursor c1 = db.rawQuery(mySQL, null);
			if(c1.moveToFirst()){
				do{
					toret.add(c1.getString(1));
				}while(c1.moveToNext());
				return toret;
			}else{
				return toret;
			}
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
    }
    
    private void insertData(String nombreP,String cantidad,int num_mesa ){
    	int cant = Integer.parseInt(cantidad);
    	db.beginTransaction();
    	try {

    		//insert rows
    		db.execSQL( "insert into Pedido(producto,cantidad,mesa)"
    					         + " values ('"+nombreP+"','"+cant+"','"+num_mesa+"');" );
    		
    		//commit your changes
    		db.setTransactionSuccessful();
    		Toast.makeText(this, "Data insert done!", Toast.LENGTH_LONG).show();
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
