package esei.uvigo.es.dm;

import android.app.Activity;
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

public class ListarMenu extends Activity {
	SQLiteDatabase db;
	List<String[]> toshow = new ArrayList<String[]>();
	List<String> Bebida = new ArrayList<String>();
	List<String> Plato = new ArrayList<String>();
	List<String> Postre = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView( R.layout.listar_menu);
		
		try{
			openDatabase();
			createTables();
			TextView txtview = (TextView) findViewById( R.id.txtview);
			toshow = query();
			if(toshow.isEmpty()){
				txtview.setText("No hay datos almacenados");
			}
			String[] aux = new String[3];
			
			for(int i=0;i<toshow.size();i++){
				aux = toshow.get(i);
				if(aux[2].equals("Bebida")) Bebida.add(aux[0]);
				else if(aux[2].equals("Plato")) Plato.add(aux[0]);
				else Postre.add(aux[0]);
			}
			
			String mostrar = "\nBebidas:\n";
			
			if(!Bebida.isEmpty()){
				for(int j=0;j<Bebida.size();j++){
					mostrar+=Bebida.get(j)+"\n";
				}
			}
			
			mostrar+="\nPlatos:\n";
			if(!Plato.isEmpty()){
				for(int k=0;k<Plato.size();k++){
					mostrar+=Plato.get(k)+"\n";
				}
			}
			
			mostrar+="\nPostres:\n";
			if(!Postre.isEmpty()){
				for(int m=0;m<Postre.size();m++){
					mostrar+=Postre.get(m)+"\n";
				}
			}
			
			txtview.setText(mostrar);
			
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
}
