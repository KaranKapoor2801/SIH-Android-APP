package com.example.workerattendance.Adapter;

public class Students {

        private String taskName;
        private int per_comp;
        private int stime;
        private int etime;

        public Students(){

    }

    public Students(String taskName, int per_comp, int stime, int etime) {
        this.taskName = taskName;
        this.per_comp = per_comp;
        this.stime = stime;
        this.etime = etime;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getEtime() {
        return etime;
    }

    public int getPer_comp() {
        return per_comp;
    }

    public int getStime() {
        return stime;
    }

    @Override
    public String toString() {
        return "Students{" +
                "taskName='" + taskName + '\'' +
                ", per_comp=" + per_comp +
                ", stime=" + stime +
                ", etime=" + etime +
                '}';
    }

/*public String getStudentClass() {
        return studentClass;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "Students{" +
                "studentId='" + studentId + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }*/
}
