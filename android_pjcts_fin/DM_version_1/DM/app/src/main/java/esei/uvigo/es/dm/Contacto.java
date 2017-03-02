package esei.uvigo.es.dm;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Contacto extends Activity {
	
	WebView browser;
	
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.contacto );
		browser = (WebView) findViewById( R.id.webkit);
		browser.getSettings().setJavaScriptEnabled(true); // allow scripts
		browser.setWebViewClient( new WebViewClient() ); // page navigation
		
		
		browser.loadUrl("http://www.marca.com");
		
		}// onCreate 
	
	// /////////////////////////////////////////////////////////////
	// browser navigation implemented through the menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu( menu );
		this.getMenuInflater().inflate( R.menu.webviewmenu, menu );
		return true;
	}// onCreateOptionsMenu

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String option = item.getTitle().toString();
		if (option.equals("Forward Page")) 
			browser.goForward();		
		if (option.equals("Back Page")) 
			browser.goBack();
		return true;
	}//onOptionsItemSelected
}

