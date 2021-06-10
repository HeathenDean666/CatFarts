package com.heathendean.catfarts;

import android.app.Application;
import android.util.Log;

import com.yariksoffice.lingver.Lingver;
import com.yariksoffice.lingver.store.PreferenceLocaleStore;

import java.util.Locale;

public class CatFarts extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceLocaleStore store = new PreferenceLocaleStore(this, Locale.ENGLISH);
        Lingver lingver = Lingver.init(this, store);
    }

}
