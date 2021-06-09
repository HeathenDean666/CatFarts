package com.heathendean.catfarts.settings.options;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.heathendean.catfarts.R;
import com.heathendean.catfarts.settings.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Options extends AppCompatActivity {

    private static View rootView;
    public static WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);

        rootView = this.getWindow().getDecorView().findViewById(android.R.id.content);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.options_fragment, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            webView = (WebView) getActivity().findViewById(R.id.testWebErrorView);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                    webView.loadUrl("file:///android_asset/general_error.html");
                }
            });
        }

    }

    public void test_error(View view){
        throw new RuntimeException("This is a test of the Custom Error Page for CatFarts.");
    }

    public void test_web_error(View view){
        webView.loadUrl("file:///android_asset/null.html");
    }

    public void back_to_settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}