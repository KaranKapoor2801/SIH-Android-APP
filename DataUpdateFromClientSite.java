package com.example.workerattendance.models;

public class DataUpdateFromClientSite {
    private String projectType;
    private String siteName;
    private String siteId;
    private String emailOfConstructor;
    private String emailOfClient;
    private String currentDate;
    private String siteEngineerId;

    public DataUpdateFromClientSite() {

    }
    public DataUpdateFromClientSite(String projectType, String siteName, String siteId, String emailOfConstructor, String emailOfClient, String currentDate, String siteEngineerId) {
        this.projectType = projectType;
        this.siteName = siteName;
        this.siteId = siteId;
        this.emailOfConstructor = emailOfConstructor;
        this.emailOfClient = emailOfClient;
        this.currentDate = currentDate;
        this.siteEngineerId = siteEngineerId;
    }

    @Override
    public String toString() {
        return "DataUpdateFromClientSite{" +
                "projectType='" + projectType + '\'' +
                ", siteName='" + siteName + '\'' +
                ", siteId='" + siteId + '\'' +
                ", emailOfConstructor='" + emailOfConstructor + '\'' +
                ", emailOfClient='" + emailOfClient + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", siteEngineerId='" + siteEngineerId + '\'' +
                '}';
    }

    public String getSiteEngineerId() {
        return siteEngineerId;
    }

    public void setSiteEngineerId(String siteEngineerId) {
        this.siteEngineerId = siteEngineerId;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getEmailOfConstructor() {
        return emailOfConstructor;
    }

    public void setEmailOfConstructor(String emailOfConstructor) {
        this.emailOfConstructor = emailOfConstructor;
    }

    public String getEmailOfClient() {
        return emailOfClient;
    }

    public void setEmailOfClient(String emailOfClient) {
        this.emailOfClient = emailOfClient;
    }
}
