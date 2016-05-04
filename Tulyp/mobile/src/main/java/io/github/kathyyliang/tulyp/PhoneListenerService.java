package io.github.kathyyliang.tulyp;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.List;

public class PhoneListenerService extends WearableListenerService {

    private final static String TREMOR_DATA_PATH = "/tremor_data_point";
    private final static String TREMOR_DATA = "tremor_data";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {}

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        Log.d("T", "dataChanged called");
        final List<DataEvent> events = FreezableUtils.freezeIterable(dataEvents);
        for (DataEvent event : events) {
            if (event.getType() == DataEvent.TYPE_CHANGED && event.getDataItem().getUri().getPath().equals(TREMOR_DATA_PATH)) {
                DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                DataMap repData = dataMapItem.getDataMap().getDataMap(TREMOR_DATA);
                System.out.println(repData.toString());
            }
        }
    }

}
