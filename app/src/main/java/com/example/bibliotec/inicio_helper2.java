package com.example.bibliotec;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;

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

public class inicio_helper2 extends AsyncTask<String, Void, String> {

    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    AlertDialog dialog;
    Context context;

    public inicio_helper2(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Ver libros");
    }

    @Override
    protected void onPostExecute(String s) {

        dialog.setMessage(s);
        dialog.show();

        if (s.contains("Ver libros")) {

            EditText usr = (EditText) ((MainActivity) context).findViewById(R.id.username);
            String user = usr.getText().toString();

            Intent i = new Intent();
            i.setClass(context.getApplicationContext(), Libros.class);
            i.putExtra(EXTRA_TEXT, user);
            context.startActivity(i);
        }
    }

    @Override
    protected String doInBackground(String... voids) {

        String result = "";
        String user = voids[0];

        String host = "http://192.168.1.3:80/bibliotec/incio.php";

        try {

            URL url = new URL(host);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
            String line = "";

            while ((line = reader.readLine()) != null) {

                result += line;
            }

            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e) {

            result = e.getMessage();

        } catch (IOException e) {

            result = e.getMessage();

        }

        return result;
    }
}