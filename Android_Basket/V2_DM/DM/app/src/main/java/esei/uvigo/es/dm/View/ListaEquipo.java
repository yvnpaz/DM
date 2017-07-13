package esei.uvigo.es.dm.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import esei.uvigo.es.dm.R;

public class ListaEquipo extends AppCompatActivity {

    private Controlador app;
    List<String> toshow = new ArrayList<String>();
    List<String> toshowP = new ArrayList<String>();
    int posJug = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipo);

        final TextView txtNombre = (TextView) this.findViewById( R.id.txtNombreEquipo );
        final TextView txtTipo = (TextView) this.findViewById( R.id.txtTipoEquipo );
        final TextView txtPuntuacion = (TextView) this.findViewById( R.id.txtPunt );

        this.app = (Controlador) this.getApplication();
        toshow = app.queryE();
        toshowP = app.queryP();

        Intent datosEnviados = this.getIntent();
        final int pos = datosEnviados.getExtras().getInt( "extra" );

        String nombre = "";
        String tipo = "";

        nombre = app.getItemListE().get( pos ).getNombre();
        tipo = app.getItemListE().get( pos ).getTipo();

        txtNombre.setText( nombre );
        txtTipo.setText( tipo );

        if( app.getPuntuacion(toshow.get(pos)) > 0 )
        {
            int var = app.getPuntuacion(toshowP.get(pos));
            txtPuntuacion.setText(var+ " pts");
        }

        posJug = pos;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu( menu );
        this.getMenuInflater().inflate( R.menu.action_menu_equipo, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch( menuItem.getItemId() )
        {
            case R.id.opModificarEquipo:

                Toast.makeText(this, "Equipo modificado "+posJug, Toast.LENGTH_LONG).show();

                final EditText edEquipo = new EditText( ListaEquipo.this );
                final EditText edTipo   = new EditText( ListaEquipo.this );

                AlertDialog.Builder dlg = new AlertDialog.Builder( ListaEquipo.this );
                dlg.setTitle( "Modificar Equipo" );

                LinearLayout ver = new LinearLayout(this);
                ver.setOrientation(LinearLayout.VERTICAL);
                ver.addView( edEquipo );
                ver.addView( edTipo );
                dlg.setView(ver);

                dlg.setPositiveButton("+", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        app.modifyEquipo(toshow.get(posJug),edEquipo.getText().toString(),edTipo.getText().toString());

                        ListaEquipo.this.setResult( Activity.RESULT_OK );
                        ListaEquipo.this.finish();

                    }
                });

                dlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Cancelado.
                    }
                });

                dlg.create().show();

                return true;

            default:
                return super.onOptionsItemSelected(menuItem);

        }
    }
}
