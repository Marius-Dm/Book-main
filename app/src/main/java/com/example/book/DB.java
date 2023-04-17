package com.example.book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    Context context;
    public DB(@Nullable Context context) {
        super(context, "book.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE BOOKS (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLU TEXT, AUTOR TEXT, COMENTARIU TEXT,RATING TEXT, GEN TEXT)";
        db.execSQL(createTable);
    }

    public void addBook(Book book){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("TITLU",book.getTitlu());
        cv.put("AUTOR",book.getAutor());
        cv.put("COMENTARIU",book.getComentariu());
        cv.put("RATING",book.getRating());
        cv.put("GEN",book.getGen());

        db.insert("BOOKS",null,cv);
    }

    public void updateBook(int id, String newTitlu, String newAutor, String newComentariu, String newRadio, String newGen){
        String query= "UPDATE BOOKS SET TITLU='"+newTitlu+"',AUTOR='"+newAutor+"',COMENTARIU='"+newComentariu+"',RATING='"+newRadio+"',GEN='"+newGen+"' WHERE ID="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public void removeBook(int id){
        String query="DELETE FROM BOOKS WHERE ID="+id;
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(query);
    }

    public List<Book> getBooks(){
        List<Book> resultList=new ArrayList<>();

        String queryList="SELECT * FROM BOOKS" ;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(queryList,null);

        if(cursor.moveToFirst()){
            do {
                int id=cursor.getInt(0);
                String titlu=cursor.getString(1);
                String autor=cursor.getString(2);
                String comentariu=cursor.getString(3);
                String rating=cursor.getString(4);
                String gen=cursor.getString(5);

                Book book=new Book(id,titlu,autor,comentariu,rating,gen);
                resultList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resultList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
