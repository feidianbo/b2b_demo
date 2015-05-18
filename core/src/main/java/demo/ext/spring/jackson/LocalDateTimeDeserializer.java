package demo.ext.spring.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by joe on 1/14/15.
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    protected  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)throws IOException, JsonProcessingException {
        String string = jp.getText().trim();
        if(string.length() == 0)
            return null;
        return LocalDateTime.parse(string, formatter);
    }
}
