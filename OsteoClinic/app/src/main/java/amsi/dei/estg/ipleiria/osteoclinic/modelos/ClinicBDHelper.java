package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClinicBDHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "OsteoClinciBD";
    private static final int VERSAO_BD = 1;
    //dados da tabela consultas
    private static final String TABELA_CONSULTA = "Consultas";
    private static final String ID_CONSULTA = "id";
    private static final String DATA_CONSULTA = "data";
    private static final String DESCRICAO_CONSULTA = "descricao";
    private static final String PACIENTE_CONSULTA = "id_paciente";
    private static final String PESO = "peso";
    private static final String TRATAMENTO_CONSULTA = "tratamento";
    private static final String OBS_CONSULTA = "observacoes";
    private static final String RECOMENDACAO_CONSULTA = "recomendacao";

    private final SQLiteDatabase database;

    public ClinicBDHelper(Context contexto){
        super(contexto, NOME_BD, null, VERSAO_BD);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
