package ch.zhaw.projectx.mongo;

import com.opencsv.CSVWriter;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVExporter {

    public void exportNodesToCSV(ArrayList<Document> dataList, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            for (Document data : dataList) {
                Integer id = data.getInteger("id");
                String name = data.getString("name");
                String[] line = {String.valueOf(id), name};
                writer.writeNext(line, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}