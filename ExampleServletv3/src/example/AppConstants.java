package example;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;

import entities.User;
import example.model.Customer;

/**
 * A simple place to hold global application constants
 */
public interface AppConstants {

	public final String CUSTOMERS = "customers";
	public final String CUSTOMERS_FILE = CUSTOMERS + ".json";
	public final String USERS = "users";
	public final String USERS_FILE = USERS + ".json";
	public final String NAME = "name";
	public final Type CUSTOMER_COLLECTION = new TypeToken<Collection<Customer>>() {}.getType();
	public final Type USER_COLLECTION = new TypeToken<Collection<User>>() {}.getType();
	//derby constants
	public final String DB_NAME = "DB_NAME";
	public final String DB_DATASOURCE = "DB_DATASOURCE";
	public final String PROTOCOL = "jdbc:derby:"; 
	public final String OPEN = "Open";
	public final String SHUTDOWN = "Shutdown";
	
	//sql statements
	public final String CREATE_CUSTOMERS_TABLE = "CREATE TABLE CUSTOMER(Name varchar(100),"
			+ "City varchar(100),"
			+ "Country varchar(100))";
	public final String INSERT_CUSTOMER_STMT = "INSERT INTO CUSTOMER VALUES(?,?,?)";
	public final String SELECT_ALL_CUSTOMERS_STMT = "SELECT * FROM CUSTOMER";
	public final String SELECT_CUSTOMER_BY_NAME_STMT = "SELECT * FROM CUSTOMER "
			+ "WHERE Name=?";
	
	public final String CREATE_USERS_TABLE = "CREATE table USERS("
/*		+"Id INTEGER NOT NULL  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"*/
			+"Username varchar(100) not null PRIMARY KEY,"
			+"Password varchar(100) not null,"
			+"Email varchar(100),"
			+"Address varchar(100),"
			+"Telephone varchar(100),"
			+"Nickname varchar(100) not null,"
			+"Description varchar(100),"
			+"PhotoUrl varchar(100))";
	
	
	public final String INSERT_USERS_STMT = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?,?)";
	public final String SELECT_USER_BY_NAME_STMT = "SELECT * FROM USERS "+ "WHERE Username=?";
	
	public final String SELECT_ALL_USERS_STMT = "SELECT * FROM USERS";
}
