package com.brotherhui.cucumber.common.excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.io.ClassPathResource;

public class SimpleCsvUtils {

    private static SimpleCsvUtils simpleCsvUtils = new SimpleCsvUtils();

    private SimpleCsvUtils() {
    }

    public static SimpleCsvUtils getInstance() {
        return simpleCsvUtils;
    }
    
    
	public InputStream getResourceAsInputStream(final String filePath)
	        throws IOException {
		return new ClassPathResource(filePath).getInputStream();
	}

	public List<String> getResourceAsLines(final String filePath)
	        throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
		        this.getResourceAsInputStream(filePath)))) {
			return buffer.lines().collect(Collectors.toList());
		}
	}
	
 

    public List readCsv(String fileName){
        try (Stream<String> lines = this.getResourceAsLines(fileName)
		        .stream()) {
			return lines.map(line -> Arrays.asList(line.split(",")))
			        .collect(Collectors.toList());
	      } catch (IOException e) {
	          e.printStackTrace();
	          throw new RuntimeException("Fail to read csv with reason "+e.getMessage());
	      }
    }

    public <T> List<T> readCsvbjects(String fileName, Class<T> clazz, int offsetLine, int limitLine){
    	List<T> result = null;
		try {
            List<List<String>> csvContent = readCsv(fileName);
			result = readCsvObjectsHandler(csvContent, clazz, offsetLine, limitLine);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Fail to read csv with reason "+e.getMessage());
		}
        return result;
    }

    private <T> List<T> readCsvObjectsHandler(List<List<String>> csvContent, Class<T> clazz, int offsetLine, int limitLine) throws InstantiationException, IllegalAccessException, InvocationTargetException {

        List<T> list = new ArrayList<>();
        Map<Integer, CsvHeader> maps = BasicCsvTools.getHeaderMap(csvContent.get(0), clazz);
        if (maps == null || maps.size() <= 0)
            throw new RuntimeException("Check if the csv format is correct");
        int maxLine = csvContent.size() > (offsetLine + limitLine) ? (offsetLine + limitLine) : csvContent.size();

        for (int i = offsetLine; i < maxLine; i++) {
            List<String> row = csvContent.get(i);
            T obj = clazz.newInstance();
            for (int ci = 0; ci < row.size(); ci++) {
                String column = row.get(ci);
                CsvHeader header = maps.get(ci);
                if (null == header)
                    continue;
                String filed = header.getFiled();
                Object value = BasicCsvTools.str2TargetClass(column, header.getFiledClazz());
                BeanUtils.copyProperty(obj, filed, value);
            }
            list.add(obj);        
        }
        return list;
    }
    

}
