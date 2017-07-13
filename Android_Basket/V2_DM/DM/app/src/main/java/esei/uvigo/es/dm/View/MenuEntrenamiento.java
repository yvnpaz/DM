package esei.uvigo.es.dm.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import esei.uvigo.es.dm.Core.Entrenamiento;
import esei.uvigo.es.dm.Core.Partido;
import esei.uvigo.es.dm.R;

import static esei.uvigo.es.dm.View.MenuEquipo.CODIGO_EDICION_JUGADOR;

public class MenuEntrenamiento extends AppCompatActivity {


    protected static final int CODIGO_EDICION_PARTIDO = 100;
    protected static final int CODIGO_ADICION_PARTIDO = 102;

    private ArrayAdapter<Entrenamiento> adaptadorItems;
    List<String> toshow = new ArrayList<String>();
    private Controlador app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_entrenamiento);

        this.app = (Controlador) this.getApplication();
        toshow = app.queryEntreno();

        ListView lvListaEntreno = (ListView) this.findViewById( R.id.lvListaEntreno );
        Button btInsertaEnt = (Button) this.findViewById( R.id.btInsertaEntreno );

        // Lista completa
        this.adaptadorItems = new ArrayAdapter<>(
                this,
                android.R.layout.simple_selectable_list_item,
                android.R.id.text1,
                app.getItemListEntreno() );
        lvListaEntreno.setAdapter( this.adaptadorItems );

        //Inserta entreno
        btInsertaEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent subActividad = new Intent( MenuEntrenamiento.this, EntrenamientoEditionActivity.class );

                subActividad.putExtra( "pos", -1 );
                MenuEntrenamiento.this.startActivityForResult( subActividad, CODIGO_ADICION_PARTIDO );
            }
        });

        // Ver un Entrenamiento
        lvListaEntreno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent subActividad = new Intent( MenuEntrenamiento.this, ListaEntrenamiento.class );

                subActividad.putExtra( "pos", i );//i es la posicion de la seleccion de la lista
                MenuEntrenamiento.this.startActivity( subActividad );
            }
        });

        this.updateStatus();
        this.registerForContextMenu(lvListaEntreno);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo cmi)
    {
        super.onCreateContextMenu(menu, view, cmi);

        if (view.getId() == R.id.lvListaEntreno)
        {
            this.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            menu.setHeaderTitle("Opciones");
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem menuitem)
    {
        boolean toret = false;
        switch (menuitem.getItemId())
        {

            case R.id.opEliminar: toret=true;

                int posEli = ( (AdapterView.AdapterContextMenuInfo) menuitem.getMenuInfo() ).position;

                MenuEntrenamiento.this.adaptadorItems.remove(app.getItemListEntreno().get(posEli));
                MenuEntrenamiento.this.adaptadorItems.notifyDataSetChanged();

                app.deleteEnt(toshow.get( posEli ));
                this.updateStatus();
//                String res = toshow.get( posEli );
//                Toast.makeText(this, "Entrenamiento borrado con id___ " + res, Toast.LENGTH_LONG).show();

                break;
            case R.id.opModificar: toret=true;
                Intent subActividad = new Intent( MenuEntrenamiento.this, EntrenamientoEditionActivity.class );
                int posMod = ( (AdapterView.AdapterContextMenuInfo) menuitem.getMenuInfo() ).position;
//                Toast.makeText(this, "Partido borrado con id___ " + posMod, Toast.LENGTH_LONG).show();
                subActividad.putExtra( "pos", posMod );
                MenuEntrenamiento.this.startActivityForResult( subActividad, CODIGO_EDICION_JUGADOR );

        }
        return toret;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if ( requestCode == CODIGO_ADICION_PARTIDO
                && resultCode == Activity.RESULT_OK )
        {
            this.adaptadorItems.notifyDataSetChanged();
            this.updateStatus();
        }

        if ( requestCode == CODIGO_EDICION_PARTIDO
                && resultCode == Activity.RESULT_OK )
        {
            this.adaptadorItems.notifyDataSetChanged();
        }

        return;
    }

    private void updateStatus()
    {
        TextView lblNum = (TextView) this.findViewById( R.id.lblNum );

        lblNum.setText( Integer.toString( this.adaptadorItems.getCount() ) );
    }
}
