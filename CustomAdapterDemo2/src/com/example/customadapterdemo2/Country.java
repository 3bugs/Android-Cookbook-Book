package com.example.customadapterdemo2;

public class Country {
    private int m_flagImageId;
    private String m_countryName;
    private String m_countryOfficialName;
    private String m_capitalName;
    
    public Country(int flagImageId, String countryName, String countryOfficialName, String capitalName) {
        m_flagImageId = flagImageId;
        m_countryName = countryName;
        m_countryOfficialName = countryOfficialName;
        m_capitalName = capitalName;
    }

    public int getFlagImageId() {
        return m_flagImageId;
    }

    public void setFlagImageId(int flagImageId) {
        m_flagImageId = flagImageId;
    }

    public String getCountryName() {
        return m_countryName;
    }

    public void setCountryName(String countryName) {
        m_countryName = countryName;
    }

    public String getCountryOfficialName() {
        return m_countryOfficialName;
    }

    public void setCountryOfficialName(String countryOfficialName) {
        m_countryOfficialName = countryOfficialName;
    }
    public String getCapitalName() {
        return m_capitalName;
    }

    public void setCapitalName(String capitalName) {
        m_capitalName = capitalName;
    }
}
