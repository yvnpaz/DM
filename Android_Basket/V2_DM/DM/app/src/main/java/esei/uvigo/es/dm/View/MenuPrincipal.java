package esei.uvigo.es.dm.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import esei.uvigo.es.dm.R;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Button btnEquipo = (Button) this.findViewById( R.id.btnEquipo );
        Button btnPartido = (Button) this.findViewById( R.id.btnPartido );
        Button btnView = (Button) this.findViewById( R.id.btntWeb );
        Button btnComment = (Button) this.findViewById( R.id.btntWebComment );
        Button btnAddEquipo = (Button) this.findViewById( R.id.btn_addEquipo );
        Button btnEntreno = (Button) this.findViewById( R.id.btnEntreno );

        final Controlador app = (Controlador) this.getApplication();


        //EQUIPO
        btnEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //Nueva actividad Jugador
                    Intent myIntentMainMenu = new Intent( MenuPrincipal.this, MenuEquipo.class );
                    startActivity(myIntentMainMenu);
                }catch(Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        //PARTIDO
        btnPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //Nueva actividad Partido
                    Intent myPartido = new Intent( MenuPrincipal.this, MenuPartido.class );
                    startActivity(myPartido);
                }catch(Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        //WEBVIEW
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //Nueva actividad Web
                    Intent myWeb = new Intent( MenuPrincipal.this, WebView.class );
                    startActivity(myWeb);
                }catch(Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        //WEB_COMENTAR
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //Nueva actividad Partido
                    Intent myComment = new Intent( MenuPrincipal.this, WebComment.class );
                    startActivity(myComment);
                }catch(Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        //AÑADIR EQUIPO
        //Si existe un equipo ya no se puede dar de alta otro.
        btnAddEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //Numero de equipos
                    int var = app.getHayEquipo();

                    if(var == 0) {
                        //Nueva actividad Equipo
                        Intent myTeam = new Intent(MenuPrincipal.this, AnadirEquipo.class);
                        myTeam.putExtra( "extra", 0 );
                        startActivity(myTeam);
                    }else{
                        //Nueva actividad Equipo
                        Intent myTeam = new Intent(MenuPrincipal.this, ListaEquipo.class);
                        myTeam.putExtra( "extra", 0 );
                        startActivity(myTeam);
                    }


                }catch(Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        //ENTRENAMIENTOS
        btnEntreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //Nueva actividad Entreno
                    Intent myTrain = new Intent( MenuPrincipal.this, MenuEntrenamiento.class );
                    startActivity(myTrain);
                }catch(Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
