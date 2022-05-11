package com.example.three_activity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoaderWeb extends Thread{
    String siteName = "https://www.ivi.ru/";
    WebView webView;
    EditText urlT;
    ImageButton refresh;
    ImageButton back;
    ImageButton next;

    public LoaderWeb(WebView webView, EditText urlT, ImageButton refresh, ImageButton back, ImageButton next){
        this.webView = webView;
        this.urlT = urlT;
        this.refresh = refresh;
        this.back = back;
        this.next = next;
    }

    @Override
    public void run(){
        try {
            String content = getContent(siteName);
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadDataWithBaseURL(siteName, content, "text/html", "UTF-8", siteName);
                    webView.setWebViewClient(new WebViewClient(){
                        @Override
                        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                            if (urlT != null)
                            urlT.setText(url);
                            System.out.println(webView.canGoBack());
                            System.out.println(back != null);
                            if (webView.canGoBack() & (back != null))
                                back.setEnabled(true);
                            else if (back != null)
                                back.setEnabled(false);
                            if (webView.canGoForward() & (next != null))
                                next.setEnabled(true);
                            else if (next != null)
                                next.setEnabled(false);
                            super.doUpdateVisitedHistory(view,url,isReload);
                        }
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            if (refresh != null)
                            refresh.setEnabled(false);
                            refresh.setVisibility(View.INVISIBLE);
                            super.onPageFinished(view,url);
                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getContent(String path) throws IOException {
        BufferedReader reader = null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        try {
            URL url = new URL(path);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.connect();
            stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder buf = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line).append("\n");
            }
            return (buf.toString());
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
