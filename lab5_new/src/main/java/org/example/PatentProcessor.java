package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class PatentProcessor {
    // 3. Создание коллекции патентов, посвященных моделированию (simulation)
    public List<Patent> getSimulationPatents(List<Patent> allPatents) {
        List<Patent> simulationPatents = new ArrayList<>();
        for (Patent patent : allPatents) {
            if (patent.getTitle().toLowerCase().contains("simulation")) {
                simulationPatents.add(patent);
            }
        }
        return simulationPatents;
    }

    // 4. Проверка наличия дубликатов в списке патентов и создание коллекции уникальных патентов
    public List<Patent> getUniquePatents(List<Patent> patents) {
        Set<Patent> uniquePatents = new HashSet<>(patents);
        return new ArrayList<>(uniquePatents);
    }

    public Map<String, Integer> getPatentRegistrationCountries(List<Patent> patents) {
        Map<String, Integer> countryCounts = new HashMap<>();
        for (Patent patent : patents) {
            String country = patent.getNumber().substring(0, 2);
            countryCounts.put(country, countryCounts.getOrDefault(country, 0) + 1);
        }
        return countryCounts;
    }

    public List<Patent> getPatentsWithMultipleOwners(List<Patent> patents) {
        List<Patent> multipleOwnersPatents = new ArrayList<>();
        for (Patent patent : patents) {
            if (patent.getCompanies().size() > 1) {
                multipleOwnersPatents.add(patent);
            }
        }
        return multipleOwnersPatents;
    }

    public void writeResultsToFile(List<Patent> patents, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Patent patent : patents) {
                writer.write(patent.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PatentReader patentReader = new PatentReader();
        PatentProcessor patentProcessor = new PatentProcessor();

        String output_root = "src/main/output/";
        try {
            List<Patent> allPatents = patentReader.readCSV("src/main/resources/patents.csv");

            // 3. Создание коллекции патентов, посвященных моделированию (simulation)
            List<Patent> simulationPatents = patentProcessor.getSimulationPatents(allPatents);

            // 4. Проверка наличия дубликатов в списке патентов и создание коллекции уникальных патентов
            List<Patent> uniquePatents = patentProcessor.getUniquePatents(allPatents);

            // 5. Создание коллекции, содержащей страны регистрации патентов и количество патентов из списка, зарегистрированных в каждой стране
            Map<String, Integer> countryCounts = patentProcessor.getPatentRegistrationCountries(allPatents);

            // 6. Создание коллекции патентов, у которых более одного патентообладателя
            List<Patent> multipleOwnersPatents = patentProcessor.getPatentsWithMultipleOwners(allPatents);

            // Запись результатов в файл
            patentProcessor.writeResultsToFile(simulationPatents, output_root + "simulation_patents.txt");
            patentProcessor.writeResultsToFile(uniquePatents, output_root + "unique_patents.txt");
            patentProcessor.writeResultsToFile(multipleOwnersPatents, output_root + "multiple_owners_patents.txt");

            // Запись коллекции стран в файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output_root + "patent_countries.txt"))) {
                for (Map.Entry<String, Integer> entry : countryCounts.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}