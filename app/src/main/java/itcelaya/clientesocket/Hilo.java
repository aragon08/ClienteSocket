package itcelaya.clientesocket;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by isctorres on 23/03/17.
 */

public class Hilo extends AsyncTask<String,String,Void> {

    TextView txtMsjs;
    Context con;
    ProgressDialog dialogo;

    private Socket socketCte;
    //private String host = "192.168.43.183";
    private String host = "10.254.32.244";
    private int puerto = 5000;

    public Hilo(Context cntx, TextView txtmsjs)
    {
        con = cntx;
        txtMsjs = txtmsjs;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialogo = new ProgressDialog(con);
        dialogo.setMessage("Enviando Mensaje al Servidor");
        dialogo.show();
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            socketCte = new Socket(host,puerto);
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(socketCte.getOutputStream()),true);
            publishProgress("Cliente:");
            publishProgress(params[0]);
            salida.println(params[0]);

            Thread.sleep(1000);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCte.getInputStream()));
            publishProgress("Servidor:");
            publishProgress(entrada.readLine());

            socketCte.close();

        }
        catch (IOException e) {}
        catch (InterruptedException e) {}


        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        txtMsjs.append(values[0]+"\n");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        dialogo.dismiss();
    }
}
