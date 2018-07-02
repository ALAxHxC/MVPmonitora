package ponny.org.monitora.models.firebase;

import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private LocalBroadcastManager broadcaster;

    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.println(Log.ASSERT, "FCM", remoteMessage.getData().toString());
    }
}
