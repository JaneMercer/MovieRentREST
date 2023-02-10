package company.movierental.rest;

import company.movierental.database.handlers.CategoryHandler;

public class CategoryController {
	private final CategoryHandler categoryHandler;

	public CategoryController(CategoryHandler categoryHandler) {
		this.categoryHandler = categoryHandler;
	}

}
