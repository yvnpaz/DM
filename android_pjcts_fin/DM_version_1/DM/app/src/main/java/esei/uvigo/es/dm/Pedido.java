package esei.uvigo.es.dm;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends Activity{
	SQLiteDatabase db;
	List<String> toshow = new ArrayList<>();
	ListView lv;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView( R.layout.pedido );
		try{
			openDatabase();
			createTables();
			toshow = query();
			final TextView txtview = (TextView) findViewById(R.id.txtview);
			if(toshow.isEmpty()){
				txtview.setText("No hay datos almacenados");
			}
			final String[] items = new String[toshow.size()];
			for(int i=0;i<toshow.size();i++){
				items[i]=toshow.get(i);
			}
			lv = (ListView) findViewById(R.id.list);
			ArrayAdapter<String> aa = new ArrayAdapter<String>(
					this,
					android.R.layout.simple_list_item_1,
					android.R.id.text1,
					items);
			lv.setAdapter(aa);
			lv.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
		    		try{
		    			Intent myIntentA1A2 = new Intent(Pedido.this,PedidoMesa.class);
		    			Bundle toSend = new Bundle();
		    			toSend.putInt("mesa", position);
		    			myIntentA1A2.putExtras(toSend);
		    			startActivity(myIntentA1A2);
		    			
		    		}catch(Exception e){
		    			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		    		}
				}
				
			});
			
			
		}catch(Exception e){
			Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}//onCreate
	
	public void onPause(){
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
    		int num=0;
    		List<String> toret = new ArrayList<String>();
    		//hard-coded SQL select with no arguments
			String mySQL ="select num_mesas from Opciones";
			Cursor c1 = db.rawQuery(mySQL, null);
			if(c1.getCount() == 0){
				return toret;
			}else{
				c1.moveToFirst();
				num=c1.getInt(0);
				for(int i=0;i<num;i++){
					toret.add("Mesa"+(i+1));
				}
				return toret;
			}

		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
    }

}
