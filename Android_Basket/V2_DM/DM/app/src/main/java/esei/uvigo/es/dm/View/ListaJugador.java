package esei.uvigo.es.dm.View;
import esei.uvigo.es.dm.Core.Jugador;
import esei.uvigo.es.dm.Core.SqlIO;
import esei.uvigo.es.dm.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListaJugador extends AppCompatActivity {

    private Controlador app;

    private boolean favorito=false;
    private boolean lesionado=false;
    private boolean titular=false;
    int posJug = 0;
    List<String> toshow = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jugador);

        final TextView txtNombre = (TextView) this.findViewById( R.id.txtNombre );
        final TextView txtPosicion = (TextView) this.findViewById( R.id.txtPosicion );
        final TextView txtDorsal = (TextView) this.findViewById( R.id.txtDorsal );
        final TextView txtEdad = (TextView) this.findViewById( R.id.txtEdad );
        final TextView txtFav = (TextView) this.findViewById( R.id.txtFavorito );
        final TextView txtLes = (TextView) this.findViewById( R.id.txtLesionado );
        final TextView txtTit = (TextView) this.findViewById( R.id.txtTitular );

        this.app = (Controlador) this.getApplication();
        toshow = app.query();

        Intent datosEnviados = this.getIntent();
        int pos = datosEnviados.getExtras().getInt( "pos" );

        String nombre = "";
        String posJugador = "";
        int dorsal=0;
        int edad=0;

        if ( pos >= 0 ) {
            nombre = app.getItemListJ().get( pos ).getNombre();
            posJugador = app.getItemListJ().get( pos ).getPosicion();
            dorsal = app.getItemListJ().get( pos ).getDorsal();
            edad = app.getItemListJ().get( pos ).getEdad();
        }

        txtNombre.setText( nombre );
        txtPosicion.setText( posJugador );
        txtDorsal.setText( Integer.toString( dorsal ) );
        txtEdad.setText( Integer.toString( edad ) );


        if( app.getFavorito(toshow.get(pos)) == 1 )
        {
            txtFav.setText("Es favorito");
            favorito=true;
        }
        else{
            txtFav.setText("NO es favorito");
            favorito=false;
        }


        if( app.getLesionado(toshow.get(pos)) == 1 )
        {
            txtLes.setText("Está lesionado!:(");
            lesionado=true;
        }
        else{
            txtLes.setText("NO está lesionado!:)");
            lesionado=false;
        }

        if( app.getTitular(toshow.get(pos)) == 1 )
        {
            txtTit.setText("Es titular");
            lesionado=true;
        }
        else{
            txtTit.setText("NO es titular");
            lesionado=false;
        }

        posJug = pos;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu( menu );
        this.getMenuInflater().inflate( R.menu.actions_menu, menu );
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch( menuItem.getItemId() )
        {
            case R.id.opFavorito:
                if(favorito==false) {
                    app.addFavorito(toshow.get(posJug));
                    int var = app.getFavorito(toshow.get(posJug));
                    if( var==1 ) {
                        final TextView txtFav = (TextView) this.findViewById(R.id.txtFavorito);
                        txtFav.setText("Es favorito");
                        favorito=true;
                    }
//                    Toast.makeText(this, "Jugador favorito a "+var, Toast.LENGTH_LONG).show();
                }
                else{
                    app.quitarFavorito(toshow.get(posJug));
                    int var = app.getFavorito(toshow.get(posJug));
                    if( var==0 ) {
                        final TextView txtFav = (TextView) this.findViewById(R.id.txtFavorito);
                        txtFav.setText("No es favorito");
                        favorito=false;
                    }
//                    Toast.makeText(this, "Jugador favorito a "+var, Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.opLesionado:
                if(lesionado==false) {
                    app.addLesionado(toshow.get(posJug));
                    int var = app.getLesionado(toshow.get(posJug));
                    if( var==1 ) {
                        final TextView txtLes = (TextView) this.findViewById(R.id.txtLesionado);
                        txtLes.setText("Está lesionado!:(");
                        lesionado=true;
                    }
//                    Toast.makeText(this, "Jugador lesionado es "+var, Toast.LENGTH_LONG).show();
                }
                else{
                    app.quitarLesionado(toshow.get(posJug));
                    int var = app.getLesionado(toshow.get(posJug));
                    if( var==0 ) {
                        final TextView txtLes = (TextView) this.findViewById(R.id.txtLesionado);
                        txtLes.setText("No está lesionado!:)");
                        lesionado=false;
                    }
//                    Toast.makeText(this, "Jugador Lesionado es "+var, Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.opTitular:
                if(titular==false) {
                    app.addTitular(toshow.get(posJug));
                    int var = app.getTitular(toshow.get(posJug));
                    if( var==1 ) {
                        final TextView txtTit = (TextView) this.findViewById(R.id.txtTitular);
                        txtTit.setText("Es titular");
                        titular=true;
                    }
//                    Toast.makeText(this, "Jugador titular es "+var, Toast.LENGTH_LONG).show();
                }
                else{
                    app.quitarTiutlar(toshow.get(posJug));
                    int var = app.getTitular(toshow.get(posJug));
                    if( var==0 ) {
                        final TextView txtTit = (TextView) this.findViewById(R.id.txtTitular);
                        txtTit.setText("NO es titular");
                        titular=false;
                    }
//                    Toast.makeText(this, "Jugador titular es "+var, Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }

}
