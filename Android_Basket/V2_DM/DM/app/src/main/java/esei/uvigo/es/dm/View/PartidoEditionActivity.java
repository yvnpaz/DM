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

public class PartidoEditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido_edition);

        final Button btGuardarPartido = (Button) this.findViewById( R.id.btGuardarPartido );

        final EditText edLocal = (EditText) this.findViewById( R.id.edLocal );
        final EditText edMarcadorLocal = (EditText) this.findViewById( R.id.edMarcLocal );
        final EditText edVisitante = (EditText) this.findViewById( R.id.edVisitante );
        final EditText edMarcadorVisitante = (EditText) this.findViewById( R.id.edMarcVisitante );

        final Controlador app = (Controlador) this.getApplication();

        Intent datosEnviados = this.getIntent();
        final int pos = datosEnviados.getExtras().getInt( "pos" );


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

        edLocal.setText( local );
        edMarcadorLocal.setText( Integer.toString( marcadorLocal ) );
        edVisitante.setText( visitante );
        edMarcadorVisitante.setText( Integer.toString( marcadorVisitante ) );

        btGuardarPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String local = edLocal.getText().toString();
                final int marcadorLocal = Integer.parseInt( edMarcadorLocal.getText().toString() );
                final String visitante = edVisitante.getText().toString();
                final int marcadorVisitante = Integer.parseInt( edMarcadorVisitante.getText().toString() );

                if ( pos >= 0 ) {
                    app.modifyPartido( pos, local, visitante, marcadorLocal, marcadorVisitante );
                } else {
                    app.addPartido( local, visitante, marcadorLocal, marcadorVisitante );
                }
                PartidoEditionActivity.this.setResult( Activity.RESULT_OK );
                PartidoEditionActivity.this.finish();
            }
        });

        btGuardarPartido.setEnabled( false );


        edLocal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btGuardarPartido.setEnabled( edLocal.getText().toString().trim().length() > 0 );
            }
        });

        edMarcadorLocal.addTextChangedListener(new TextWatcher() {
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
                    dorsal = Integer.parseInt( edMarcadorLocal.getText().toString() );
                } catch(NumberFormatException exc) {
                    Log.w( "PartidoEditionACtivity", "Marcador local no puede ser convertido a número" );
                }
                btGuardarPartido.setEnabled( dorsal >= 0 );
            }
        });

        edVisitante.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btGuardarPartido.setEnabled( edVisitante.getText().toString().trim().length() > 0 );
            }
        });

        edMarcadorVisitante.addTextChangedListener(new TextWatcher() {
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
                    dorsal = Integer.parseInt( edMarcadorVisitante.getText().toString() );
                } catch(NumberFormatException exc) {
                    Log.w( "PartidoEditionACtivity", "Marcador visitante no puede ser convertido a número" );
                }
                btGuardarPartido.setEnabled( dorsal >= 0 );
            }
        });


    }
}
