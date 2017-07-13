package esei.uvigo.es.dm.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import esei.uvigo.es.dm.Core.Partido;
import esei.uvigo.es.dm.R;

import static esei.uvigo.es.dm.View.MenuEquipo.CODIGO_EDICION_JUGADOR;

public class MenuPartido extends AppCompatActivity {


    protected static final int CODIGO_EDICION_PARTIDO = 100;
    protected static final int CODIGO_ADICION_PARTIDO = 102;

    private ArrayAdapter<Partido> adaptadorItems;
    List<String> toshow = new ArrayList<String>();
    private Controlador app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_partido);

        this.app = (Controlador) this.getApplication();
        toshow = app.queryP();

        ListView lvListaPartido = (ListView) this.findViewById( R.id.lvListaPartido );
        Button btInserta = (Button) this.findViewById( R.id.btInsertaPartido );

        // Lista completa
        this.adaptadorItems = new ArrayAdapter<>(
                this,
                android.R.layout.simple_selectable_list_item,
                app.getItemListP() );
        lvListaPartido.setAdapter( this.adaptadorItems );

        //Inserta
        btInserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent subActividad = new Intent( MenuPartido.this, PartidoEditionActivity.class ); //Crear clase+layout MatchEdition

                subActividad.putExtra( "pos", -1 );
                MenuPartido.this.startActivityForResult( subActividad, CODIGO_ADICION_PARTIDO );
            }
        });

        // Ver un Partido
        lvListaPartido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent subActividad = new Intent( MenuPartido.this, ListaPartido.class );

                subActividad.putExtra( "pos", i );//i es la posicion de la seleccion de la lista
                MenuPartido.this.startActivity( subActividad );

            }
        });

        this.updateStatus();
        this.registerForContextMenu(lvListaPartido);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo cmi)
    {
        super.onCreateContextMenu(menu, view, cmi);

        if (view.getId() == R.id.lvListaPartido)
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
                MenuPartido.this.adaptadorItems.remove(app.getItemListP().get(posEli));
                MenuPartido.this.adaptadorItems.notifyDataSetChanged();
                String res = toshow.get( posEli );
                app.deleteP(res);
                this.updateStatus();

//                Toast.makeText(this, "Partido borrado con id___ " + res, Toast.LENGTH_LONG).show();
                break;
            case R.id.opModificar: toret=true;

                Intent subActividad = new Intent( MenuPartido.this, PartidoEditionActivity.class );
                int posMod = ( (AdapterView.AdapterContextMenuInfo) menuitem.getMenuInfo() ).position;
                subActividad.putExtra( "pos", posMod );
                MenuPartido.this.startActivityForResult( subActividad, CODIGO_EDICION_JUGADOR );
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
