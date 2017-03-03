package com.devbaltasarq.whoami.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.devbaltasarq.whoami.R;
import com.devbaltasarq.whoami.core.DatosPersonales;
import com.devbaltasarq.whoami.core.DatosPersonalesExtendidos;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        final EditText edNombre = (EditText) this.findViewById( R.id.edNombre );
        final EditText edEmail = (EditText) this.findViewById( R.id.edEmail );
        final EditText edDireccion = (EditText) this.findViewById( R.id.edDireccion );

        edNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.datosPersonalesExtendidos.setNombre( edNombre.getText().toString() );
            }
        });

        edDireccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.datosPersonalesExtendidos.setDireccion( edDireccion.getText().toString() );
            }
        });

        edEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.datosPersonalesExtendidos.setEmail( edEmail.getText().toString() );
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();

        SharedPreferences prefs = this.getPreferences( Context.MODE_PRIVATE );
        String nombre = prefs.getString( "nombre", "" );
        String email = prefs.getString( "email", "" );
        String dir = prefs.getString( "direccion", "" );

        this.datosPersonalesExtendidos = new DatosPersonalesExtendidos();

        this.datosPersonalesExtendidos.setNombre( nombre );
        this.datosPersonalesExtendidos.setEmail( email );
        this.datosPersonalesExtendidos.setDireccion( dir );

        final EditText edNombre = (EditText) this.findViewById( R.id.edNombre );
        final EditText edEmail = (EditText) this.findViewById( R.id.edEmail );
        final EditText edDireccion = (EditText) this.findViewById( R.id.edDireccion );

        edNombre.setText( this.datosPersonalesExtendidos.getNombre() );
        edDireccion.setText( this.datosPersonalesExtendidos.getDireccion() );
        edEmail.setText( this.datosPersonalesExtendidos.getEmail() );
    }

    @Override
    public void onPause()
    {
        super.onPause();

        SharedPreferences.Editor edit = this.getPreferences( Context.MODE_PRIVATE ).edit();

        //edit.putString( "info", this.datosPersonales.toString() );
        edit.putString( "nombre", this.datosPersonalesExtendidos.getNombre() );
        edit.putString( "email", this.datosPersonalesExtendidos.getEmail() );
        edit.putString( "direccion", this.datosPersonalesExtendidos.getDireccion() );

        edit.apply();
    }

    private DatosPersonalesExtendidos datosPersonalesExtendidos;
}
