package galen.helpers.common;

import galen.helpers.tenant.dexter.DexterMetricsRecord;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class CSVHelpers {
    WebDriver driver;

    public CSVHelpers (WebDriver driver) {
        this.driver=driver;
    }

    public CSVHelpers () {
    }

    LinkedHashMap<String, String> parseCSV(File fileName, String columnName, String columnValue) throws IOException {
        LinkedHashMap<String, String> localRecordMap = new LinkedHashMap<>();
        List<String> metricsColumns;
        List<String> columnValues;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        metricsColumns= Arrays.asList(line.split(","));
        line=br.readLine();
        int j = 0;
        String mapValue;

        while (line!=null) {
            line=line.replace("\"Yes, ", "\"Yes ").replace("\"No, ","\"No ");
            columnValues= Arrays.asList(line.split(","));
            if (columnValues.get(metricsColumns.indexOf(columnName)).equalsIgnoreCase(columnValue)) {
                while (j < metricsColumns.size() && j < columnValues.size()) {
                    if (metricsColumns.get(j).equalsIgnoreCase("KNOWSBP") ) {
                        mapValue=columnValues.get(j).toUpperCase().replace("\"YES ", "\"YES, ").
                                replace("\"NO ","\"NO, ");
                    } else {
                        mapValue=columnValues.get(j).toUpperCase();
                    }
                    localRecordMap.put(metricsColumns.get(j).toUpperCase(), mapValue);
                    j++;
                }
                return localRecordMap;
            }
            line=br.readLine();
        }
        return localRecordMap;
    }

    public void verifyAllMetricsValues(DexterMetricsRecord dmr, File fileName, @Nullable GalenReport report) throws IOException {
        LinkedHashMap<String, Object[]> metricMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> localRecordMap = parseCSV(fileName, "AssessmentNumber", dmr.AssessmentNumber);
        List<Field> fieldlist = Arrays.asList(DexterMetricsRecord.class.getDeclaredFields());

        fieldlist.forEach((Field it)->
        {
            String field = it.getName();
            if (localRecordMap.containsKey(field.toUpperCase())) {
                Object value = dmr.getProperty(field);
                Object value2 = localRecordMap.get(field.toUpperCase());
                metricMap.put(field, new Object[]{value, value2});
            }
         });
         if (report!=null) {
             String name = (fileName.toString().contains("Individual")) ? "Individual":"Bulk";
             report.addMetricsVerificationStep(metricMap, false);
         }
    }

    public String getCSVValueByAssessmentID(File fileName, String assessmentNumber, String column) {
        try {
            LinkedHashMap<String, String> localRecordMap = parseCSV(fileName, "AssessmentNumber", assessmentNumber);
            return  localRecordMap.get(column.toUpperCase());
        } catch(Exception e) {
            return null;
        }
    }

    public boolean verifyCSVValuePresent(File fileName, String assessmentID, String column) throws IOException {
        LinkedHashMap<String, String> localRecordMap = parseCSV(fileName, "AssessmentNumber", assessmentID);
        if (localRecordMap.size()==0) { return false; }
        return  !localRecordMap.get(column.toUpperCase()).equalsIgnoreCase("");
    }

}
