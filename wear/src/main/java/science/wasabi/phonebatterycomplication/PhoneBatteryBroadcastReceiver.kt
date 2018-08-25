package science.wasabi.phonebatterycomplication

import android.util.Log

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.support.wearable.complications.ComplicationManager
import android.support.wearable.complications.ComplicationProviderService
import android.support.wearable.complications.ComplicationData
import android.support.wearable.complications.ComplicationText





class ComplicationTapBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("", "onReceive()")
    }
}


class PhoneBatteryComplicationProviderService : ComplicationProviderService() {
    override fun onComplicationUpdate(complicationId: Int, type: Int, manager: ComplicationManager?) {
        Log.d("", "onComplicationUpdate(): $complicationId")

        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            this.baseContext.registerReceiver(null, ifilter)
        }

        val percentage = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        Log.d("PhoneBatteryComplicationProviderService", "percentage is $percentage")

        val complicationData = ComplicationData.Builder(ComplicationData.TYPE_SHORT_TEXT)
                .setShortText(ComplicationText.plainText(percentage.toString()))
                .build()

        manager?.updateComplicationData(complicationId, complicationData)
    }
}

