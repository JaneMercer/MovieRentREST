package company.movierental.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import company.movierental.database.handlers.InvoiceHandler;
import company.movierental.database.handlers.MovieHandler;

@RestController
@RequestMapping("/api/shoppingcart")
public class InvoiceController {
	private final InvoiceHandler invoiceHandler;

	public InvoiceController(InvoiceHandler invoiceHandler) {
		this.invoiceHandler = invoiceHandler;
	}
	
	
	//TODO:create the rest of Invoice REST endpoint methods

}
