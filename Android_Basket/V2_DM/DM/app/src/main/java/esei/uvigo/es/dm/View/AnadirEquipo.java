package esei.uvigo.es.dm.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import esei.uvigo.es.dm.R;

public class AnadirEquipo extends AppCompatActivity {


    private Controlador app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_equipo);

        this.app = (Controlador) this.getApplication();


        final Button btGuardarEquipo = (Button) this.findViewById( R.id.btGuardarEquipo );
        final EditText edNombre = (EditText) this.findViewById( R.id.edNombreEquipo );
        final EditText edTipo = (EditText) this.findViewById( R.id.edTipo );

        Intent datosEnviados = this.getIntent();
        final int pos = datosEnviados.getExtras().getInt( "extra" );

        String nombre = "";
        String tipoEquipo = "";

        if ( pos < 0 ) {
            nombre = app.getItemListE().get( pos ).getNombre();
            tipoEquipo = app.getItemListE().get( pos ).getTipo();
        }

        edNombre.setText( nombre );
        edTipo.setText( tipoEquipo );

        btGuardarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nombre = edNombre.getText().toString();
                final String posJugador = edTipo.getText().toString();

                app.addEquipo(nombre, posJugador);

                AnadirEquipo.this.setResult( Activity.RESULT_OK );
                AnadirEquipo.this.finish();
            }
        });

    }
}
