package amsi.dei.estg.ipleiria.osteoclinic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CpuUsageInfo;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;

public class ConsultasJsonParser {
    public static ArrayList<Consulta> parserJsonConsultas(JSONArray resposta){
        ArrayList<Consulta> listaconsultas = new ArrayList<>();

        try {
            for(int i = 0; i < resposta.length(); i++){
                JSONObject consultajson = (JSONObject) resposta.get(i);
                long id = consultajson.getLong("id");
                String data = consultajson.getString("data_consulta");
                String descricao = consultajson.getString("descricao");
                long terapeuta = consultajson.getLong("terapeuta_id");
                double peso = consultajson.getDouble("peso");
                String tratamento = consultajson.getString("tratamento");
                String obs = consultajson.getString("obs");
                String rec = consultajson.getString("recomendacao");

                SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
                Date data1 = formatter.parse(data);

                Consulta consulta = new Consulta(id, data1, descricao, terapeuta, peso, tratamento, obs, rec);

                listaconsultas.add(consulta);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return listaconsultas;
    }

    public static Consulta parserJsonConsulta(String resposta){
        Consulta consulta = null;

        try {
            JSONObject consultajson = new JSONObject(resposta);
            long id = consultajson.getLong("id");
            String data = consultajson.getString("data_consulta");
            String descricao = consultajson.getString("descricao");
            long terapeuta = consultajson.getLong("terapeuta_id");
            double peso = consultajson.getDouble("peso");
            String tratamento = consultajson.getString("tratamento");
            String obs = consultajson.getString("obs");
            String rec = consultajson.getString("recomendacao");

            SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
            Date data1 = formatter.parse(data);

            consulta = new Consulta(id, data1, descricao, terapeuta, peso, tratamento, obs, rec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consulta;
    }


    public static boolean isConnected(Context contexto){
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
