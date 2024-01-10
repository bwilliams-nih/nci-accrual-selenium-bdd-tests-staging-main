package gov.nci.ctrp.accrual.utils;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUtils {
    public static List<String> getCellHeaders(String file) {
        List<String> list = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            nextRecord = csvReader.readNext();
            for (String cell : nextRecord) {
                list.add(cell);
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getRowData(String file)
    {
        String delimiter = ",";
        String line;
        List<String> lines = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            while((line = br.readLine()) != null){
                List<String> values = Arrays.asList(line.split(delimiter));
                lines.add(values.toString());
            }
            lines.forEach(System.out::println);
        } catch (Exception e){
            System.out.println(e);
        }
        return  lines;
    }
}
