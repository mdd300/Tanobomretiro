package com.tanobomretiro.gabriel.tanobomretiro;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by victoroshiro on 04/05/17.
 */

public abstract class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }
    static JSONObject jobj = null;
    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url   = "http://tanobomretiro.com/login/categorias.php";
        // String reg_url = "http://192.168.56.1/webapp/categoria.php";
        String method = params[0];
        String Nome = "nome";
        if (method.equals("categoria")) {
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {

                    response+= line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.e("teste",response.toString());


                try {

                    JSONObject obj = new JSONObject (response.toString());
                    JSONArray JAStuff = obj.getJSONArray("stuff");

                    for (int i = 0; i < JAStuff.length(); i++) {
                        JSONObject JOStuff = JAStuff.getJSONObject(i);
                        list.add(JOStuff.getString("nome"));



                    }
                    }
                 catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("teste","Erro");
                }


                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registration Success..."))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }

    }

    // setting the ArrayList Value
    public void setArrayList ( ArrayList list )
    {
        this.list = list;
    }

    // getting the ArrayList value
    public ArrayList getArrayList()
    {

        return list;
    }

    protected abstract void onPostExecute();
}
