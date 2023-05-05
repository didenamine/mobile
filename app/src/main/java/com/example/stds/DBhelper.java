package com.example.stds;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DBNAME = "register.db";
    ArrayList<String>myarray = new ArrayList<>();

    public DBhelper(Context context) {
        super(context, "register.db" ,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create table user_table(username Text  ,email Text,password text)");
        MyDB.execSQL("Create table first_page_notes(Title Text ,Note Text,note_user Text)");
        MyDB.execSQL("Create table curent_user(username Text )");
        MyDB.execSQL("CREATE TABLE travel(name TEXT ,To_Do TEXT,state INTEGER)");
        MyDB.execSQL("CREATE TABLE read_note(title TEXT ,note TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists user_table");
        MyDB.execSQL("drop table if exists first_page_notes");
        MyDB.execSQL("drop table if exists curent_user");
        MyDB.execSQL("drop table if exists travel");
        MyDB.execSQL("drop table if exists travel");
        MyDB.execSQL("drop table if exists read_note");

    }
    public void deleteTodo(String todo)
    {
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("delete from travel where To_Do = ?",new String[]{todo});
        db.close();
    }
    public void insertData(String username , String email,String password )
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",email);
        contentValues.put("password",password);
         MyDB.insert("user_table",null,contentValues);
        MyDB.close();
    }

    public void insert_read_note(String title,String note)
    {    SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SQLiteDatabase Mydbr = this.getReadableDatabase();
        Cursor cursor = Mydbr.rawQuery("select  * from read_note",null);

        if (cursor.getCount()==0)
        {
            contentValues.put("title",title);
            contentValues.put("note",note);
            MyDB.insert("read_note",null,contentValues);

        }
        else{

            MyDB.execSQL("update read_note set title =? ,note = ?",new String[]{title,note});

        }
        MyDB.close();
        Mydbr.close();
    }
    public String get_read_title()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select title from read_note",null);
       String title = "";
        while(cursor.moveToNext())
        {
            title=cursor.getString(0).toString();
        }
        return  title;
    }
    public String get_read_note()
    {   SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select note from read_note",null);
        String note= "";
        while(cursor.moveToNext())
        {
            note=cursor.getString(0).toString();
        }
        return  note;

    }
////////////////////////////
    public boolean createTravelTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("CREATE TABLE travel(name TEXT ,To_Do TEXT,state INTEGER)");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
        Cursor readTravelUser(String user_name) {
        String ReadTableStatement = "SELECT To_Do FROM travel WHERE name  = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(ReadTableStatement, new String[]{user_name});
        }
        return cursor;
    }

    public boolean insertTravelUser(String Name, String To_Do, int state) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", Name);
        cv.put("To_Do", To_Do);
        cv.put("state", state);

        long insert = db.insert("travel", null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
public int getstate(String pos)
{
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("select state from travel where To_Do=?",new String[]{pos});
        int state = 0 ;
    while(cursor.moveToNext())
    {

        state= Integer.parseInt(cursor.getString(0).toString());

    }
    return state;

}
public void updatestate(String pos)
{
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("update travel set state = 1 where To_Do=?",new String[]{pos});

}



     public int  checkuser(String username ,String password )
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("Select * from user_table where username =? and password = ?",new String[]{username,password});
        if (cursor.getCount()==1)
        {
             return  1 ;
        }
        else {
            return 0 ;
        }

}
public  int checkusername(String username)
{
    SQLiteDatabase MyDB = this.getWritableDatabase();
    Cursor cursor =MyDB.rawQuery("Select * from user_table where username =? ",new String[]{username});
    if (cursor.getCount()==1)
    {
        return 0;
    }
    else {
        return 1;
    }
}
//first page sql /////////////////////////////////////////////
public void Add_note(String Title , String Note,String note_user)
{

    SQLiteDatabase MyDB = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("Title",Title);
    contentValues.put("Note",Note);
    contentValues.put("note_user",note_user);
    MyDB.insert("first_page_notes",null,contentValues);
    MyDB.close();
}
public int check_notes(String username)
{
    SQLiteDatabase MyDB = this.getWritableDatabase();
    Cursor cursor =MyDB.rawQuery("Select * from first_page_notes where note_user=? ",new String[]{username});
    if (cursor.getCount()>=1)
    {
        return 1;
    }
    else {
        return 0;
    }
}
/////////////////////////////////////////////////////////////

///////////////////Diary note data
    /////////////////////fitst notes page
    public ArrayList<first_page_notes_items> getNotes(String note_user)
    {
        ArrayList<first_page_notes_items> items = new ArrayList<first_page_notes_items>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor=MyDB.rawQuery("select * from first_page_notes where note_user=?  ",new String[]{note_user});
        while(cursor.moveToNext())
{
        items.add(new first_page_notes_items(cursor.getString(0).toString(),cursor.getString(1).toString()));

}
return items;

    }
    public void deleteNote(String note)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from first_page_notes where Note=?",new String[]{note});
    }
    ////
    public void setCurentUser(String username )
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SQLiteDatabase Mydbr = this.getReadableDatabase();
        Cursor cursor = Mydbr.rawQuery("select  * from curent_user",null);
        if (cursor.getCount()==0)
        {
            contentValues.put("username",username);
            MyDB.insert("curent_user",null,contentValues);

        }
        else{
            MyDB.rawQuery("update curent_user set username =?",new String[]{username});
        }
        MyDB.close();
    }

    public int checkCurentUser()
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from curent_user",null);
        if (cursor.getCount()==0)
        {
            return 0 ;
        }
        else{
            return 1;
        }
    }
    public String getCurentUser()
    {
        SQLiteDatabase Mydb =this.getReadableDatabase();
        Cursor cursor = Mydb.rawQuery("select * from curent_user",null);
String name = "";
        while(cursor.moveToNext())
        {
         name+=cursor.getString(0).toString();
        }
        Mydb.close();
        return  name;
    }
    public void deleteCurentUser()
    {

        SQLiteDatabase Mydb =this.getWritableDatabase();
        Mydb.execSQL("delete from curent_user");
    }
    public int check_todo(String user)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery("Select * from travel where name=? ",new String[]{user});
        if (cursor.getCount()>=1)
        {
            return 1;
        }
        else {
            return 0;
        }
    }
}











