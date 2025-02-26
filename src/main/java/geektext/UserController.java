package geektext;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
	//Setting up repositories
	private @Autowired UserRepository userRepository;
	private @Autowired CreditCardRepository ccRepository;

	//=====================
	//== Testing methods ==
	//=====================
	
	//Returns all users as JSON
	@ResponseBody
	@GetMapping(path="/user/all")
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	//Returns all credit cards as JSON
	@ResponseBody
	@GetMapping(path="/user/credit/all")
	public Iterable<CreditCard> getAllCreditCards() {
		return ccRepository.findAll();
	}
	
	//Gets a user via id; returns the user info as JSON
	@ResponseBody
	@GetMapping(path="/user/id/{id}")
	public Optional<User> getUserById(@PathVariable Integer id) {
		return userRepository.findById(id);
	}
	
	//Gets a credit card via id; returns the card info as JSON
	@ResponseBody
	@GetMapping(path="/user/credit/id/{id}")
	public Optional<CreditCard> getCreditCard(@PathVariable Integer id) {
		return ccRepository.findById(id);
	}
	
	//======================
	//==== Main methods ====
	//======================
	
	//Gets a user via username; returns the user info as JSON
	//TODO incorrect type/invalid input/missing/extra params
	@ResponseBody
	@GetMapping(path="/user/username/{username}")
	public Optional<User> getUser(@PathVariable String username) {
		int id = userRepository.findUserId(username);
		return userRepository.findById(id);
	}
	
	//Adds a user; returns nothing
	//TODO sql injection, incorrect type/invalid input/missing/extra params, nonunique username
	@ResponseBody
	@PostMapping(path="/user/add")
	public void addUser(
	@RequestParam("username")                   String username, 
	@RequestParam("password")                   String userPassword, 
	@RequestParam(required=false, name="name")  String fullName, 
	@RequestParam(required=false, name="email") String emailAddress, 
	@RequestParam(required=false, name="home")  String homeAddress) {
		//Create the user
		User u = new User();
		u.setUsername(username);
		u.setUserPassword(userPassword);
		u.setFullName(fullName);
		u.setEmailAddress(emailAddress);
		u.setHomeAddress(homeAddress);
		
		//Save the user to the database
		userRepository.save(u);
	}
	
	//Adds a credit card for a user; returns nothing
	//TODO sql injection, incorrect type/invalid input/missing/extra params
	@ResponseBody
	@PostMapping(path="/user/credit/add")
	public void addCreditCard(
	@RequestParam("username")          String username, 
	@RequestParam("cardNum")           Long cardNum, 
	@RequestParam("cvv")               String cvv, 
	@RequestParam("expDate") 
	@DateTimeFormat(pattern="yyyy-MM") Date expDate, 
	@RequestParam("cardholder")        String cardholder) {
		int id = userRepository.findUserId(username);
		
		//Create the credit card
		CreditCard c = new CreditCard();
		c.setUserId(id);
		c.setCardNum(cardNum);
		c.setCVV(cvv);
		c.setExpDate(expDate);
		c.setCardholder(cardholder);
		
		//Save the card to the database
		ccRepository.save(c);
	}
	
	//Updates a user's fields; returns nothing
	//TODO sql injection, incorrect type/invalid input/missing/extra params
	@ResponseBody
	@PutMapping(path="/user/update/{username}")
	public void updateUser(
	@PathVariable String username, 
	@RequestParam(required=false, name="password") String userPassword, 
	@RequestParam(required=false, name="name")     String fullName, 
	@RequestParam(required=false, name="email")    String emailAddress, 
	@RequestParam(required=false, name="home")     String homeAddress) {
		//Get the user
		int id = userRepository.findUserId(username);
		User u = userRepository.findById(id).get();
		
		//Set the new user values
		if(userPassword != null) {u.setUserPassword(userPassword);}
		if(fullName != null)     {u.setFullName(fullName);}
		if(emailAddress != null) {u.setEmailAddress(emailAddress);}
		if(homeAddress != null)  {u.setHomeAddress(homeAddress);}
		
		//Save the user to the database
		userRepository.save(u);
	}
	
}