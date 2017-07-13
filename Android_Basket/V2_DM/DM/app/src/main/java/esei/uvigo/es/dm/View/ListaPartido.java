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

public class ListaPartido extends AppCompatActivity {

    private Controlador app;

    int posJug = 0;
    List<String> toshow = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_partido);

        final TextView txtLocal = (TextView) this.findViewById( R.id.txtLocal );
        final TextView txtMarcadorLocal = (TextView) this.findViewById( R.id.txtMarcadorLocal );
        final TextView txtVisitante = (TextView) this.findViewById( R.id.txtVisitante );
        final TextView txtMarcadorVisitante = (TextView) this.findViewById( R.id.txtMarcadorVisitante );


        this.app = (Controlador) this.getApplication();
        toshow = app.queryP();

        Intent datosEnviados = this.getIntent();
        int pos = datosEnviados.getExtras().getInt( "pos" );

        String local = "";
        int marcadorLocal = 0;
        String visitante = "";
        int marcadorVisitante = 0;

        if ( pos >= 0 ) {
            local = app.getItemListP().get( pos ).getEquipoLocal();
            marcadorLocal = app.getItemListP().get( pos ).getMarcadorLocal();
            visitante = app.getItemListP().get( pos ).getEquipoVisitante();
            marcadorVisitante = app.getItemListP().get( pos ).getMarcadorVisitante();
        }

        txtLocal.setText( local );
        txtMarcadorLocal.setText( Integer.toString( marcadorLocal ) );
        txtVisitante.setText( visitante );
        txtMarcadorVisitante.setText( Integer.toString( marcadorVisitante ) );

        posJug = pos;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu( menu );
        this.getMenuInflater().inflate( R.menu.action_menu_partido, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch( menuItem.getItemId() )
        {
            case R.id.opGanar:

                app.ganado(toshow.get(posJug));
//                int var = app.getRes(toshow.get(posJug));
//                Toast.makeText(this, "Resultado "+posJug, Toast.LENGTH_LONG).show();
//                Toast.makeText(this, "Posjug "+posJug, Toast.LENGTH_LONG).show();

                return true;

            case R.id.opPerdido:
                    app.perdido(toshow.get(posJug));
//                int var2 = app.getRes(toshow.get(posJug));
//                Toast.makeText(this, "Equipo a "+var2, Toast.LENGTH_LONG).show();
                return true;

            case R.id.opEmpate:
                app.empate(toshow.get(posJug));
//                int var3 = app.getRes(toshow.get(posJug));
//                Toast.makeText(this, "Equipo a "+var3, Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }

}
