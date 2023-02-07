package company.movierental.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import company.movierental.database.model.Movie;
import company.movierental.database.model.Rating;

public class JsonHelper {

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

	public static Movie createMovieFromJson(JsonObject jsonObject) {

		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
				// case insensitive to parse JAN and FEB
				.parseCaseInsensitive()
				// add pattern
				.appendPattern("dd MMM yyyy")
				// create formatter (use English Locale to parse month names)
				.toFormatter(Locale.ENGLISH);

		// create list of Ratings
		JsonArray ratings = jsonObject.getAsJsonArray("Ratings");
		List<Rating> movieRatings = new ArrayList<>();
		for (int i = 0; i < ratings.size(); i++) {
			JsonObject rating = ratings.get(i).getAsJsonObject();
			movieRatings.add(new Rating(rating.get("Source").getAsString(), rating.get("Value").getAsString()));
		}

		try {
			String title = jsonObject.get("Title").getAsString();
			int year = jsonObject.get("Year").getAsInt();
			String rated = jsonObject.get("Rated").getAsString();
			String releaseDateJson = jsonObject.get("Released").getAsString();
			LocalDate releaseDate = null;
			if (!releaseDateJson.equals("N/A")) {
				releaseDate = LocalDate.parse(releaseDateJson, formatter);
			}
			String runtime = jsonObject.get("Runtime").getAsString();
			String genre = jsonObject.get("Genre").getAsString();
			String director = jsonObject.get("Director").getAsString();
			String writer = jsonObject.get("Writer").getAsString();
			String actors = jsonObject.get("Actors").getAsString();
			String plot = jsonObject.get("Plot").getAsString();
			String language = jsonObject.get("Language").getAsString();
			String country = jsonObject.get("Country").getAsString();
			String awards = jsonObject.get("Awards").getAsString();
			String poster = jsonObject.get("Poster").getAsString();
			String metascore = jsonObject.get("Metascore").getAsString();
			String imdbRating = jsonObject.get("imdbRating").getAsString();
			String imdbVotes = jsonObject.get("imdbVotes").getAsString();
			String imdbID = jsonObject.get("imdbID").getAsString();

			if (title == null || imdbRating == null || releaseDate == null) {
				throw new IllegalArgumentException(
						"Title, ImdbRating, and Released are required fields, cannot be null. Movie won't be created.");
			}

			return new Movie(title, year, rated, releaseDate, runtime, genre, director, writer, actors, plot, language,
					country, awards, poster, movieRatings, metascore, imdbRating, imdbVotes, imdbID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Error parsing movie information from JsonObject", e);
		}
	}

}
