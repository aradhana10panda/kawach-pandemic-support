package com.stackroute.informationservice.service;

import com.stackroute.informationservice.model.PandemicStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformationService {

    // Public COVID-19 data source (India district-level data)
    private static final String COVID_DATA_URL =
            "https://api.covid19india.org/csv/latest/district_wise.csv";

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Fetches pandemic stats from external CSV API and parses them.
     * Returns empty list with a default entry if the API is unavailable.
     */
    public List<PandemicStats> getAllStats() {
        try {
            String csvData = restTemplate.getForObject(COVID_DATA_URL, String.class);
            return parseCsv(csvData);
        } catch (Exception e) {
            // Return placeholder data if external API is unavailable
            return getPlaceholderStats();
        }
    }

    public List<PandemicStats> getStatsByState(String state) {
        return getAllStats().stream()
                .filter(s -> state.equalsIgnoreCase(s.getState()))
                .collect(Collectors.toList());
    }

    public List<PandemicStats> getStatsByDistrict(String district) {
        return getAllStats().stream()
                .filter(s -> district.equalsIgnoreCase(s.getDistrict()))
                .collect(Collectors.toList());
    }

    private List<PandemicStats> parseCsv(String csvData) {
        List<PandemicStats> statsList = new ArrayList<>();
        if (csvData == null || csvData.isEmpty()) {
            return statsList;
        }
        try (CSVParser parser = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim()
                .parse(new StringReader(csvData))) {

            for (CSVRecord record : parser) {
                try {
                    PandemicStats stats = new PandemicStats();
                    stats.setState(record.get("State"));
                    stats.setDistrict(record.get("District"));
                    stats.setTotalConfirmed(parseLong(record, "Confirmed"));
                    stats.setTotalRecovered(parseLong(record, "Recovered"));
                    stats.setTotalDeceased(parseLong(record, "Deceased"));
                    stats.setActiveCases(parseLong(record, "Active"));
                    stats.setLastUpdated(record.isMapped("Last_Updated_Time")
                            ? record.get("Last_Updated_Time") : "N/A");
                    statsList.add(stats);
                } catch (Exception ignored) {
                    // skip malformed rows
                }
            }
        } catch (Exception e) {
            return getPlaceholderStats();
        }
        return statsList;
    }

    private long parseLong(CSVRecord record, String column) {
        try {
            String val = record.isMapped(column) ? record.get(column).trim() : "0";
            return val.isEmpty() ? 0L : Long.parseLong(val);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    private List<PandemicStats> getPlaceholderStats() {
        List<PandemicStats> list = new ArrayList<>();
        list.add(new PandemicStats("Maharashtra", "Pune", 500000, 490000, 8000, 2000, "N/A"));
        list.add(new PandemicStats("Delhi", "Central Delhi", 700000, 690000, 10000, 0, "N/A"));
        return list;
    }
}
