package uvigo.esei.dm;

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

public class PedidoMesa extends Activity {
	int num_mesa=0;
	GridView gridview;
	
	Integer[] Images = {
			es.uvigo.esei.dm1516.p45.R.drawable.anadir,
			es.uvigo.esei.dm1516.p45.R.drawable.listar,
			es.uvigo.esei.dm1516.p45.R.drawable.modificar,
			es.uvigo.esei.dm1516.p45.R.drawable.limpiar
	};
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(es.uvigo.esei.dm1516.p45.R.layout.pedido_mesa);
		Intent myCallerIntentHandler = getIntent();
		Bundle myBundle = myCallerIntentHandler.getExtras();
		int param = myBundle.getInt("mesa");
		num_mesa=param+1;
		gridview = (GridView) findViewById(es.uvigo.esei.dm1516.p45.R.id.gridview);
		gridview.setAdapter(new MyImageAdapter(this));
		gridview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView parent,View view,
					int position,long id){
				if(position == 0){
		    		try{
		    			Intent myIntenttoAnadir = new Intent(PedidoMesa.this,PedidoMesaAnadir.class);
		    			Bundle toSend = new Bundle();
		    			toSend.putInt("mesa", num_mesa);
		    			myIntenttoAnadir.putExtras(toSend);
		    			startActivity(myIntenttoAnadir);
		    			
		    		}catch(Exception e){
		    			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		    		}
					
				}
				if(position == 1){
		    		try{
		    			Intent myIntenttoListar = new Intent(PedidoMesa.this,PedidoMesaListar.class);
		    			Bundle toSend = new Bundle();
		    			toSend.putInt("mesa", num_mesa);
		    			myIntenttoListar.putExtras(toSend);
		    			startActivity(myIntenttoListar);
		    			
		    		}catch(Exception e){
		    			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		    		}
					
				}
				if(position == 2){
		    		try{
		    			Intent myIntenttoModificar = new Intent(PedidoMesa.this,PedidoMesaModificar.class);
		    			Bundle toSend = new Bundle();
		    			toSend.putInt("mesa", num_mesa);
		    			myIntenttoModificar.putExtras(toSend);
		    			startActivity(myIntenttoModificar);
		    			
		    		}catch(Exception e){
		    			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		    		}
				}
				if(position == 3){
		    		try{
		    			Intent myIntenttoLimpiar = new Intent(PedidoMesa.this,PedidoMesaLimpiar.class);
		    			Bundle toSend = new Bundle();
		    			toSend.putInt("mesa", num_mesa);
		    			myIntenttoLimpiar.putExtras(toSend);
		    			startActivity(myIntenttoLimpiar);
		    			
		    		}catch(Exception e){
		    			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		    		}
				}
			}
		});
		
	}//onCreate
	
	
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
