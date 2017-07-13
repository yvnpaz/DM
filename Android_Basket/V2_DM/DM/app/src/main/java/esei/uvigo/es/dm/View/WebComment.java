package esei.uvigo.es.dm.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.webkit.WebSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import esei.uvigo.es.dm.R;

public class WebComment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_comment);

        ImageButton btBackComment = (ImageButton) this.findViewById( R.id.btBack );
        WebView wvView = (WebView) this.findViewById( R.id.wvViewComment );

        btBackComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebComment.this.finish();
            }
        });

        this.configureWebView( wvView, "http://www.google.es", 12 );
    }

    private void configureWebView(WebView wvView, String url, int defaultFontSize)
    {
        WebSettings webSettings = wvView.getSettings();

        webSettings.setBuiltInZoomControls( true );
        webSettings.setDefaultFontSize( defaultFontSize );

        //Habilitar javascript
        webSettings.setJavaScriptEnabled( true );
        wvView.addJavascriptInterface( new WebAppInterface( this ), "WebComment");

        //La url es manejada por el webView y no por el navegador.
        wvView.setWebViewClient( new WebViewClient() );

        StringBuilder builder = new StringBuilder();
        InputStream in = null;

        try{
            String line;

            in = this.getAssets().open( "comment.html" );
            BufferedReader inf = new BufferedReader( new InputStreamReader( in ));

            while(( line = inf.readLine()) != null )
            {
                builder.append( line );
            }
        } catch (IOException e)
        {
            builder.append("<html><body><big>ERROR internal: loading asset</big></body></html>");
            Log.e("webCo.configureWebView", "error loading asset 'comment.html'");
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }catch (IOException exc)
            {
                    //sin comentar
            }
        }
        wvView.loadData( builder.toString(), "text/html", "utf-8" );
    }
}
