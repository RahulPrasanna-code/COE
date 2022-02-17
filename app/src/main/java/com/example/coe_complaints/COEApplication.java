package com.example.coe_complaints;

import static com.example.coe_complaints.MainActivity.app;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.mongodb.sync.SyncConfiguration;

public class COEApplication extends Application {
    final int METADATA_DB_SCHEMA_VERSION = 4;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().allowQueriesOnUiThread(true).allowWritesOnUiThread(true)
                .name("coe.realm")
                .schemaVersion(METADATA_DB_SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfig);

        try {
            Realm realm = Realm.getDefaultInstance();
            realm.close();
        }
        catch (RealmMigrationNeededException e) {
                Realm.deleteRealm(realmConfig);
            }
    }
}
