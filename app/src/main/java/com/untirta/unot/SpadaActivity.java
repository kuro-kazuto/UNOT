package com.untirta.unot;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.Objects;

public class SpadaActivity extends AppCompatActivity {
  private WebView webView;
  private TextView error;

  SwipeRefreshLayout swipe;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);
    webView = findViewById(R.id.webview);
    error = findViewById(R.id.error);
    gotoPage();

    //Buttom
    BottomNavigationItemView btnBack = findViewById(R.id.navigation_back);
    BottomNavigationItemView btnForward = findViewById(R.id.navigation_forward);
    BottomNavigationItemView btnReload = findViewById(R.id.navigation_reload);
    BottomNavigationItemView btnHome = findViewById(R.id.navigation_to_home);
    BottomNavigationItemView btnClose = findViewById(R.id.navigation_close);

    swipe = findViewById(R.id.swipe);
    swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        webView.reload();
      }
    });

    btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (webView.isFocused() && webView.canGoBack()) {
          webView.goBack();
          swipe.setRefreshing(true);
        }
      }
    });
    btnForward.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (webView.isFocused() && webView.canGoForward()) {
          webView.goForward();
          swipe.setRefreshing(true);
        }
      }
    });
    btnReload.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        webView.reload();
        swipe.setRefreshing(true);
      }
    });
    btnHome.setOnClickListener(new View.OnClickListener() {
      String url = "http://spada.untirta.ac.id/"; //Change URL
      @Override
      public void onClick(View v) {
        webView.loadUrl(url);
        swipe.setRefreshing(true);
      }
    });
    btnClose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        webView.clearCache(true);
        webView.clearHistory();
        deleteCookies();
        Intent i = new Intent(SpadaActivity.this, MainActivity2.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
      }
    });

  }

  private void gotoPage() {
    ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo nInfo = Objects.requireNonNull(manager).getActiveNetworkInfo();
    if (nInfo != null && nInfo.isConnectedOrConnecting()) {
      // isConnected = true;
      Button theButton = findViewById(R.id.button);
      theButton.setVisibility(View.GONE);
      error.setVisibility(View.GONE);
      webView.setVisibility(View.VISIBLE);
      //URL
      String url = "http://spada.untirta.ac.id/"; //Change URL
      //
      WebStorage.getInstance().deleteAllData();

      // Clear all the cookies
      webView.getSettings().setLoadWithOverviewMode(true);
      webView.getSettings().setUseWideViewPort(true);
      webView.getSettings().setSupportZoom(true);
      webView.getSettings().setBuiltInZoomControls(true);
      webView.getSettings().setDisplayZoomControls(false);
      webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
      webView.getSettings().setMinimumFontSize(12);
      webView.getSettings().setDomStorageEnabled(true);
      webView.getSettings().setJavaScriptEnabled(true);
      String newUA= "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Safari/602.1.50";
      webView.getSettings().setUserAgentString(newUA);
      webView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
      webView.loadUrl(url);

    } else {
      error.setVisibility(View.VISIBLE);
      swipe.setRefreshing(false);
      Button theButton = findViewById(R.id.button);
      theButton.setVisibility(View.VISIBLE);
      theButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = getIntent();
          overridePendingTransition(0, 0);
          intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
          finish();
          overridePendingTransition(0, 0);
          startActivity(intent);
        }
      });
    }
  }

  private class Callback extends WebViewClient {  //HERE IS THE MAIN CHANGE.

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      return (false);
    }

    // when finish loading page
    public void onPageFinished(WebView view, String url) {
      swipe.setRefreshing(false);
    }
    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
      view.loadUrl("about:blank");
      //Toast.makeText(App.getContext(), "Error occured, please check newtwork connectivity", Toast.LENGTH_SHORT).show();
      super.onReceivedError(view, errorCode, description, failingUrl);
    }
  }

  @Override
  public void onBackPressed() {
    if (webView.isFocused() && webView.canGoBack()) {
      webView.goBack();
      swipe.setRefreshing(true);
    } else {
      finish();
      super.onBackPressed();
    }
  }
  public static void deleteCookies() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      CookieManager.getInstance().removeAllCookies(null);
    }
    CookieManager.getInstance().removeAllCookie();
  }
}
