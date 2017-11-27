package com.coohomeless.adapters;

import android.app.Application;

import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.remoting.adapters.RestContractItem;

public class ApiAdapter extends Application {
    RestAdapter adapter;

    public ApiAdapter() {
    }

    public RestAdapter getApiAdapter() {
        if (adapter == null) {
            // Instantiate the shared RestAdapter. In most circumstances,
            // you'll do this only once; putting that reference in a singleton
            // is recommended for the sake of simplicity.
            // However, some applications will need to talk to more than one
            // server - create as many Adapters as you need.
            adapter = new RestAdapter(getApplicationContext(), "http://4985bc07.ngrok.io/api");

            // This boilerplate is required for Lesson Three.
//            adapter.getContract().addItem(new RestContractItem("locations/nearby", "GET"), "location.nearby");
        }

        return adapter;
    }
}
