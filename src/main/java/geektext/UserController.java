package geektext;

import java.util.Date;
//TODO import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
	//Setting up repositories and assemblers
	private @Autowired UserRepository userRepository;
	private @Autowired CreditCardRepository ccRepository;
	private @Autowired UserModelAssembler userAssembler;

	//=====================
	//== Testing methods ==
	//==== TODO remove ====
	//=====================
	
//	//Returns all users as JSON
//	@ResponseBody
//	@GetMapping(path="/user")
//	public Iterable<User> getAllUsers() {
//		return userRepository.findAll();
//	}
//	
//	//Returns all credit cards as JSON
//	@ResponseBody
//	@GetMapping(path="/credit")
//	public Iterable<CreditCard> getAllCreditCards() {
//		return ccRepository.findAll();
//	}
//	
//	//Gets a user via id; returns the user info as JSON
//	@ResponseBody
//	@GetMapping(path="/user/id/{id}")
//	public Optional<User> getUserById(@PathVariable Integer id) {
//		return userRepository.findById(id);
//	}
//	
//	//Gets a credit card via id; returns the card info as JSON
//	@ResponseBody
//	@GetMapping(path="/credit/id/{id}")
//	public Optional<CreditCard> getCreditCard(@PathVariable Integer id) {
//		return ccRepository.findById(id);
//	}
	
	//======================
	//==== Main methods ====
	//======================
	
	//Helper method for getting user id via username
	private Integer findUserId(String username) {
		//Get user id as Object for error checking
		Object o = userRepository.findId(username);
		
		//Throw exception if user doesn't exist
		if(o == null) {throw new UserNotFoundException(username);}
		
		//Otherwise id is valid int so cast back to Integer
		return (Integer) o;
	}
	
	//Gets a user via username; returns the user info as JSON
	@ResponseBody
	@GetMapping(path="/user")
	public ResponseEntity<?> getUser(@RequestParam("username") String username) {
		//Get the user
		int id = findUserId(username);
		User u = userRepository.findById(id).get();
		
		//Build a JSON model for the user
		EntityModel<User> uModel = userAssembler.toModel(u);
		
		return ResponseEntity.ok(uModel);
	}
	
	//Adds a user; returns nothing
	@ResponseBody
	@PostMapping(path="/user/add")
	public ResponseEntity<?> addUser(
	@RequestParam("username")                   String username, 
	@RequestParam("password")                   String userPassword, 
	@RequestParam(required=false, name="name")  String fullName, 
	@RequestParam(required=false, name="email") String emailAddress, 
	@RequestParam(required=false, name="home")  String homeAddress) {
		//Throw exception if username is already taken
		Object o = userRepository.findId(username);
		if(o != null) {throw new DuplicateUsernameException(username);}
		
		//Throw exception if required params are empty
		String e = "";
		if(username.compareTo(e) == 0 
		|| userPassword.compareTo(e) == 0) {throw new EmptyParamException();}
		
		//Create the user
		User u = new User();
		u.setUsername(username);
		u.setUserPassword(userPassword);
		u.setFullName(fullName);
		u.setEmailAddress(emailAddress);
		u.setHomeAddress(homeAddress);
		
		//Save the user to the database	
		userRepository.save(u);
		
		return ResponseEntity.noContent().build();
	}
	
	//Adds a credit card for a user; returns nothing
	@ResponseBody
	@PostMapping(path="/credit/add")
	public ResponseEntity<?> addCreditCard(
	@RequestParam("username")          String username, 
	@RequestParam("cardNum")           Long cardNum, 
	@RequestParam("cvv")               String cvv, 
	@RequestParam("expDate") 
	@DateTimeFormat(pattern="yyyy-MM") Date expDate, 
	@RequestParam("cardholder")        String cardholder) {
		int id = findUserId(username);
		
		//Throw exception if required params are empty
		String e = "";
		if(cvv.compareTo(e) == 0
		|| cardholder.compareTo(e) == 0) {throw new EmptyParamException();}
		
		//Create the credit card
		CreditCard c = new CreditCard();
		c.setUserId(id);
		c.setCardNum(cardNum);
		c.setCVV(cvv);
		c.setExpDate(expDate);
		c.setCardholder(cardholder);
		
		//Save the card to the database
		ccRepository.save(c);
		
		return ResponseEntity.noContent().build();
	}
	
	//Updates a user's fields; returns nothing
	@ResponseBody
	@PutMapping(path="/user/{username}")
	public ResponseEntity<?> updateUser(
	@PathVariable String username, 
	@RequestParam(required=false, name="password") String userPassword, 
	@RequestParam(required=false, name="name")     String fullName, 
	@RequestParam(required=false, name="email")    String emailAddress, 
	@RequestParam(required=false, name="home")     String homeAddress) {
		//Get the user
		int id = findUserId(username);
		User u = userRepository.findById(id).get();
		
		//Set the new user values
		//Throw exception if params are empty (but not null)
		String e = "";
		if(userPassword != null) {
			if(userPassword.compareTo(e) == 0) {throw new EmptyParamException();}
			else {u.setUserPassword(userPassword);}
		}
		if(fullName != null)     {
			if(fullName.compareTo(e) == 0) {throw new EmptyParamException();}
			else {u.setFullName(fullName);}
		}
		if(emailAddress != null) {
			if(emailAddress.compareTo(e) == 0) {throw new EmptyParamException();}
			else {u.setEmailAddress(emailAddress);}
		}
		if(homeAddress != null)  {
			if(homeAddress.compareTo(e) == 0) {throw new EmptyParamException();}
			else {u.setHomeAddress(homeAddress);}
		}
		
		//Save the user to the database
		userRepository.save(u);
		
		return ResponseEntity.noContent().build();
	}
	
}