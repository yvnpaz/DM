package esei.uvigo.es.dm.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import esei.uvigo.es.dm.R;

public class JugadorEditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_jugador_edition );

        final Button btGuardar = (Button) this.findViewById( R.id.btGuardar );

        final EditText edNombre = (EditText) this.findViewById( R.id.edNombre );
        final EditText edPos = (EditText) this.findViewById( R.id.edPosicion );
        final EditText edDorsal = (EditText) this.findViewById( R.id.edDorsal );
        final EditText edEdad = (EditText) this.findViewById( R.id.edEdad );

        final Controlador app = (Controlador) this.getApplication();

        Intent datosEnviados = this.getIntent();
        final int pos = datosEnviados.getExtras().getInt( "pos" );
        String nombre = "";
        String posJugador = "";
        String posicion = "";
        int dorsal = 0;
        int edad = 0;

        if ( pos >= 0 ) {
            nombre = app.getItemListJ().get( pos ).getNombre();
            posJugador = app.getItemListJ().get( pos ).getPosicion();
            dorsal = app.getItemListJ().get( pos ).getDorsal();
            edad = app.getItemListJ().get( pos ).getEdad();

        }

        edNombre.setText( nombre );
        edPos.setText( posJugador );
        edDorsal.setText( Integer.toString( dorsal ) );
        edEdad.setText( Integer.toString( edad ) );

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nombre = edNombre.getText().toString();
                final String posJugador = edPos.getText().toString();
                final int dorsal = Integer.parseInt( edDorsal.getText().toString() );
                final int edad = Integer.parseInt( edEdad.getText().toString() );

                if ( pos >= 0 ) {
                    app.modifyJugador( pos, nombre, posJugador, dorsal, edad );
                } else {
                   app.addJugador( nombre, posJugador, dorsal, edad );
                }

                JugadorEditionActivity.this.setResult( Activity.RESULT_OK );
                JugadorEditionActivity.this.finish();
            }
        });
        btGuardar.setEnabled( false );

        edNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btGuardar.setEnabled( edNombre.getText().toString().trim().length() > 0 );
            }
        });

        edPos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btGuardar.setEnabled( edPos.getText().toString().trim().length() > 0 );
            }
        });

        edDorsal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int dorsal = 0;

                try {
                    dorsal = Integer.parseInt( edDorsal.getText().toString() );
                } catch(NumberFormatException exc) {
                    Log.w( "JugadorEditionACtivity", "dorsal no puede ser convertido a número" );
                }

                btGuardar.setEnabled( dorsal > 0 );
            }
        });

        edEdad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int edad = 0;

                try {
                    edad = Integer.parseInt( edEdad.getText().toString() );
                } catch(NumberFormatException exc) {
                    Log.w( "JugadorEditionACtivity", "edad no puede ser convertido a número" );
                }

                btGuardar.setEnabled( edad > 0 );
            }
        });
    }
}
