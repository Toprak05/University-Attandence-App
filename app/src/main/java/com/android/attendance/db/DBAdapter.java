package com.android.attendance.db;

import java.util.ArrayList;

import com.android.attendance.bean.FacultyBean;
import com.android.attendance.bean.StudentBean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Attendance";

    // Contacts table name
    private static final String FACULTY_INFO_TABLE = "faculty_table";
    private static final String STUDENT_INFO_TABLE = "student_table";

    // Contacts Table Columns names
    private static final String KEY_FACULTY_ID = "faculty_id";
    private static final String KEY_FACULTY_FIRSTNAME = "faculty_firstname";

    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_STUDENT_FIRSTNAME = "student_firstname";
    private static final String KEY_STUDENT_LASTNAME = "student_lastname";
    private static final String KEY_STUDENT_MO_NO = "student_mobilenumber";
    private static final String KEY_STUDENT_ADDRESS = "student_address";
    private static final String KEY_STUDENT_DEPARTMENT = "student_department";

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryFaculty = "CREATE TABLE " + FACULTY_INFO_TABLE + " (" +
                KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FACULTY_FIRSTNAME + " TEXT)";
        Log.d("queryFaculty", queryFaculty);

        String queryStudent = "CREATE TABLE " + STUDENT_INFO_TABLE + " (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_FIRSTNAME + " TEXT, " +
                KEY_STUDENT_LASTNAME + " TEXT, " +
                KEY_STUDENT_MO_NO + " TEXT, " +
                KEY_STUDENT_ADDRESS + " TEXT," +
                KEY_STUDENT_DEPARTMENT + " TEXT)";
        Log.d("queryStudent", queryStudent);

        try {
            db.execSQL(queryFaculty);
            db.execSQL(queryStudent);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        String queryFaculty = "CREATE TABLE " + FACULTY_INFO_TABLE + " (" +
                KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FACULTY_FIRSTNAME + " TEXT)";
        Log.d("queryFaculty", queryFaculty);

        String queryStudent = "CREATE TABLE " + STUDENT_INFO_TABLE + " (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_FIRSTNAME + " TEXT, " +
                KEY_STUDENT_LASTNAME + " TEXT, " +
                KEY_STUDENT_MO_NO + " TEXT, " +
                KEY_STUDENT_ADDRESS + " TEXT," +
                KEY_STUDENT_DEPARTMENT + " TEXT)";
        Log.d("queryStudent", queryStudent);

        try {
            db.execSQL(queryFaculty);
            db.execSQL(queryStudent);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    //facult crud
    public void addFaculty(FacultyBean facultyBean) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO faculty_table (faculty_firstname) values ('" +
                facultyBean.getFaculty_firstname() + "')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public ArrayList<FacultyBean> getAllFaculty() {
        Log.d("in get all", "in get all");
        ArrayList<FacultyBean> list = new ArrayList<FacultyBean>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM faculty_table";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                FacultyBean facultyBean = new FacultyBean();
                facultyBean.setFaculty_id(Integer.parseInt(cursor.getString(0)));
                facultyBean.setFaculty_firstname(cursor.getString(1));
                list.add(facultyBean);

            } while (cursor.moveToNext());
        }
        return list;
    }

    public void deleteFaculty(int facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM faculty_table WHERE faculty_id=" + facultyId;

        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    //student crud
    public void addStudent(StudentBean studentBean) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO student_table (student_firstname,student_lastname,student_mobilenumber,student_address,student_department) values ('" +
                studentBean.getStudent_firstname() + "', '" +
                studentBean.getStudent_lastname() + "','" +
                studentBean.getStudent_mobilenumber() + "', '" +
                studentBean.getStudent_address() + "', '" +
                studentBean.getStudent_department() + "')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public ArrayList<StudentBean> getAllStudent() {
        ArrayList<StudentBean> list = new ArrayList<StudentBean>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                StudentBean studentBean = new StudentBean();
                studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
                studentBean.setStudent_firstname(cursor.getString(1));
                studentBean.setStudent_lastname(cursor.getString(2));
                studentBean.setStudent_mobilenumber(cursor.getString(3));
                studentBean.setStudent_address(cursor.getString(4));
                studentBean.setStudent_department(cursor.getString(5));
                list.add(studentBean);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public StudentBean getStudentById(int studentId) {
        StudentBean studentBean = new StudentBean();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table where student_id=" + studentId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
                studentBean.setStudent_firstname(cursor.getString(1));
                studentBean.setStudent_lastname(cursor.getString(2));
                studentBean.setStudent_mobilenumber(cursor.getString(3));
                studentBean.setStudent_address(cursor.getString(4));
                studentBean.setStudent_department(cursor.getString(5));

            } while (cursor.moveToNext());
        }
        return studentBean;
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM student_table WHERE student_id=" + studentId;

        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }
}