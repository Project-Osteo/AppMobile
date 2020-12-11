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
    private static final String DESCRICAO_CONSULTA = "descricao_treino";
    private static final String PACIENTE_CONSULTA = "id_paciente";
    private static final String TERAPEUTA_CONSULTA = "terapeuta_id";
    private static final String PESO = "peso";
    private static final String TRATAMENTO_CONSULTA = "tratamento";
    private static final String OBS_CONSULTA = "observacoes";
    private static final String RECOMENDACAO_CONSULTA = "recomendacao";

    //dados da tabela treinos
    private static final String TABELA_TREINOS = "Treinos";
    private static final String ID_TREINO = "id";
    private static final String PACIENTE_TREINO = "id_paciente";
    private static final String DATA_TREINO = "data";
    private static final String DESCRICAO_TREINO = "descricao_treino";
    private static final String TIPO_TREINO = "tipo_treino";
    private static final String OBS_TREINO = "observacos_treino";

    //dados da tabela de feedback
    private static final String TABELA_FEEDBACK = "feedback";
    private static final String ID_FEEDBACK = "id";
    private static final String USER_ID = "id_utilizador";
    private static final String CONSULTA_ID_FEEDBACK = "id_consulta";
    private static final String TREINO_ID_FEEDBACK = "id_treino";
    private static final String MENSAGEM = "mensagem";
    private static final String DATAHORA = "data_e_hora";

    private final SQLiteDatabase database;

    public ClinicBDHelper(Context contexto){
        super(contexto, NOME_BD, null, VERSAO_BD);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaConsultas = "CREATE TABLE " + TABELA_CONSULTA + "( " +
                ID_CONSULTA + " INTEGER PRIMARY KEY, " +
                DATA_CONSULTA + " DATE NOT NULL, " +
                DESCRICAO_CONSULTA + " TEXT NOT NULL, " +
                PACIENTE_CONSULTA + " INTEGER FOREIGN KEY, " +
                TERAPEUTA_CONSULTA + " INTEGER FOREIGN KEY, " +
                PESO + " TEXT NOT NULL, " +
                TRATAMENTO_CONSULTA + " TEXT NOT NULL, " +
                OBS_CONSULTA + " TEXT NOT NULL, " +
                RECOMENDACAO_CONSULTA + " TEXT NOT NULL" +
                ")";

        String sqlTabelaTreinos = "CREATE TABLE " + TABELA_TREINOS + "( " +
                ID_TREINO + " INTEGER PRIMARY KEY, " +
                PACIENTE_TREINO + " INTEGER FOREIGN KEY, " +
                DATA_TREINO + " DATE NOT NULL, " +
                DESCRICAO_TREINO + " TEXT NOT NULL, " +
                TIPO_TREINO + " TEXT NOT NULL, " +
                OBS_TREINO + " TEXT NOT NULL" +
                ")";

        String sqlTabelaFeedback = "CREATE TABLE " + TABELA_FEEDBACK + "( " +
                ID_FEEDBACK + " INTEGER PRIMARY KEY, " +
                USER_ID + " INTEGER FOREIGN KEY, " +
                CONSULTA_ID_FEEDBACK + " INTEGER FOREIGN KEY, " +
                TREINO_ID_FEEDBACK + " INTEGER FOREIGN KEY, " +
                MENSAGEM + " TEXT NOT NULL, " +
                DATAHORA + " DATETIME NOW()" +
                ")";

        db.execSQL(sqlTabelaConsultas);
        db.execSQL(sqlTabelaTreinos);
        db.execSQL(sqlTabelaFeedback);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CONSULTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_TREINOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_FEEDBACK);
        this.onCreate(db);
    }
}
