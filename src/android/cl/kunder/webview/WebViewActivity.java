package cl.kunder.webview;

import android.os.Bundle;

import org.apache.cordova.CordovaActivity;
import android.content.ContextWrapper;
import java.io.*;
import android.widget.Toast;

public class WebViewActivity extends CordovaActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String url = b.getString("url");
		if(url.startsWith("file:///")) {
			loadUrl(url);
		} else if (url.startsWith("downloads://")) {
			String[] parts = url.split("downloads://");
			ContextWrapper contextWrapper = new ContextWrapper(this);
			
			String cUrl = "file:///" + contextWrapper.getFilesDir().toString() + "/files/downloads/" + parts[1];
			
			/*
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(this, cUrl, duration);
			toast.show();
			*/
			
			loadUrl(cUrl);
			
		} else {
			loadUrl((url.matches("^(.*://|javascript:)[\\s\\S]*$")?"":"file:///android_asset/www/")+url);
		}
    }
}