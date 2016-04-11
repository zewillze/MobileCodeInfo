package com.will.zengzewill.mobilecodeinfo.api;

import android.os.AsyncTask;
import android.util.Log;

import com.will.zengzewill.mobilecodeinfo.model.InfoModel;
import com.will.zengzewill.mobilecodeinfo.protocol.MobileCodeInfoDelegate;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by ze on 16/4/11.
 */
public class FetchInfoApi extends AsyncTask<String, Void, String>{
//GET http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=1341234123
// HEADER apikey cbf24b7d11c6386d4d15ca12f5c67638

    public MobileCodeInfoDelegate delegate = null;

    private final String baseURL = "http://apis.baidu.com/apistore/mobilenumber/mobilenumber?";
    private final String apiKey = "cbf24b7d11c6386d4d15ca12f5c67638";


    @Override
    protected String doInBackground(String... params) {

        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(baseURL+"phone="+params[0].toString());
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("apikey", apiKey);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            Log.v("ResponseCode", String.valueOf(responseCode));

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null){
                result.append(line);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


        try {
            Log.v("IOOIOIOI", new JSONObject(s).toString());

            JSONObject object = new JSONObject(s);
            int errnum = object.getInt("errNum");
            if (errnum < 0){
                delegate.didFailInfo(object.getString("retMsg"));
            }
            else {
                InfoModel model = new InfoModel(new JSONObject(s));
                delegate.didFinishFetchInfo(model);
            }



        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
