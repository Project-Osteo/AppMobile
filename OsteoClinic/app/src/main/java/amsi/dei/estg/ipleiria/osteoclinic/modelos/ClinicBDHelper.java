package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ClinicBDHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "OsteoClinicBD";
    private static final int VERSAO_BD = 3;

    //dados da tabela consultas
    private static final String TABELA_CONSULTAS = "Consultas";
    private static final String ID_CONSULTA = "id_consulta";
    private static final String DATA_CONSULTA = "data_consulta";
    private static final String DESCRICAO_CONSULTA = "descricao_consulta";
    private static final String TRATAMENTO = "tratamento";
    private static final String OBS_CONSULTA = "obs_consulta";
    private static final String RECOMENDACAO_CONSULTA = "recomendacao";

    //dados da tabela treinos
    private static final String TABELA_TREINOS = "Treinos";
    private static final String ID_TREINO = "id_treino";
    private static final String DATA_TREINO = "data_treino";
    private static final String DESCRICAO_TREINO = "descricao_treino";
    private static final String TIPO_TREINO = "tipo_treino";
    private static final String OBS_TREINO = "obs_treino";

    //dados da tabela de feedbacks
    private static final String TABELA_FEEDBACK = "Feedbacks";
    private static final String ID_FEEDBACK = "id_feedback";
    private static final String USER_ID = "user_id";
    private static final String CONSULTA_ID = "consulta_id";
    private static final String MENSAGEM = "mensagem";
    private static final String DATAHORA = "datahora";

    private final SQLiteDatabase database;

    public ClinicBDHelper(Context contexto){
        super(contexto, NOME_BD, null, VERSAO_BD);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaConsultas = "CREATE TABLE " + TABELA_CONSULTAS + "(" +
                ID_CONSULTA + " INTEGER PRIMARY KEY, " +
                DATA_CONSULTA + " DATE NOT NULL, " +
                DESCRICAO_CONSULTA + " TEXT NOT NULL, " +
                TRATAMENTO + " TEXT NOT NULL, " +
                OBS_CONSULTA + " TEXT NOT NULL, " +
                RECOMENDACAO_CONSULTA + " TEXT NOT NULL" +
                ")";
        db.execSQL(sqlTabelaConsultas);

        String sqlTabelaTreinos = "CREATE TABLE " + TABELA_TREINOS + "( " +
                ID_TREINO + " INTEGER PRIMARY KEY, " +
                DATA_TREINO + " DATE NOT NULL, " +
                DESCRICAO_TREINO + " TEXT NOT NULL, " +
                TIPO_TREINO + " TEXT NOT NULL, " +
                OBS_TREINO + " TEXT NOT NULL" +
                ")";
        db.execSQL(sqlTabelaTreinos);

        String sqlTabelaFeedback = "CREATE TABLE " + TABELA_FEEDBACK + "( " +
                ID_FEEDBACK + " INTEGER PRIMARY KEY, " +
                USER_ID + " INTEGER, " +
                CONSULTA_ID + " INTEGER, " +
                MENSAGEM + " TEXT NOT NULL, " +
                DATAHORA + " DATETIME " +
                ")";
        db.execSQL(sqlTabelaFeedback);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CONSULTAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_TREINOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_FEEDBACK);
        this.onCreate(db);
    }

    // ----------- métodos CRUD consultas ---------------------------------
    public ArrayList<Consulta> getAllConsultasBD() throws ParseException {
        ArrayList<Consulta> lista = new ArrayList<>();

        SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");

        Cursor cursor = this.database.query(
                TABELA_CONSULTAS,
                new String[] {ID_CONSULTA, DATA_CONSULTA, DESCRICAO_CONSULTA,
                    TRATAMENTO, OBS_CONSULTA, RECOMENDACAO_CONSULTA},
                null, null,null, null, DATA_CONSULTA);
        if(cursor.moveToFirst()){
            do {
                Consulta consulta = new Consulta(cursor.getLong(0), formatter.parse(cursor.getString(1)),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5));
                lista.add(consulta);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public Consulta adicionarConsultaBD(Consulta c)  {
        ContentValues valores = new ContentValues();
        SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
        valores.put(ID_CONSULTA, c.getId());
        valores.put(DATA_CONSULTA, formatter.format(c.getDataConsulta()));
        valores.put(DESCRICAO_CONSULTA, c.getDescricao_consulta());
        valores.put(TRATAMENTO, c.getTratamento());
        valores.put(OBS_CONSULTA, c.getObs_consulta());
        valores.put(RECOMENDACAO_CONSULTA, c.getRecomendacao());

        this.database.insert(TABELA_CONSULTAS, null, valores);
        return null;
    }

    public void removeAllConsultasBD(){
        this.database.delete(TABELA_CONSULTAS, null, null);
    }

    // --------------  métodos CRUD treinos ------------------------
    public ArrayList<Treino> getAllTreinosBD() throws ParseException {
        ArrayList<Treino> lista = new ArrayList<>();

        SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");

        Cursor cursor = this.database.query(
                TABELA_TREINOS,
                new String[] {ID_TREINO, DATA_TREINO, DESCRICAO_TREINO, TIPO_TREINO, OBS_TREINO},
                null, null,null, null, DATA_TREINO);
        if(cursor.moveToFirst()){
            do {
                Treino treino = new Treino(cursor.getLong(0), formatter.parse(cursor.getString(1)),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4));
                lista.add(treino);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public Treino adicionarTreinoBD(Treino t) {
        ContentValues valores = new ContentValues();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        valores.put(ID_TREINO, t.getId());
        valores.put(DATA_TREINO, formatter.format(t.getDataTreino()));
        valores.put(DESCRICAO_TREINO, t.getDescricao_treino());
        valores.put(TIPO_TREINO, t.getTipoTreino());
        valores.put(OBS_TREINO, t.getObs_treino());

        long id = this.database.insert(TABELA_TREINOS, null, valores);
        if(id > -1){
            t.setId(id);
            return t;
        }
        return null;
    }

    public void removeAllTreinosBD(){
        this.database.delete(TABELA_TREINOS, null, null);
    }


    // ------------ métodos CRUD feedback ------------------------

    public ArrayList<Feedback> getAllFeedbackBD(long id_consulta) throws ParseException {
        ArrayList<Feedback> lista = new ArrayList<>();

        SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
        String[] args = { id_consulta+"" };
        Cursor cursor = this.database.query(
                TABELA_FEEDBACK,
                new String [] {ID_FEEDBACK, CONSULTA_ID, MENSAGEM, DATAHORA},
                CONSULTA_ID + "= ?", args, null, null, DATAHORA);
        if(cursor.moveToFirst()){
            do{
                Feedback feedback = new Feedback(cursor.getLong(0), cursor.getLong(1),
                        cursor.getString(2),
                        formatter.parse(cursor.getString(3)));
                lista.add(feedback);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public void removeAllFeedbackBD() {
        this.database.delete(TABELA_FEEDBACK, null, null);
    }

    public Feedback adcionarFeedbackBD(Feedback f){
        ContentValues valores = new ContentValues();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
        valores.put(ID_FEEDBACK, f.getId());
        valores.put(MENSAGEM, f.getMensagem());
        valores.put(CONSULTA_ID, f.getConsulta_id());
        valores.put(DATAHORA, formatter.format(f.getDatahora()));

        this.database.insert(TABELA_FEEDBACK, null, valores);

        return null;
    }

    public boolean editarFeedbackBD(Feedback feedback){
        ContentValues valores = new ContentValues();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        valores.put(MENSAGEM, feedback.getMensagem());
        valores.put(CONSULTA_ID, feedback.getConsulta_id());
        valores.put(DATAHORA, formatter.format(feedback.getDatahora()));

        int registosalterados = this.database.update(TABELA_FEEDBACK, valores,
                "id_feedback = ?", new String [] {""+ feedback.getId()});

        return registosalterados > 0;
    }

    public boolean removerFeedbackBD(long id){
        return this.database.delete(TABELA_FEEDBACK, ID_FEEDBACK + " = ?",
                new String [] {""+ id}) == 1;
    }

}
