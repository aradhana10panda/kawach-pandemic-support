package com.stackroute.informationservice.model;

public class PandemicStats {

    private String state;
    private String district;
    private long totalConfirmed;
    private long totalRecovered;
    private long totalDeceased;
    private long activeCases;
    private String lastUpdated;

    public PandemicStats() {}

    public PandemicStats(String state, String district, long totalConfirmed,
                         long totalRecovered, long totalDeceased,
                         long activeCases, String lastUpdated) {
        this.state = state;
        this.district = district;
        this.totalConfirmed = totalConfirmed;
        this.totalRecovered = totalRecovered;
        this.totalDeceased = totalDeceased;
        this.activeCases = activeCases;
        this.lastUpdated = lastUpdated;
    }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public long getTotalConfirmed() { return totalConfirmed; }
    public void setTotalConfirmed(long totalConfirmed) { this.totalConfirmed = totalConfirmed; }
    public long getTotalRecovered() { return totalRecovered; }
    public void setTotalRecovered(long totalRecovered) { this.totalRecovered = totalRecovered; }
    public long getTotalDeceased() { return totalDeceased; }
    public void setTotalDeceased(long totalDeceased) { this.totalDeceased = totalDeceased; }
    public long getActiveCases() { return activeCases; }
    public void setActiveCases(long activeCases) { this.activeCases = activeCases; }
    public String getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }
}
