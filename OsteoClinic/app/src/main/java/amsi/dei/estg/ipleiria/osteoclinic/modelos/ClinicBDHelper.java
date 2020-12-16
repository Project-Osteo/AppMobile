package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClinicBDHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "OsteoClinicBD";
    private static final int VERSAO_BD = 1;

    //dados da tabela consultas
    private static final String TABELA_CONSULTAS = "Consultas";
    private static final String ID_CONSULTA = "id";
    private static final String DATA_CONSULTA = "data";
    private static final String DESCRICAO_CONSULTA = "descricao";
    private static final String PACIENTE_CONSULTA = "paciente_id";
    private static final String TERAPEUTA_CONSULTA = "terapeuta_id";
    private static final String PESO = "peso";
    private static final String TRATAMENTO = "tratamento";
    private static final String OBS_CONSULTA = "observacoes";
    private static final String RECOMENDACAO_CONSULTA = "recomendacao";

    //dados da tabela treinos
    private static final String TABELA_TREINOS = "Treinos";
    private static final String ID_TREINO = "id";
    private static final String DATA_TREINO = "data";
    private static final String DESCRICAO_TREINO = "descricao_treino";
    private static final String TERAPEUTA_TREINO = "terapeuta_id";
    private static final String PACIENTE_TREINO = "paciente_id";
    private static final String TIPO_TREINO = "tipo_treino";
    private static final String OBS_TREINO = "observacos_treino";

    //dados da tabela de feedback
    private static final String TABELA_FEEDBACK = "Feedbacks";
    private static final String ID_FEEDBACK = "id";
    private static final String USER_ID = "id_utilizador";
    private static final String CONSULTA_ID_FEEDBACK = "id_consulta";
    private static final String TREINO_ID_FEEDBACK = "id_treino";
    private static final String MENSAGEM = "mensagem";
    private static final String DATAHORA = "data_e_hora";

    //dados da tabela de Terapeutas
    private static final String TABELA_TERAPEUTAS = "Terapeutas";
    private static final String ID_TERAPEUTA = "id";
    private static final String ESPECIALIDADE = "especialidade";
    private static final String CONTACTO_TERAPEUTA = "contacto";

    private final SQLiteDatabase database;

    public ClinicBDHelper(Context contexto){
        super(contexto, NOME_BD, null, VERSAO_BD);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaTerapeutas = "CREATE TABLE " + TABELA_TERAPEUTAS + "( " +
                ID_TERAPEUTA + " INTEGER PRIMARY KEY, " +
                ESPECIALIDADE + " TEXT, " +
                CONTACTO_TERAPEUTA + " TEXT)";
        db.execSQL(sqlTabelaTerapeutas);

        String sqlTabelaConsultas = "CREATE TABLE " + TABELA_CONSULTAS + "( " +
                ID_CONSULTA + " INTEGER PRIMARY KEY, " +
                DATA_CONSULTA + " DATE NOT NULL, " +
                DESCRICAO_CONSULTA + " TEXT NOT NULL, " +
                TERAPEUTA_CONSULTA + " INTEGER, " +
                PESO + " TEXT NOT NULL, " +
                TRATAMENTO + " TEXT NOT NULL, " +
                OBS_CONSULTA + " TEXT NOT NULL, " +
                RECOMENDACAO_CONSULTA + " TEXT NOT NULL, " +
                "CONSTRAINT fk_consultas_terapeuta FOREIGN KEY (" + TERAPEUTA_CONSULTA + ") REFERENCES " +
                TABELA_TERAPEUTAS + "(" + ID_TERAPEUTA + ")" +
                ")";
        db.execSQL(sqlTabelaConsultas);

        String sqlTabelaTreinos = "CREATE TABLE " + TABELA_TREINOS + "( " +
                ID_TREINO + " INTEGER PRIMARY KEY, " +
                DATA_TREINO + " DATE NOT NULL, " +
                DESCRICAO_TREINO + " TEXT NOT NULL, " +
                TERAPEUTA_TREINO + " INTEGER, " +
                TIPO_TREINO + " TEXT NOT NULL, " +
                OBS_TREINO + " TEXT NOT NULL, " +
                "CONSTRAINT fk_treinos_terapeuta FOREIGN KEY (" + TERAPEUTA_TREINO + ") REFERENCES " +
                TABELA_TERAPEUTAS + "(" + ID_TERAPEUTA + ")" +
                ")";
        db.execSQL(sqlTabelaTreinos);

        String sqlTabelaFeedback = "CREATE TABLE " + TABELA_FEEDBACK + "( " +
                ID_FEEDBACK + " INTEGER PRIMARY KEY, " +
                USER_ID + " INTEGER, " +
                CONSULTA_ID_FEEDBACK + " INTEGER, " +
                TREINO_ID_FEEDBACK + " INTEGER, " +
                MENSAGEM + " TEXT NOT NULL, " +
                DATAHORA + " DATETIME " +
                ")";
        db.execSQL(sqlTabelaFeedback);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_TERAPEUTAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CONSULTAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_TREINOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_FEEDBACK);
        this.onCreate(db);
    }


    public ArrayList<Consulta> getAllConsultasBD() throws ParseException {
        ArrayList<Consulta> lista = new ArrayList<>();

        SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");

        Cursor cursor = this.database.query(
                TABELA_CONSULTAS,
                new String[] {ID_CONSULTA, DATA_CONSULTA, DESCRICAO_CONSULTA, TERAPEUTA_CONSULTA,
                    PESO, TRATAMENTO, OBS_CONSULTA, RECOMENDACAO_CONSULTA},
                null, null,null, null, DATA_CONSULTA);
        if(cursor.moveToFirst()){
            do {
                Consulta consulta = new Consulta(cursor.getLong(0), formatter.parse(cursor.getString(1)),
                        cursor.getString(2), cursor.getLong(3), cursor.getDouble(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7));
            }while(cursor.moveToNext());
        }
        return lista;
    }


    public ArrayList<Treino> getAllTreinosBD() throws ParseException {
        ArrayList<Treino> lista = new ArrayList<>();

        SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");

        Cursor cursor = this.database.query(
                TABELA_TREINOS,
                new String[] {ID_TREINO, DATA_TREINO, DESCRICAO_TREINO, TERAPEUTA_TREINO, TIPO_TREINO, OBS_TREINO},
                null, null,null, null, DATA_TREINO);
        if(cursor.moveToFirst()){
            do {
                Treino treino = new Treino(cursor.getLong(0), formatter.parse(cursor.getString(1)),
                        cursor.getString(2), cursor.getLong(3), cursor.getString(4),
                        cursor.getString(6));
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public void removeAllConsultasBD(){
        this.database.delete(TABELA_CONSULTAS, null, null);
    }

    public void removeAllTreinosBD(){
        this.database.delete(TABELA_TREINOS, null, null);
    }


    public Consulta adicionarConsultaBD(Consulta c)  {
        ContentValues valores = new ContentValues();
        SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
        valores.put(DATA_CONSULTA, formatter.format(c.getDataConsulta()));
        valores.put(DESCRICAO_CONSULTA, c.getDescricao());
        valores.put(TERAPEUTA_CONSULTA, c.getTerapeuta_id());
        valores.put(PESO, c.getPeso());
        valores.put(TRATAMENTO, c.getTratamento());
        valores.put(OBS_CONSULTA, c.getObs());
        valores.put(RECOMENDACAO_CONSULTA, c.getRecomendacao());

        //instrução insert devolve o id do objeto adicionado
        long id = this.database.insert(TABELA_CONSULTAS, null, valores);
        if(id > -1){
            c.setId(id);
            return c;
        }
        return null;
    }

    public Treino adicionarTreinoBD(Treino t) {
        return null;
    }


}
