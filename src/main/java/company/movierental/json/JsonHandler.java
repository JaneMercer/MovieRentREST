package company.movierental.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import company.movierental.database.model.Database;
import company.movierental.database.model.Manager;
import company.movierental.json.gson.LocalDateTimeTypeAdapter;
import company.movierental.json.gson.LocalDateTypeAdapter;
import company.movierental.json.gson.ManagerSecurityKeyTypeAdapter;
import company.movierental.utils.ManagerSecurityKey;

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

	// TODO: save using saveDatabase
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

	public static JsonObject fetchJsonObject(String requestURL) throws IOException {
		URL url = new URL(requestURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "*/*");
		connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

		InputStream stream = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);
		StringBuilder response = new StringBuilder();
		String line;

		while ((line = buffer.readLine()) != null) {
			response.append(line);
		}

		buffer.close();
		connection.disconnect();
		return new Gson().fromJson(response.toString(), JsonObject.class);
	}

}
