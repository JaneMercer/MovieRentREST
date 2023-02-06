package company.movierental.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import company.movierental.database.model.Category;
import company.movierental.database.model.DataBase;
import company.movierental.database.model.Manager;
import company.movierental.database.model.Movie;
import company.movierental.database.model.Rating;
import company.movierental.database.model.RentalOrder;
import company.movierental.database.model.RentedMovie;
import company.movierental.database.model.User;
import company.movierental.service.MoviePrice;
import company.movierental.utils.security.ManagerSecurityKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TypeAdapterFactoryBuilder {
	private static TypeAdapterFactory createTypeAdapterFactory() {
		TypeAdapterFactory factory = RuntimeTypeAdapterFactory.of(DataBaseElement.class, "type")
				.registerSubtype(DataBase.class, "database").registerSubtype(Movie.class, "movie")
				.registerSubtype(Manager.class, "manager").registerSubtype(User.class, "user")
				.registerSubtype(Category.class, "category").registerSubtype(Rating.class, "rating")
				.registerSubtype(RentalOrder.class, "rentalOrder").registerSubtype(RentedMovie.class, "rentedMovie");
		return factory;
	}

	public static Gson createGson() {
		TypeAdapterFactory factory = createTypeAdapterFactory();
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(factory)
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
				.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.registerTypeAdapter(ManagerSecurityKey.class, new ManagerSecurityKeyTypeAdapter()).create();
		return gson;
	}
}