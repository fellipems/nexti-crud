package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DataCustomizadaSerialize extends JsonSerializer<Date> {
    
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void serialize(final Date data, 
    		final JsonGenerator gen, 
    		final SerializerProvider provider) 
    				throws IOException, JsonProcessingException {

        String dataString = format.format(data);
        gen.writeString(dataString);
    }

}
