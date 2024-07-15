package com.example.f_manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CycleEndReciever extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        long cycleId = intent.getLongExtra("cycleId", -1);

        if (cycleId != -1) {
            MyDAO dao = new MyDAO(context);
            dao.markCycleAsEnded(cycleId);
        }
    }
}
