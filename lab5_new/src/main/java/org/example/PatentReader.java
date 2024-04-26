package org.example;

import org.example.Patent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PatentReader {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public List<Patent> readCSV(String filePath) throws IOException, ParseException {
        List<Patent> patents = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\",\"");
                Patent patent = new Patent();
                patent.setTitle(data[0]);
                patent.setNumber(data[1]);
                // Remove quotes from date string before parsing
                String dateStr = data[2].replaceAll("\"", "");
                patent.setDate(DATE_FORMAT.parse(dateStr));
                // Assuming inventors, companies, and mpk are comma-separated values in the file
                patent.setInventors(List.of(data[3].split(";")));
                patent.setCompanies(List.of(data[4].split(";")));
                patent.setMpk(List.of(data[5].split(";")));
                patents.add(patent);
            }
        }

        return patents;
    }

    public static void main(String[] args) {
        PatentReader patentReader = new PatentReader();
        try {
            List<Patent> patents = patentReader.readCSV("src/main/resources/patents.csv");
            // Now you have the list of patents
            for (Patent patent : patents) {
                System.out.println(patent);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
