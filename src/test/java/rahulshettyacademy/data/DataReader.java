package rahulshettyacademy.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;


import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		// read json to string -> json file k data ko string form mai read kr raha h
		String jsonContent =FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	    ObjectMapper mapper =new ObjectMapper(); 
	    
	    // String to HashMap-Jackson Databind (HashMap k data ko List mai Store kr rahe h)
	    List<HashMap<String, String>> data =mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){
	    	
	    });
	    return data;
	    
	    
	}
}
