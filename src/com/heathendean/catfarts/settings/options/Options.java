package com.heathendean.catfarts.settings.options;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.heathendean.catfarts.R;
import com.heathendean.catfarts.settings.Settings;
import com.yariksoffice.lingver.Lingver;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class Options extends AppCompatActivity {
    public static final String PREF_NAME = "CF_PREFS" ;
    public static SharedPreferences sharedpreferences;
    private static View rootView;
    public static WebView webView;
    public Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);
        // Get app-wide Shared Preferences.
        sharedpreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // Set Application language and locale.
        //Lingver.getInstance().setLocale(getApplicationContext(), sharedpreferences.getString("Language","en"), sharedpreferences.getString("Country", "EN"));
        Log.d("Options.java", "Language is: " + sharedpreferences.getString("Language","en"));
        Log.d("Options.java", "Country is: " + sharedpreferences.getString("Country", "EN"));
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
            webView = getActivity().findViewById(R.id.testWebErrorView);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                    webView.loadUrl("file:///android_asset/general_error.html");
                }
            });
            Spinner spinner = getActivity().findViewById(R.id.language_spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),
                    R.array.languages, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            boolean isEnglish = sharedpreferences.getString("Language","en").equals("en");
            String language = isEnglish ? "English" : "Russian";
            spinner.setSelection(getIndexForSpinner(spinner, language));
            spinner.setOnItemSelectedListener(new MySpinnerActivity());
        }

    }

    private static int getIndexForSpinner(Spinner spinner, String value){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)){
                return i;
            }
        }
        return 0;
    }

    public static class MySpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Log.d("Options.java", "Language Selected: " + parent.getItemAtPosition(pos).toString());
            SharedPreferences.Editor editor = sharedpreferences.edit();
            // TODO: Figure out some fucking way to add these values to the individual items in the spinner string array
            switch(parent.getItemAtPosition(pos).toString()) {
                case "English":
                    Lingver.getInstance().setLocale(rootView.getContext(), "en", "EN");
                    Log.d("Options.java", "Language,Country set to: en, EN");
                    // Set Shared Preference for these values.
                    //TODO: Set these to a String set at some point
                    editor.putString("Language", "en");
                    editor.putString("Country", "EN");
                    editor.apply();
                    break;
                case "Russian":
                    Lingver.getInstance().setLocale(rootView.getContext(), "ru", "RU");
                    Log.d("Options.java", "Language,Country set to: ru, RU");
                    // Set Shared Preference for these values.
                    //TODO: Set these to a String set at some point
                    editor.putString("Language", "ru");
                    editor.putString("Country", "RU");
                    editor.apply();
                    break;
                default:
                    throw new RuntimeException("Bad Language input somehow.");
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    public void switch_language(View view){
        Intent intent = new Intent(rootView.getContext(), Options.class);
        startActivity(intent);
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