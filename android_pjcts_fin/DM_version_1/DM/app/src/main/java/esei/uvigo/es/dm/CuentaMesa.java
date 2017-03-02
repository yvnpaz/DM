package uvigo.esei.dm;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CuentaMesa extends Activity {
	int num_mesa=0;
	TextView txtview;
	String mostrar="";
	SQLiteDatabase db;
	List<String[]> infoProducto = new ArrayList<String[]>();
	List<String[]> infoMesa = new ArrayList<String[]>();
	List<String> Producto = new ArrayList<String>();
	List<String> Cantidad = new ArrayList<String>();
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(es.uvigo.esei.dm1516.p45.R.layout.cuenta_mesa);
		Intent myCallerIntentHandler = getIntent();
		Bundle myBundle = myCallerIntentHandler.getExtras();
		int param = myBundle.getInt("mesa");
		num_mesa=param+1;
		txtview = (TextView) findViewById(es.uvigo.esei.dm1516.p45.R.id.txtview);
		try{
			openDatabase();
			createTables();
			infoProducto=query();
			infoMesa=query2();
			if(infoMesa.isEmpty()){
				txtview.setText("No hay pedidos");
			}else{
				String[] aux = new String[3];
				for(int i=0;i<infoMesa.size();i++){
					aux = infoMesa.get(i);
					if(Integer.parseInt(aux[2])==num_mesa){
					Producto.add(aux[0]);
					Cantidad.add(aux[1]);
					}
				}
				if(Producto.isEmpty()) txtview.setText("La mesa actual no tiene pedidos");
				else{
					String aux2="";
					String[] aux3 = new String[3];
					float precio=0;
					float total=0;
					int cantidad=0;
					for(int j=0;j<Producto.size();j++){
						aux2=Producto.get(j);
						for(int k=0;k<infoProducto.size();k++){
							aux3 = infoProducto.get(k);
							if(aux3[0].equals(aux2)) precio = Float.parseFloat(aux3[1]);
						}
						cantidad = Integer.parseInt(Cantidad.get(j));
						precio=precio*cantidad;
						total=total+precio;
						mostrar+="Producto:"+aux2+"\n Cantidad:"+cantidad+"\n Coste:"+precio+"\n";
					}
					mostrar+="\n\n TOTAL: "+total;
					txtview.setText(mostrar);
				}
			}
			
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
    
    private List<String[]> query(){
    	try {
    		List<String[]> toret = new ArrayList<String[]>();
    		//hard-coded SQL select with no arguments
			String mySQL ="select * from Producto";
			Cursor c1 = db.rawQuery(mySQL, null);
			if(c1.moveToFirst()){
				do{
					String[] tolist={
							c1.getString(1),
							Float.toString(c1.getFloat(2)),
							c1.getString(3)
					};
					toret.add(tolist);
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
    
    private List<String[]> query2(){
    	try {
    		List<String[]> toret = new ArrayList<String[]>();
    		//hard-coded SQL select with no arguments
			String mySQL ="select * from Pedido";
			Cursor c1 = db.rawQuery(mySQL, null);
			if(c1.moveToFirst()){
				do{
					String[] tolist={
							c1.getString(1),
							Integer.toString(c1.getInt(2)),
							Integer.toString(c1.getInt(3))
					};
					toret.add(tolist);
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
    
}
