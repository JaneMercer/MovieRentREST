package company.movierental.utils.gson;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
	  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

	  @Override
	  public void write(JsonWriter out, LocalDate date) throws IOException {
	    if (date == null) {
	      out.nullValue();
	    } else {
	      out.value(FORMATTER.format(date));
	    }
	  }

	  @Override
	  public LocalDate read(JsonReader in) throws IOException {
	    return LocalDate.parse(in.nextString(), FORMATTER);
	  }
	}