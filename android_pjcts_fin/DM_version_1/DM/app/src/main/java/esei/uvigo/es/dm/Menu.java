package esei.uvigo.es.dm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Menu extends Activity {
	GridView gridview;
	Integer[] Images = {
			R.drawable.anadirplato,
			R.drawable.modificarplato,
			R.drawable.eliminarplato,
			R.drawable.listarmenu,
		};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		gridview = (GridView) findViewById(R.id.gridview2);
		gridview.setAdapter(new MyImageAdapter(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView parent,View view,
					int position,long id){
				if(position == 0){
	    			try{
	    				Intent myIntenttoNuevoPlato = new Intent(Menu.this, AnadirPlato.class);
	    				startActivity(myIntenttoNuevoPlato);
	    			}catch(Exception e){
	        			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

	    			}
				}
				if(position == 1){
	    			try{
	    				Intent myIntenttoModificarPlato = new Intent(Menu.this,ModificarPlato.class);
	    				startActivity(myIntenttoModificarPlato);
	    			}catch(Exception e){
	        			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

	    			}
					
				}
				if(position == 2){
	    			try{
	    				Intent myIntenttoEliminarPlato = new Intent(Menu.this,EliminarPlato.class);
	    				startActivity(myIntenttoEliminarPlato);
	    			}catch(Exception e){
	        			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

	    			}					
				}
				if(position == 3){
	    			try{
	    				Intent myIntenttoListarMenu = new Intent(Menu.this,ListarMenu.class);
	    				startActivity(myIntenttoListarMenu);
	    			}catch(Exception e){
	        			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

	    			}					
				}
			}
		});
	}
	
	public class MyImageAdapter extends BaseAdapter {
		private Context mContext;

		public MyImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return Images.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}
		// create ImageViews for each thumbnail mentioned by the ArrayAdapter
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(150, 120));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(Images[position]);
			return imageView;
		}
	}// ImageAdapter
	
}
