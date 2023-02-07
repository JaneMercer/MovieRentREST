package company.movierental.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import company.movierental.database.model.Database;
import company.movierental.database.model.Manager;
import company.movierental.json.gson.LocalDateTimeTypeAdapter;
import company.movierental.json.gson.LocalDateTypeAdapter;
import company.movierental.json.gson.ManagerSecurityKeyTypeAdapter;
import company.movierental.json.gson.TypeAdapterFactoryBuilder;
import company.movierental.utils.security.ManagerSecurityKey;

public class DatabaseFileController {

	public static void createJsonFile(String name, String folder, ArrayList<Manager> managers) throws IOException {

		LocalDateTime creationDate = LocalDateTime.now();
		LocalDateTime lastEditDate = LocalDateTime.now();
		String finalPath = folder + name + ".json";
		String format = "JSON";
		Database database = new Database(name, finalPath, managers);
		database.setCreationDate(creationDate);
		database.setFormat(format);
		database.setLastEditDate(lastEditDate);

		File file = new File(finalPath);
		if (file.createNewFile()) {
			Gson gson = new GsonBuilder().setPrettyPrinting()
					.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
					.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
					.registerTypeAdapter(ManagerSecurityKey.class, new ManagerSecurityKeyTypeAdapter()).create();

			try (FileWriter writer = new FileWriter(finalPath)) {
				gson.toJson(database, writer);
				writer.flush();
			} catch (IOException e) {
				throw new IOException("Error writing to file: " + finalPath, e);
			}
		} else {
			throw new IOException("File already exists: " + finalPath);
		}
	}

//	TODO: Write "JsonObject readJsonFile(String databasePath, String databaseClasstoRead)" Idea is that it would read 
	// Create another small file that would work as a pair for "database REST name: database path"
	// Write "writeJSsonFile(JsonObject ...)" ...Just search a bit more about how to do this
}
