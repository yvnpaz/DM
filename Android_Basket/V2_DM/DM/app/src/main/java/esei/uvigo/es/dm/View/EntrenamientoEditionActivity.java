package esei.uvigo.es.dm.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import esei.uvigo.es.dm.R;

public class EntrenamientoEditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento_edition);

        final Button btGuardarEnt = (Button) this.findViewById( R.id.btGuardarEntreno );

        final EditText edNombre = (EditText) this.findViewById( R.id.edNombreEntreno );
        final EditText edTipoEnt = (EditText) this.findViewById( R.id.edTipoEntreno );
        final EditText edDescripcion = (EditText) this.findViewById( R.id.edDescripcion );

        final Controlador app = (Controlador) this.getApplication();

        Intent datosEnviados = this.getIntent();
        final int pos = datosEnviados.getExtras().getInt( "pos" );

        String nombreEnt = "";
        String tipoEnt = "";
        String descripcionEnt = "";

        if ( pos >= 0 ) {
            nombreEnt = app.getItemListEntreno().get( pos ).getNombre();
            tipoEnt = app.getItemListEntreno().get( pos ).getTipo();
            descripcionEnt = app.getItemListEntreno().get( pos ).getDescripcion();
        }

        edNombre.setText( nombreEnt );
        edTipoEnt.setText( tipoEnt );
        edDescripcion.setText( descripcionEnt );

        btGuardarEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nombreEntreno = edNombre.getText().toString();
                final String tipoEntreno = edTipoEnt.getText().toString();
                final String descripcionEntreno = edDescripcion.getText().toString();

                if ( pos >= 0 ) {
                    app.modifyEntrenamiento( pos, nombreEntreno, tipoEntreno, descripcionEntreno );
                } else {
                    app.addEntreno( nombreEntreno, tipoEntreno, descripcionEntreno );
                }

                EntrenamientoEditionActivity.this.setResult( Activity.RESULT_OK );
                EntrenamientoEditionActivity.this.finish();
            }
        });
        btGuardarEnt.setEnabled( false );

        edNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btGuardarEnt.setEnabled( edNombre.getText().toString().trim().length() > 0 );
            }
        });

        edTipoEnt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btGuardarEnt.setEnabled( edTipoEnt.getText().toString().trim().length() > 0 );
            }
        });

        edDescripcion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btGuardarEnt.setEnabled( edDescripcion.getText().toString().trim().length() > 0 );
            }
        });

    }

}
