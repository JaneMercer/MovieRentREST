package company.movierental.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import company.movierental.database.model.Database;
import company.movierental.database.model.Movie;
import company.movierental.json.gson.LocalDateTimeTypeAdapter;
import company.movierental.json.gson.LocalDateTypeAdapter;
import company.movierental.json.gson.ManagerSecurityKeyTypeAdapter;
import company.movierental.utils.security.ManagerSecurityKey;

public class JsonHandler {
	protected String filePath;
	protected Database database;

	public JsonHandler(String filePath) {
		this.filePath = filePath;
		this.database = readDatabase();
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	private Database readDatabase() {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
				.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.registerTypeAdapter(ManagerSecurityKey.class, new ManagerSecurityKeyTypeAdapter()).create();

		try (FileReader reader = new FileReader(filePath)) {
			return gson.fromJson(reader, Database.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected boolean saveDatabase() {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
				.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.registerTypeAdapter(ManagerSecurityKey.class, new ManagerSecurityKeyTypeAdapter()).create();

		try (FileWriter writer = new FileWriter(this.filePath)) {
			gson.toJson(this.database, writer);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
