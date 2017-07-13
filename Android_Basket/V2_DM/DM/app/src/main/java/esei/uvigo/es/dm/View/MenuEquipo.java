package esei.uvigo.es.dm.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
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

import esei.uvigo.es.dm.Core.Jugador;
import esei.uvigo.es.dm.R;

public class MenuEquipo extends AppCompatActivity {

    protected static final int CODIGO_EDICION_JUGADOR = 100;
    protected static final int CODIGO_ADICION_JUGADOR = 102;


    private ArrayAdapter<Jugador> adaptadorItems;
    List<String> toshow = new ArrayList<String>();
    private Controlador app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_equipo);


        this.app = (Controlador) this.getApplication();
        toshow = app.query();

        ListView lvLista = (ListView) this.findViewById( R.id.lvLista );
        Button btInserta = (Button) this.findViewById( R.id.btInserta );

        // Lista completa
        this.adaptadorItems = new ArrayAdapter<>(
                this,
                android.R.layout.simple_selectable_list_item,
                app.getItemListJ() );
        lvLista.setAdapter( this.adaptadorItems );

        // Inserta
        btInserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent subActividad = new Intent( MenuEquipo.this, JugadorEditionActivity.class );

                subActividad.putExtra( "pos", -1 );
                MenuEquipo.this.startActivityForResult( subActividad, CODIGO_ADICION_JUGADOR );
            }
        });


        // Ver un Jugador
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent subActividad = new Intent( MenuEquipo.this, ListaJugador.class );

                subActividad.putExtra( "pos", i );//i es la posicion de la seleccion de la lista
                MenuEquipo.this.startActivity( subActividad );

            }
        });

        this.updateStatus();
        this.registerForContextMenu(lvLista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo cmi)
    {
        super.onCreateContextMenu(menu, view, cmi);

        if (view.getId() == R.id.lvLista)
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
                MenuEquipo.this.adaptadorItems.remove(app.getItemListJ().get(posEli));
                MenuEquipo.this.adaptadorItems.notifyDataSetChanged();

                String var = toshow.get( posEli );
                app.deleteJ(var);
                this.updateStatus();
//                Toast.makeText(this, "Que es var: " + var, Toast.LENGTH_LONG).show();

                break;
            case R.id.opModificar: toret=true;

                Intent subActividad = new Intent( MenuEquipo.this, JugadorEditionActivity.class );
                int posMod = ( (AdapterView.AdapterContextMenuInfo) menuitem.getMenuInfo() ).position;
                subActividad.putExtra( "pos", posMod );
                MenuEquipo.this.startActivityForResult( subActividad, CODIGO_EDICION_JUGADOR );
        }
            return toret;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if ( requestCode == CODIGO_ADICION_JUGADOR
                && resultCode == Activity.RESULT_OK )
        {
            this.adaptadorItems.notifyDataSetChanged();
            this.updateStatus();
        }

        if ( requestCode == CODIGO_EDICION_JUGADOR
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
