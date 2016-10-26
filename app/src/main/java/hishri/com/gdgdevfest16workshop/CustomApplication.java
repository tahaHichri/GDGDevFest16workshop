package hishri.com.gdgdevfest16workshop;

import android.app.Application;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;

/**
 * Created by taha on 25/10/16.
 * awesome code \m/
 */

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
       Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder( getApplicationContext() );

        // enable DevTools
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(getApplicationContext()));

        // DumpApp
        initializerBuilder.enableDumpapp( Stetho.defaultDumperPluginsProvider( getApplicationContext() ) );

        Stetho.Initializer initializer = initializerBuilder.build();

        Stetho.initialize(initializer);


    }
}
