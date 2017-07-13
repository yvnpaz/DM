package esei.uvigo.es.dm.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import esei.uvigo.es.dm.R;

public class ListaEntrenamiento extends AppCompatActivity {


    private Controlador app;
    int posJug = 0;
    List<String> toshow = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_entrenamiento);

        final TextView txtNombre = (TextView) this.findViewById( R.id.txtNombre );
        final TextView txtTipo = (TextView) this.findViewById( R.id.txtTipoE );
        final TextView txtDescripcion = (TextView) this.findViewById( R.id.txtDescripcionE );
        final TextView txtHecho = (TextView) this.findViewById( R.id.txtHecho );

        this.app = (Controlador) this.getApplication();
        toshow = app.queryEntreno();

        Intent datosEnviados = this.getIntent();
        int pos = datosEnviados.getExtras().getInt( "pos" );

        String nombre = "";
        String tipo = "";
        String descripcion = "";

        if ( pos >= 0 ) {
            nombre = app.getItemListEntreno().get( pos ).getNombre();
            tipo = app.getItemListEntreno().get( pos ).getTipo();
            descripcion = app.getItemListEntreno().get( pos ).getDescripcion();
        }

        txtNombre.setText( nombre );
        txtTipo.setText( tipo );
        txtDescripcion.setText( descripcion );

        if( app.getHecho(toshow.get(pos)) == 1 )
        {
            txtHecho.setText("Está hecho!!!:P");
        }
        else{
            txtHecho.setText("No está Hecho!!!:P");
        }

        posJug = pos;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu( menu );
        this.getMenuInflater().inflate( R.menu.action_menu_entrenamiento, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch( menuItem.getItemId() )
        {
            case R.id.opHecho:

                if(app.getHecho(toshow.get(posJug)) == 1 ) {

                    app.quitarEntrenamiento(toshow.get(posJug));
                    final TextView txtHecho = (TextView) this.findViewById(R.id.txtHecho);
                    txtHecho.setText("No está hecho!!!:P");
//                    Toast.makeText(this, "ARRAY__"+app.getItemListEntreno().get(posJug).getHecho(), Toast.LENGTH_LONG).show();
//                    Toast.makeText(this, "BD__"+app.getHecho(toshow.get(posJug)), Toast.LENGTH_LONG).show();
                }
                else{

                    app.addEntrenamiento(toshow.get(posJug));
                    final TextView txtHecho = (TextView) this.findViewById(R.id.txtHecho);
                    txtHecho.setText("Está hecho!!!:P");
//                    Toast.makeText(this, "COMO ESTA__"+app.getItemListEntreno().get(posJug).getHecho(), Toast.LENGTH_LONG).show();
//                    Toast.makeText(this, "BD__"+app.getHecho(toshow.get(posJug)), Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }
}
