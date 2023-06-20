package imd.ufrn.br.purposesong.database.csv;

import imd.ufrn.br.purposesong.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CsvUtil {
    private static final String CSV_DELIMITER = ",";
    private final String[] CSV_HEADER;
    private final String CSV_PATH;
    public CsvUtil(String filePath, String[] headers){
        this.CSV_PATH = filePath;
        this.CSV_HEADER = headers;
    }

    public List<String[]> readCsvFile() {
        List<String[]> values = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_PATH))) {

            // Skip header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                values.add(line.split(CSV_DELIMITER));
            }
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
        }
        return values;
    }

    public void writeCsvFile(List<String[]> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_PATH))) {

            // Write CSV header
            writer.write(String.join(CSV_DELIMITER, CSV_HEADER));
            writer.newLine();

            for (String[] values : lines) {
                String line = String.join(CSV_DELIMITER, values);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
