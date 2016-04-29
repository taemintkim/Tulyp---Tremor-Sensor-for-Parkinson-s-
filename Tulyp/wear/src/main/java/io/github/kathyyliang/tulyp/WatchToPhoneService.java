package io.github.kathyyliang.tulyp;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;

public class WatchToPhoneService extends Service implements GoogleApiClient.ConnectionCallbacks {

    public final static String MESSAGE_KEY = "message";
    public final static String TREMOR_DATA_PATH = "/tremor_data_point";
    public final static String TREMOR_DATA = "tremor_data";

    private GoogleApiClient mWatchApiClient;
    private List<Node> nodes = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize the googleAPIClient for message passing
        mWatchApiClient = new GoogleApiClient.Builder( this )
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .build();
        //and actually connect it
        mWatchApiClient.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWatchApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("T", "in onConnected");
        Wearable.NodeApi.getConnectedNodes(mWatchApiClient)
                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
                    @Override
                    public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                        nodes = getConnectedNodesResult.getNodes();
                    }
                });
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendTremorData(intent.getBundleExtra(MESSAGE_KEY));
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void sendTremorData(Bundle tremorData) {
        PutDataMapRequest putDataMapReq = PutDataMapRequest.create(TREMOR_DATA_PATH);

        putDataMapReq.getDataMap().putDataMap(TREMOR_DATA, DataMap.fromBundle(tremorData));
        PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();

        PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi.putDataItem(mWatchApiClient, putDataReq);
        pendingResult.setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
            @Override
            public void onResult(DataApi.DataItemResult dataItemResult) {
                Status result = dataItemResult.getStatus();
                Log.d("sendTremorData result", result.toString());
            }
        });
    }
}
