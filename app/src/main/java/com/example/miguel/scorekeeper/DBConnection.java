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

    public DBConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GAME_TABLE);
        db.execSQL(CREATE_TEAM_TABLE);
        db.execSQL(CREATE_PARTIDA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GAME_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TEAM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PARTIDA_TABLE_NAME);
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

    public void insertPartida(String juego, String equipoA, String equipoB, Integer puntosA, Integer puntosB){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PARTIDA_COLUMN_JUEGO, juego);
        values.put(PARTIDA_COLUMN_EQUIPO_A, equipoA);
        values.put(PARTIDA_COLUMN_EQUIPO_B, equipoB);
        values.put(PARTIDA_COLUMN_PUNTAJE_A, puntosA);
        values.put(PARTIDA_COLUMN_PUNTAJE_B, puntosB);

        db.insert(PARTIDA_TABLE_NAME, null, values);
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

        juego.setNombre(cursor.getString(0));
        juego.setDescripcion(cursor.getString(1));

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

        equipo.setNombre(cursor.getString(0));
        equipo.setDescripcion(cursor.getString(1));

        return equipo;
    }

    public List<Partida> getAllPartidas(){
        List<Partida> partidas = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PARTIDA_TABLE_NAME, ALL_COLUMNS_PARTIDA, null, null, null, null, null);

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

        partida.setJuego(cursor.getString(0));
        partida.setEquipo_A(cursor.getString(1));
        partida.setEquipo_B(cursor.getString(2));
        partida.setPuntaje_equipo_A(cursor.getInt(3));
        partida.setPuntaje_equipo_B(cursor.getInt(4));

        return partida;
    }
}
