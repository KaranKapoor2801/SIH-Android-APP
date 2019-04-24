package com.example.workerattendance;

public class DataOfCardMainSite {
    private String siteName;
    private String clientName;

    public DataOfCardMainSite(String siteName,String clientName)
    {
        this.siteName=siteName;
        this.clientName=clientName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "DateOfCardMainSite{" +
                "siteName='" + siteName + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
