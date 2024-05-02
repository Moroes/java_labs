package ru.mucrt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FirdgeReader 
{
    public static List<Fridge> readFile(String filePath) throws IOException, ParseException {
        List<Fridge> fridges = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader){
                    isHeader = false;
                    continue;
                }
                String[] data = line.split("\t");
                Fridge fridge = new Fridge();
                fridge.setId(Integer.parseInt(data[0]));
                fridge.setName(data[1]);
                fridge.setBrand(data[2]);
                fridge.setPrice(Double.parseDouble(data[3]));
                fridge.setColor(data[4]);
                fridge.setRating(Double.parseDouble(data[5].replace(',', '.')));
                fridges.add(fridge);
            }
        }

        return fridges;
    }
}
