/**
 * 
 */
package validation;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup {
	
	public static boolean checkRegister(String username, String email, String address, String zip, String city, String phone, String cellphone, String nickname, String psw, String pswRepeat) {
		
		Pattern generalPattern = Pattern.compile("^([a-zA-Z]|\\s)*$");
		Pattern mailPattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$");
		Pattern zipPattern = Pattern.compile("[0-9]{7}");
		
		Matcher user = generalPattern.matcher(username);
		boolean isUser = user.matches();
		
		
		Matcher mail = mailPattern.matcher(email);
		boolean isMail = mail.matches();
		
		
		Matcher zipcode = zipPattern.matcher(zip);
		boolean isZip = zipcode.matches();
		
		Matcher City = generalPattern.matcher(city);
		boolean isCity = City.matches();
		
		Matcher Phone = zipPattern.matcher(phone);
		boolean isPhone = Phone.matches();
		
		Matcher Cellphone = zipPattern.matcher(cellphone);
		boolean isCellphone = Cellphone.matches();
		
		if (username == null || username.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "User name is a required field");
			return false;
		}
		else if (!isUser) {
			JOptionPane.showMessageDialog(null, "User name format is incorrect");
			return false;
		}
		else if (email == null || email.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Email is a required field");
			return false;
		}
		else if (!isMail) {
			JOptionPane.showMessageDialog(null, "Email format is incorrect");
			return false;
		}
		else if (address == null || address.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Address is a required field");
			return false;
		}
		else if (zip == null || zip.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Zip code is a required field");
			return false;
		}
		else if (!isZip) {
			JOptionPane.showMessageDialog(null, "Zip code format is incorrect");
			return false;
		}
		else if (city == null || city.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "City is a required field");
			return false;
		}
		else if (!isCity) {
			JOptionPane.showMessageDialog(null, "City format is incorrect");
			return false;
		}
		else if (city.length() < 3) {
			JOptionPane.showMessageDialog(null, "City must contain 3 or more characters");
			return false;
		}
		else if (phone == null || phone.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Phone number is a required field");
			return false;
		}
		else if (!isPhone) {
			JOptionPane.showMessageDialog(null, "Phone number format is incorrect");
			return false;
		}
		else if (cellphone == null || cellphone.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Cellphone number is a required field");
			return false;
		}
		else if (!isCellphone) {
			JOptionPane.showMessageDialog(null, "Cellphone number format is incorrect");
			return false;
		}
		else if (nickname == null || nickname.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Nickname is a required field");
			return false;
		}
		else if (psw == null || psw.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Password is a required field");
			return false;
		}
		else if (pswRepeat == null || pswRepeat.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Password verification is needed");
			return false;
		}
		else if (pswRepeat != psw)
		{
			JOptionPane.showMessageDialog(null, "Password not repeated correctly");
			return false;
		}
		return true;
		
	}

	public static void main(String[] args) {
		boolean c = checkRegister("d","yoni_we1@hotmail.com","3","3009500","dfdff","5555555","5555556","fdfdfd","sdsd","sdsd");
		System.out.println(c);
	}

}
