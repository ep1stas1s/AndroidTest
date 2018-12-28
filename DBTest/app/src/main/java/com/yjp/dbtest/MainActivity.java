package com.yjp.dbtest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;

    TextView textView;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Network Button (DB와 별개임) ========================================
        Button btn_network = findViewById(R.id.btn_network);
        btn_network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NetworkActivity.class);
                startActivity(intent);
            }
        });





        // SQLite 연결 =========================================================
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                openDatabase(databaseName);
            }
        });

        editText2 = findViewById(R.id.editText2);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });

        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText3.getText().toString().trim();
                String mobile = editText5.getText().toString().trim();

                int age = -1;
                try{
                    age = Integer.parseInt(editText4.getText().toString().trim());
                }catch (Exception e){

                }

                insertData(name, age, mobile);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                selectData(tableName);
            }
        });
    }

    public void openDatabase(String databaseName){
        println("openDatabase() 호출됨.");

        /*database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
        if(database != null){
            println("데이터베이스 오픈됨.");

        }*/

//        DatabaseHelper helper = new DatabaseHelper(this, databaseName, null, 1);
        DatabaseHelper helper = new DatabaseHelper(this, databaseName, null, 2);
        database = helper.getWritableDatabase();
    }

    public void createTable(String tableName){
        println("createTable() 호출됨");

        if(database != null){
            // [IF NOT EXISTS] 존재하지 않는다면 추가
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(_id integer PRIMARY KEY AUTOINCREMENT, name text, age integer, mobile text)";
            database.execSQL(sql);

            println("created Table");
        }else{
            println("plz, open DB");
        }
    }

    public void insertData(String name, int age, String mobile){
        println("insertData() 호출됨.");
        if(database != null){
            String sql = "INSERT INTO customer(name, age, mobile) VALUES(?, ?, ?)";
            Object[] params = {name, age, mobile};
            database.execSQL(sql, params);

            println("Added Data!");
        }else{
            println("plz, open DB");
        }
    }

    public void selectData(String tableName){
        println("selectData() 호출됨.");
        if(database != null){
            String sql = "SELECT NAME, AGE, MOBILE FROM " + tableName;
            Cursor cursor = database.rawQuery(sql, null);   // resultSet 과 유사
            println("조회된 데이터의 개수 : " + cursor.getCount());
//            while(cursor.moveToNext()){
//
//            }

            for(int i = 0; i < cursor.getCount(); i++){
                cursor.moveToNext();
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                println("#" + i + " -. " + name + ", " + age + ", " + mobile);
            }

            cursor.close();
        }
    }

    public void println(String data){
        textView.append(data + "\n");
    }

    // SQLite open Helper (DB가 변경 됐을 때 처리)
    class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        // 사용자가 기존 테이블을 사용하지 않았을 때의 경우
        @Override
        public void onCreate(SQLiteDatabase db) {
            println("onCreate() 호출됨.");

            String tableName = "customer";
            // [IF NOT EXISTS] 존재하지 않는다면 추가
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
            db.execSQL(sql);

            println("created Table");

        }

        // 기존 사용자 (기존 테이블을 사용했던 사용자)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            println("onUpgrade() 호출됨 : " + oldVersion + ", " + newVersion);

            if(newVersion > 1){
                String tableName = "customer";
                // 보통은 ALTER 를 통해 수정
                db.execSQL("DROP TABLE IF EXISTS " + tableName);
                println("테이블 삭제함");

                String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
                db.execSQL(sql);
                println("테이블 새로 생성됨.");
            }
        }
    }

}
