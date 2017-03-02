package esei.uvigo.es.dm;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import esei.uvigo.es.dm.Core.TimeFetcher;

public class MainActivity extends Activity {

    Button btn;
    TextView appName;
    TextView authors;
    Intent Intservice;
    private Timer timer;
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn = (Button) findViewById(R.id.btn);
        appName = (TextView) findViewById(R.id.AppName);
        authors = (TextView) findViewById(R.id.authors);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //Nueva actividad
                    Intent myIntentMainMenu = new Intent(getApplicationContext(), MenuPrincipal.class);
                    startActivity(myIntentMainMenu);
                }catch(Exception e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        this.handler = new Handler();
    }

    protected void onDestroy(){
        super.onDestroy();
        try{
            stopService(Intservice);
        }catch(Exception e){
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        this.timer.cancel();
        this.timer.purge();
        this.timer = null;
    }
    @Override
    public void onResume()
    {
        super.onResume();

        this.setStatus( R.string.status_init );
        this.timer = new Timer();

        TimerTask taskFetchTime = new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.handler.post(new Runnable() {
                    public void run() {
                        try {
                            new TimeFetcher( MainActivity.this ).execute( new URL( TimeFetcher.TIME_URL ) );
                        } catch(MalformedURLException e)
                        {
                            MainActivity.this.setStatus( R.string.status_incorrect_url );
                        }
                    }
                });
            }
        };

        // Program the task for every ten seconds from nows
        timer.schedule( taskFetchTime, 0, 10000 );
    }

    public void setTimeInfo(String time, String timeZoneInfo, String gmtInfo)
    {
        final TextView lblTime = (TextView) this.findViewById( R.id.lblTime );
        final TextView lblGmtInfo = (TextView) this.findViewById( R.id.lblGmtInfo );
        final TextView lblTimeZoneInfo = (TextView) this.findViewById( R.id.lblTimeZoneInfo );

        lblTime.setText( time );
        lblTimeZoneInfo.setText( timeZoneInfo );
        lblGmtInfo.setText( gmtInfo );
    }

    public void setStatus(int msgId) {
        final TextView lblStatus = (TextView) this.findViewById( R.id.lblStatus );

        lblStatus.setText( msgId );
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu( menu );
        this.getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }// onCreateOptionsMenu

//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        String option = item.getTitle().toString();
//        if(option.equals("Start Music")){
//            Intservice = new Intent(getApplicationContext(),MusicService.class);
//            startService(Intservice);
//        }
//        if(option.equals("Stop Music")){
//            try{
//                stopService(Intservice);
//            }catch(Exception e){
//                //Toast.makeText(this,"Musica no iniciada", Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(),"Musica no iniciada",Toast.LENGTH_LONG).show();
//            }
//        }
//        return true;
//    }//onOptionsItemSelected
}
