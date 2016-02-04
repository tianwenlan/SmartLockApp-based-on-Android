package com.example.simsim.local;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpConnection {

    // Send post request with an object to a URL, return an object from response.
    public static Object httpPost(String urlString, Object object) throws Exception{

        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setUseCaches(false);
        urlConnection.connect();

        ObjectOutputStream out = new ObjectOutputStream(urlConnection.getOutputStream());
        out.writeObject(object);
        out.flush();
        out.close();

        ObjectInputStream in = new ObjectInputStream(urlConnection.getInputStream());
        Object o = in.readObject();
        in.close();

        urlConnection.disconnect();

        return o;
    }

    public static Bitmap getHttpBitmap(String urlString) throws Exception{
        URL url = new URL(urlString);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(0);
        conn.setDoInput(true);
        conn.connect();
        InputStream is = conn.getInputStream();
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        is.close();

        return bitmap;
    }
}