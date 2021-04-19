package utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DataCustomizadaDeserialize extends JsonDeserializer<Date> {
	
	@Override
	  public Date deserialize(JsonParser jsonparser,
	                          DeserializationContext deserializationcontext) throws IOException {
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    String data = jsonparser.getText();
	    try {
	      return formato.parse(data);
	    } catch (ParseException e){
	      throw new RuntimeException(e);
	    }
	  }

}
