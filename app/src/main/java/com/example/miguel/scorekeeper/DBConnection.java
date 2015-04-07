package com.example.miguel.scorekeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by miguel on 04/02/15.
 */
public class DBConnection extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ScooreKeeper.db";

    private static final String GAME_TABLE_NAME = "juegos";
    private static final String GAME_COLUMN_ID = "id";
    private static final String GAME_COLUMN_NAME = "nombre";
    private static final String GAME_COLUMN_DESCRIPTION = "descipcion";

    private static final String TEAM_TABLE_NAME = "equipos";
    private static final String TEAM_COLUMN_ID = "id";
    private static final String TEAM_COLUMN_NAME = "nombre";
    private static final String TEAM_COLUMN_DESCRIPTION = "descipcion";

    private static final String PARTIDA_TABLE_NAME = "partidas";
    private static final String PARTIDA_COLUMN_ID = "id";
    private static final String PARTIDA_COLUMN_JUEGO = "juego";
    private static final String PARTIDA_COLUMN_EQUIPO_A = "equipoA";
    private static final String PARTIDA_COLUMN_EQUIPO_B = "equipoB";
    private static final String PARTIDA_COLUMN_PUNTAJE_A = "puntosEquipoA";
    private static final String PARTIDA_COLUMN_PUNTAJE_B = "puntosEquipoB";

    private static final String DETALLE_TABLE_NAME = "detalle";
    private static final String DETALLE_COLUMN_ID = "id";
    private static final String DETALLE_COLUMN_EQUIPO_A = "equipoA";
    private static final String DETALLE_COLUMN_EQUIPO_B = "equipoB";
    private static final String DETALLE_COLUMN_PUNTOS_A = "puntosA";
    private static final String DETALLE_COLUMN_PUNTOS_B = "puntosB";

    public static final String[] ALL_COLUMNS_GAME = new String[] {
            GAME_COLUMN_ID,
            GAME_COLUMN_NAME,
            GAME_COLUMN_DESCRIPTION
    };

    public static final String[] ALL_COLUMNS_TEAM = new String[] {
            TEAM_COLUMN_ID,
            TEAM_COLUMN_NAME,
            TEAM_COLUMN_DESCRIPTION
    };

    public static final String[] ALL_COLUMNS_PARTIDA = new String[] {
            PARTIDA_COLUMN_ID,
            PARTIDA_COLUMN_JUEGO,
            PARTIDA_COLUMN_EQUIPO_A,
            PARTIDA_COLUMN_EQUIPO_B,
            PARTIDA_COLUMN_PUNTAJE_A,
            PARTIDA_COLUMN_PUNTAJE_B
    };

    public static final String[] ALL_COLUMNS_DETALLE = new String[] {
            DETALLE_COLUMN_ID,
            DETALLE_COLUMN_EQUIPO_A,
            DETALLE_COLUMN_EQUIPO_B,
            DETALLE_COLUMN_PUNTOS_A,
            DETALLE_COLUMN_PUNTOS_B
    };

    public static final String CREATE_GAME_TABLE =
            "CREATE TABLE " +
                    GAME_TABLE_NAME +
                    "( " +
                    GAME_COLUMN_ID + " integer primary key, " +
                    GAME_COLUMN_NAME + " text, " +
                    GAME_COLUMN_DESCRIPTION + " text " +
                    ")";

    public static final String CREATE_TEAM_TABLE =
            "CREATE TABLE " +
                    TEAM_TABLE_NAME +
                    "( " +
                    TEAM_COLUMN_ID + " integer primary key, " +
                    TEAM_COLUMN_NAME + " text, " +
                    TEAM_COLUMN_DESCRIPTION + " text " +
                    ")";

    public static final String CREATE_PARTIDA_TABLE =
            "CREATE TABLE " +
                    PARTIDA_TABLE_NAME +
                    "( " +
                    PARTIDA_COLUMN_ID + " integer primary key, " +
                    PARTIDA_COLUMN_JUEGO + " text, " +
                    PARTIDA_COLUMN_EQUIPO_A + " text, " +
                    PARTIDA_COLUMN_EQUIPO_B + " text, " +
                    PARTIDA_COLUMN_PUNTAJE_A + " integer, " +
                    PARTIDA_COLUMN_PUNTAJE_B + " integer " +
                    ")";

    public static final String CREATE_DETALLE_TABLE =
            "CREATE TABLE " +
                    DETALLE_TABLE_NAME +
                    "( " +
                    DETALLE_COLUMN_ID + " integer, " +
                    DETALLE_COLUMN_EQUIPO_A + " text, " +
                    DETALLE_COLUMN_EQUIPO_B + " text, " +
                    DETALLE_COLUMN_PUNTOS_A + " text, " +
                    DETALLE_COLUMN_PUNTOS_B + " text " +
                    ")";

    public DBConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GAME_TABLE);
        db.execSQL(CREATE_TEAM_TABLE);
        db.execSQL(CREATE_PARTIDA_TABLE);
        db.execSQL(CREATE_DETALLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GAME_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TEAM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DETALLE_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=OFF");
        }
    }

    public void insertGame(String nombre, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(GAME_COLUMN_NAME, nombre);
        values.put(GAME_COLUMN_DESCRIPTION, descripcion);

        db.insert(GAME_TABLE_NAME, null, values);
    }

    public void insertTeam(String nombre, String descripcion){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TEAM_COLUMN_NAME, nombre);
        values.put(TEAM_COLUMN_DESCRIPTION, descripcion);

        db.insert(TEAM_TABLE_NAME, null, values);
    }

    public void insertPartida(Integer id, String juego, String equipoA, String equipoB, Integer puntosA, Integer puntosB){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PARTIDA_COLUMN_ID, id);
        values.put(PARTIDA_COLUMN_JUEGO, juego);
        values.put(PARTIDA_COLUMN_EQUIPO_A, equipoA);
        values.put(PARTIDA_COLUMN_EQUIPO_B, equipoB);
        values.put(PARTIDA_COLUMN_PUNTAJE_A, puntosA);
        values.put(PARTIDA_COLUMN_PUNTAJE_B, puntosB);

        db.insert(PARTIDA_TABLE_NAME, null, values);
    }

    public void insertDetalle(Integer id, String equipoA, String equipoB, String puntosA, String puntosB){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DETALLE_COLUMN_ID, id);
        values.put(DETALLE_COLUMN_EQUIPO_A, equipoA);
        values.put(DETALLE_COLUMN_EQUIPO_B, equipoB);
        values.put(DETALLE_COLUMN_PUNTOS_A, puntosA);
        values.put(DETALLE_COLUMN_PUNTOS_B, puntosB);

        db.insert(DETALLE_TABLE_NAME, null, values);
    }

    public List<Juego> getAllGames(){
        List<Juego> juegos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GAME_TABLE_NAME, ALL_COLUMNS_GAME, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Juego juego = cursorToGames(cursor);
            juegos.add(juego);
            cursor.moveToNext();
        }
        cursor.close();

        return juegos;
    }

    private Juego cursorToGames(Cursor cursor){
        Juego juego = new Juego();

        juego.setNombre(cursor.getString(1));
        juego.setDescripcion(cursor.getString(2));

        return juego;
    }


    public List<Equipo> getAllTeams(){
        List<Equipo> equipos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TEAM_TABLE_NAME, ALL_COLUMNS_TEAM, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Equipo equipo = cursorToEquipos(cursor);
            equipos.add(equipo);
            cursor.moveToNext();
        }
        cursor.close();

        return equipos;
    }

    private Equipo cursorToEquipos(Cursor cursor){
        Equipo equipo = new Equipo();

        equipo.setNombre(cursor.getString(1));
        equipo.setDescripcion(cursor.getString(2));

        return equipo;
    }

    public List<Partida> getAllPartidas(String juego){
        List<Partida> partidas = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                PARTIDA_TABLE_NAME,
                ALL_COLUMNS_PARTIDA,
                "juego = ?",
                new String[] {juego},
                null,
                null,
                null
        );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Partida partida = cursorToPartidas(cursor);
            partidas.add(partida);
            cursor.moveToNext();
        }
        cursor.close();

        return partidas;
    }

    private Partida cursorToPartidas(Cursor cursor){
        Partida partida = new Partida();

        partida.setID(cursor.getInt(0));
        partida.setJuego(cursor.getString(1));
        partida.setEquipo_A(cursor.getString(2));
        partida.setEquipo_B(cursor.getString(3));
        partida.setPuntaje_equipo_A(cursor.getInt(4));
        partida.setPuntaje_equipo_B(cursor.getInt(5));

        return partida;
    }

    public ArrayList<String> getDetalleDePartidaByID(String id){
        ArrayList<String> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                DETALLE_TABLE_NAME,
                ALL_COLUMNS_DETALLE,
                "id = ?",
                new String[] {id},
                null,
                null,
                null
        );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            String partida = cursorToDetalle(cursor);
            lista.add(partida);
            cursor.moveToNext();
        }
        cursor.close();

        return lista;
    }

    private String cursorToDetalle(Cursor cursor){
        String jugada = "";

        jugada += cursor.getString(1);
        jugada += ": ";
        jugada += cursor.getString(3);
        jugada += "\t\t\t\t";
        jugada += cursor.getString(2);
        jugada += ": ";
        jugada += cursor.getString(4);

        return jugada;
    }

    public int getCantidadDePartidas() {
        String countQuery = "SELECT  * FROM " + PARTIDA_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int cnt = cursor.getCount();
        cursor.close();

        return cnt;
    }

    public Boolean gameExists(String nombre){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nombre FROM " +
                GAME_TABLE_NAME + " WHERE " +
                GAME_COLUMN_NAME + " = ?",
            new String[] {nombre}
        );

        if (cursor.getCount() <= 0){
            //este juego no esta registrado
            return false;
        }
        else{
            //este juego si esta registrado
            return true;
        }

    }

    public Boolean teamExists(String equipo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nombre FROM " +
                        TEAM_TABLE_NAME + " WHERE " +
                        TEAM_COLUMN_NAME + " = ?",
                new String[] {equipo}
        );

        if (cursor.getCount() <= 0){
            //este juego no esta registrado
            return false;
        }
        else{
            //este juego si esta registrado
            return true;
        }
    }

    public Boolean partidaExists(String juego, String equipoA, String equipoB){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +
                        PARTIDA_TABLE_NAME + " WHERE " +
                        PARTIDA_COLUMN_JUEGO + " = ? AND " +
                        PARTIDA_COLUMN_EQUIPO_A + " = ? AND " +
                        PARTIDA_COLUMN_EQUIPO_B + " = ?",
                new String[] {juego, equipoA, equipoB}
        );

        if (cursor.getCount() <= 0){
            //esta partida no esta registrado
            return false;
        }
        else{
            //esta partida si esta registrado
            return true;
        }

    }
}