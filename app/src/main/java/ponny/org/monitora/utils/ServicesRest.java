package ponny.org.monitora.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServicesRest {
    private static ServicesRest instance = null;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    protected  ServicesRest(){

    }
    public static ServicesRest getInstance() {

        if (instance == null) {
            instance = new ServicesRest();
        }
        return instance;
    }
    public Response post(String body_send,String url) throws IOException, SocketTimeoutException {

        RequestBody body = RequestBody.create(JSON, body_send);
        Request request = new Request.Builder()
                .post(body)
                .url(url).
                        build();
        Response response=getResponse(request);
        return response;

    }
    public Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
      Response response=getResponse(request);
      return response;
    }
    private Response getResponse(Request request) throws IOException,SocketTimeoutException {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        return response;
    }
}
