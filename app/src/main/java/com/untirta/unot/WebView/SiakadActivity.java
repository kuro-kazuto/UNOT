package com.untirta.unot.WebView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.untirta.unot.MainActivity2;
import com.untirta.unot.R;

import java.util.Objects;

public class SiakadActivity extends AppCompatActivity {
  private WebView webView;
  private TextView error;

  SwipeRefreshLayout swipe;

  FloatingActionButton printFab, qrFab;
  ExtendedFloatingActionButton toolFab;
  TextView printActionText, qrActionText;
  Boolean isAllFabsVisible;
  Button TA, siakad, spada, wisuda, eadmin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);
    webView = findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
    error = findViewById(R.id.error);
    gotoPage();

    //====floatingButton
    toolFab = findViewById(R.id.tool_fab);
    printFab = findViewById(R.id.print_fab);
    qrFab = findViewById(R.id.qr_fab);
    printActionText = findViewById(R.id.print_action_text);
    qrActionText = findViewById(R.id.qr_action_text);
    TA = findViewById(R.id.tugasakhir);
    siakad = findViewById(R.id.siakad);
    spada = findViewById(R.id.spada);
    eadmin = findViewById(R.id.eadmin);
    wisuda = findViewById(R.id.wisuda);

    // set all the FABs and all the action name texts as GONE
    printFab.setVisibility(View.GONE);
    qrFab.setVisibility(View.GONE);
    printActionText.setVisibility(View.GONE);
    qrActionText.setVisibility(View.GONE);

    isAllFabsVisible = false;
    toolFab.shrink();
    toolFab.setOnClickListener(view -> {
      if (!isAllFabsVisible) {
        printFab.show();
        qrFab.show();
        printActionText.setVisibility(View.VISIBLE);
        qrActionText.setVisibility(View.VISIBLE);
        toolFab.extend();
        isAllFabsVisible = true;
      } else {
        printFab.hide();
        qrFab.hide();
        printActionText.setVisibility(View.GONE);
        qrActionText.setVisibility(View.GONE);
        toolFab.shrink();
        isAllFabsVisible = false;
      }
    });

    qrFab.setOnClickListener(view -> {
      Intent intent = new Intent(SiakadActivity.this, QrTool.class);
      startActivity(intent);
    });

    printFab.setOnClickListener(view -> {
      doPrint();
    });
    //=============

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
        }
      }
    });
    btnForward.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (webView.isFocused() && webView.canGoForward()) {
          webView.goForward();
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
      String url = "siakad.untirta.ac.id"; //Change URL
      @Override
      public void onClick(View v) {
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        swipe.setRefreshing(true);
      }
    });
    btnClose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        webView.clearHistory();
        webView.clearCache(true);
        deleteCookies();
        Intent i = new Intent(SiakadActivity.this, MainActivity2.class);
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
      String url = "http://siakad.untirta.ac.id";
      //
      webView.setWebChromeClient(new WebChromeClient());
      webView.setWebViewClient(new WebViewClient());
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
      swipe.setRefreshing(true);
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
      Toast.makeText(SiakadActivity.this, "Error occurred, please check network connectivity", Toast.LENGTH_SHORT).show();
      super.onReceivedError(view, errorCode, description, failingUrl);
    }
  }

  @Override
  public void onBackPressed() {
    if (webView.isFocused() && webView.canGoBack()) {
      webView.goBack();
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

  //Method Print
  private void doPrint() {
    // Get the print manager.
    PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
    PrintDocumentAdapter adapter = new PrintDocumentAdapter() {
      private final PrintDocumentAdapter mWrappedInstance =
              webView.createPrintDocumentAdapter();

      @Override
      public void onStart() {
        mWrappedInstance.onStart();
      }

      @Override
      public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes,
                           CancellationSignal cancellationSignal, LayoutResultCallback callback,
                           Bundle extras) {
        mWrappedInstance.onLayout(oldAttributes, newAttributes, cancellationSignal,
                callback, extras);
      }

      @Override
      public void onWrite(PageRange[] pages, ParcelFileDescriptor destination,
                          CancellationSignal cancellationSignal, WriteResultCallback callback) {
        mWrappedInstance.onWrite(pages, destination, cancellationSignal, callback);
      }

      @Override
      public void onFinish() {
        mWrappedInstance.onFinish();
        webView.goBack();
      }
    };

    // Pass in the ViewView's document adapter.
    printManager.print("Print", adapter, null);
  }


}
