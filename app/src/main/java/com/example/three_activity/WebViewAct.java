package com.example.three_activity;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

public class WebViewAct extends Fragment {
    WebView webView;
    SwipeRefreshLayout refreshLayout;
    ImageButton refresh;
    ImageButton back,next;
    ImageButton zoomIn,zoomOut;
    EditText urlT;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_webview, container, false);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        urlT = (EditText) view.findViewById(R.id.url);
        webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        refresh = (ImageButton) view.findViewById(R.id.refresh);
        back = (ImageButton) view.findViewById(R.id.back);
        next = (ImageButton) view.findViewById(R.id.next);
        zoomIn = (ImageButton) view.findViewById(R.id.plus);
        zoomOut = (ImageButton) view.findViewById(R.id.minus);

        new LoaderWeb(webView,urlT,refresh,back,next).start();

        updateBtns();

        refreshLayout.setOnRefreshListener(
                () -> {
                    refreshLayout.setRefreshing(false);
                    if (refresh.isEnabled()) {
                    }
                    else {
                        refresh.setEnabled(true);
                        refresh.setVisibility(View.VISIBLE);
                        webView.reload();
                        updateBtns();
                    }
                }
        );
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forward();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomin();
            }
        });
        zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomout();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.stopLoading();
            }
        });
        return view;
    }

    public void back() {
        webView.goBack();
        updateBtns();
    }

    public void forward() {
        webView.goForward();
        updateBtns();
    }

    public void zoomin() {
        webView.zoomIn();
    }

    public void zoomout() {
        webView.zoomOut();
    }

    public void updateBtns(){
        if (webView.canGoBack())
            back.setEnabled(true);
        else
            back.setEnabled(false);
        if (webView.canGoForward())
            next.setEnabled(true);
        else
            next.setEnabled(false);
    }

}