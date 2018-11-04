package ponny.org.monitora.views.common;

import android.net.Uri;

import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;

public interface OnListFragmentInteractionListener {
    void onFragmentInteraction(Inbox uri);
    void onSendMessage(boolean result);
}
