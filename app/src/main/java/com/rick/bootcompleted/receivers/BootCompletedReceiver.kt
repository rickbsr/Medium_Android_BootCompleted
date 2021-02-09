package com.rick.bootcompleted.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.rick.bootcompleted.MainActivity

class BootCompletedReceiver : BroadcastReceiver() {

    companion object {
        private val TAG = BootCompletedReceiver::class.java.simpleName
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        if (Intent.ACTION_BOOT_COMPLETED == action) {
            Log.d(TAG, "onReceive: $action")
        }

        // 當 API >= 26，丟出 IllegalStateException
        val serviceIntent = Intent(context, MyService::class.java)
        context.startService(serviceIntent)

        // 當 API >= 29, 將會失效
        val activityIntent = Intent(context, MainActivity::class.java)
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(activityIntent)
    }
}