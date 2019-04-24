package com.example.workerattendance;

import java.util.Calendar;

public class Config {
    public static final String LOGIN_HISTORY="login_history";
    public static final String UPDATES = "UPDATES";
    public static final String MATERIAL = "MATERIAL";
    public static final String MATERIAL_NAME = "MATERIAL_NAME";
    public static final String MATERIAL_QUANTITY = "MATERIAL_QUANTITY";
    public static final String LOGIN_TYPE = "type";
    public static final String CONSTRUCTOR = "Constructor";
    public static final String Client = "Client";
    public static final String PERCENTAGE = "percentage";
    public static final String STEEL = "Steel";
    public static final String TASKS = "tasks";
    public static final String SITE_ENGINEER ="SiteEngineer" ;
    public static final String CIVIL = "civil";
    public static final String STEAL= "steal";
    public static final String SITE_TYPE = "siteType";
    public static final String TASKS_UPDATE = "taskUpdate";
    public static final String MATERIAL_UPDATE = "materialUpdate";
    public static final String PDF_VALUE = "pdfValue";
    public static final String SITE_DATA = "siteData";
    public static final String EMAIL_OF_CLIENT = "emailOfClient";
    public static final String PROJECT_TYPE = "projectType";
    public static final String EMAIL_OF_CONSTRUCTOR = "emailOfConstructor";
    public static final String  CURRENT_DATE = "currentDate";
    public static final String SITE_ENGINEER_ID = "siteEngineerId";
    public static final String TASK_ID = "TASK_ID";
    public static String SITE_NAME = "siteName";
    public static String SITE_ID= "siteId";
    public static final String PROGRESS = "progress";
    public static final String REASON = "reason";
    public static String CLIENT_NAME = "clientName";

    public static String getDate() {
        Calendar c= Calendar.getInstance();
        return ""+c.get(Calendar.DATE)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR);
    }
}
