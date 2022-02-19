package com.example.coe_complaints;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.sync.SyncConfiguration;

public class COEApplication extends Application {
    public static App app;
    private String email="prasannarahul22@gmail.com";
    private String password="rahul2002";
    final int METADATA_DB_SCHEMA_VERSION = 4;
    public static AtomicReference<User> user;
    private User appuser;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        String appID = "coe-0-enllr";

        user = new AtomicReference<User>();

        app = new App(new AppConfiguration.Builder(appID).build());

        Credentials emailPasswordCredentials = Credentials.emailPassword(email, password);

        app.loginAsync(emailPasswordCredentials, it -> {
            if (it.isSuccess()) {
                Log.v("AUTH", "Successfully authenticated using an email and password.");

                user.set(app.currentUser());
            } else {
                Log.e("AUTH", it.getError().toString());
            }
        });

        String partitionValue = "My Project";
        appuser = app.currentUser();

        SyncConfiguration config = new SyncConfiguration.Builder(
                appuser,
                partitionValue).allowWritesOnUiThread(true).allowQueriesOnUiThread(true)
                .build();

        Realm.setDefaultConfiguration(config);

        try {
            Realm realm = Realm.getDefaultInstance();
            realm.close();
        }
        catch (RealmMigrationNeededException e) {
                Realm.deleteRealm(config);
            }
    }
}
