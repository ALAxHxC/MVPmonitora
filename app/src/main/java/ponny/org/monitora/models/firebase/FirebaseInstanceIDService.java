package ponny.org.monitora.models.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

public class FirebaseInstanceIDService extends  com.google.firebase.iid.FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.println(Log.ASSERT,"FB","INIT SERV ");
        registerToken(token);
    }
    private void registerToken(String token) {

        Log.println(Log.ASSERT,"FB","registrando token "+token);
    }
}
