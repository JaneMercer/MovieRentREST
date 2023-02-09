package company.movierental.json.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import company.movierental.utils.ManagerSecurityKey;

public class ManagerSecurityKeyTypeAdapter extends TypeAdapter<ManagerSecurityKey> {
	  private static final String KEY_FIELD = "managerKey";

	  @Override
	  public void write(JsonWriter out, ManagerSecurityKey key) throws IOException {
	    out.beginObject();
	    out.name(KEY_FIELD);
	    out.value(key.getManagerKey());
	    out.endObject();
	  }

	  @Override
	  public ManagerSecurityKey read(JsonReader in) throws IOException {
	    ManagerSecurityKey key = new ManagerSecurityKey();
	    in.beginObject();
	    while (in.hasNext()) {
	      if (in.nextName().equals(KEY_FIELD)) {
	        key.setManagerKey(in.nextString());
	      } else {
	        in.skipValue();
	      }
	    }
	    in.endObject();
	    return key;
	  }
	}
