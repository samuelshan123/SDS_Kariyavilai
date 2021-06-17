package in.samuel.sds_kariyavilai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Youtube extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(),"Loading......", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_youtube);
        webview = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl("https://www.youtube.com/channel/UCDoKdLqD32u6bSIsq2yxAYw/videos");
        webview.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.canGoBack();
        } else {
            super.onBackPressed();
        }
    }
}