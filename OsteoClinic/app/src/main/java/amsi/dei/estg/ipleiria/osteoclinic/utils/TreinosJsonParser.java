package amsi.dei.estg.ipleiria.osteoclinic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Treino;

public class TreinosJsonParser {
    public static ArrayList<Treino> parserJsonTreinos(JSONArray resposta){
        ArrayList<Treino> listatreinos = new ArrayList<>();

        try{
            for(int i = 0; i < resposta.length(); i++){
                JSONObject treinoJson = (JSONObject) resposta.get(i);
                long id = treinoJson.getLong("id_treino");
                String data = treinoJson.getString("data_treino");
                String descricao = treinoJson.getString("descricao_treino");
                String tipo = treinoJson.getString("tipo_treino");
                String obs = treinoJson.getString("obs_treino");

                SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
                Date data1 = formatter.parse(data.substring(0,10));

                Treino treino = new Treino(id, data1, descricao, tipo, obs);

                listatreinos.add(treino);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listatreinos;
    }

    public static Treino parserJsonTreino(String resposta){
        Treino treino = null;

        try {
            JSONObject treinoJson = new JSONObject(resposta);
            long id = treinoJson.getLong("id_treino");
            String data = treinoJson.getString("data_treino");
            String descricao = treinoJson.getString("descricao_treino");
            String tipo = treinoJson.getString("tipo_treino");
            String obs = treinoJson.getString("obs_treino");

            SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
            Date data1 = formatter.parse(data);

            treino = new Treino(id, data1, descricao, tipo, obs);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return treino;
    }

    public static boolean isConnected(Context contexto){
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }
}
