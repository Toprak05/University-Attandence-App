package com.android.attendance.context;

import java.util.ArrayList;

import android.app.Application;

import com.android.attendance.bean.FacultyBean;
import com.android.attendance.bean.StudentBean;

public class ApplicationContext extends Application {
    private FacultyBean facultyBean;
    private ArrayList<StudentBean> studentBeanList;

    public FacultyBean getFacultyBean() {
        return facultyBean;
    }

    public void setFacultyBean(FacultyBean facultyBean) {
        this.facultyBean = facultyBean;
    }

    public ArrayList<StudentBean> getStudentBeanList() {
        return studentBeanList;
    }

    public void setStudentBeanList(ArrayList<StudentBean> studentBeanList) {
        this.studentBeanList = studentBeanList;
    }

}
