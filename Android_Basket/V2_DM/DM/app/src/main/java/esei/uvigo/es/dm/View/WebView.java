package esei.uvigo.es.dm.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import esei.uvigo.es.dm.R;

public class WebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);

        ImageButton btBack = (ImageButton) this.findViewById( R.id.btBack );
        android.webkit.WebView wvView = (android.webkit.WebView) this.findViewById( R.id.wvView );

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView.this.finish();
            }
        });

        wvView.loadUrl( "http://www.marca.com" );

    }
}
