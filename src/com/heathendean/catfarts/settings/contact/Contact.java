package com.heathendean.catfarts.settings.contact;


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
import com.heathendean.catfarts.settings.cheats.Cheats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Contact extends AppCompatActivity {

    public static WebView webView;

    public static String fileName = "contact.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

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
            return inflater.inflate(R.layout.contact_fragment, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            webView = (WebView) getActivity().findViewById(R.id.contactWebView);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                    webView.loadUrl("file:///android_asset/general_error.html");
                }
            });
            // TODO: improve performance here
            webView.loadUrl("file:///android_asset/" + fileName);
        }
    }

    public void back_to_settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}