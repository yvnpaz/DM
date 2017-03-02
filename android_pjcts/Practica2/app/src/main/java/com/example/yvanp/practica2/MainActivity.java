package com.example.yvanp.practica2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String nombre = this.leeInfo( "Como te llamas?");
        this.aviso( nombre );
    }
    private void aviso(String msg)
    {
        Toast.makeTest( this , msg, Toast.LENGTH_LONG).show();
    }

    private void avisoB(String msg)
    {
        AlertDialog.Builder dlg = new  AlertDialog.Builder( this );

        dlg.setMessage( msg );
        dlg.create().show();
    }

    private void pregunta(String msg)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        dlg.setMessage( msg );
        dlg.setPositiveButton( "Hola", null );
        dlg.setNegativeButton("Adios", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit( 0 );
            }
        });
        dlg.create().show();
    }

    private String leeInfo(String datos)
    {
        final StringBuilder toret = new StringBuilder();
        final EditText edInfo = new EditText( this );
        AlertDialog.Builder dlg = new AlertDialog( this );
        dlg.setTitle( datos );
        dlg.setView( edInfo );
        dlg.setPositiveButton( "Cancel", null );
        dlg.setNegativeButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toret.append(edInfo.getText().toString());
                MainActivity.this.aviso( toret.toString() );
            }
        });
        dlg.create().show();
        return toret.toString();
    }
}
