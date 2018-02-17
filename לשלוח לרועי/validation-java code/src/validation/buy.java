/**
 * 
 */
package validation;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class buy {
	
	public static boolean checkPurchase(String firstname, String lastname, String email, String address, String city, String zip, JRadioButton visa, JRadioButton master, JRadioButton amex, String ccName, String ccNumber, String month, String year, String cvv) {
		
		LocalDateTime now = LocalDateTime.now();
		String currMonth = DateTimeFormatter.ofPattern("MM").format(now);
		String currYear = DateTimeFormatter.ofPattern("yy").format(now);
		
		Pattern generalPattern = Pattern.compile("^([a-zA-Z]|\\s)*$");
		Pattern mailPattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$");
		Pattern zipPattern = Pattern.compile("[0-9]{7}");
		Pattern visaPattern = Pattern.compile("^(?:4[0-9]{12}(?:[0-9]{3})?)$");
		Pattern masterPattern = Pattern.compile("^(?:5[1-5][0-9]{14})$");
		Pattern amexPattern = Pattern.compile("^(?:3[47][0-9]{13})$");
		Pattern monthPattern = Pattern.compile("^(0[1-9]|1[0-2])$");
		Pattern yearPattern = Pattern.compile("\\d{2}$");
		Pattern cvvPattern = Pattern.compile("\\d{3}$");

		
		Matcher first = generalPattern.matcher(firstname);
		boolean isFirst = first.matches();
		
		Matcher last = generalPattern.matcher(lastname);
		boolean isLast = last.matches();
		
		Matcher mail = mailPattern.matcher(email);
		boolean isMail = mail.matches();
		
		Matcher zipcode = zipPattern.matcher(zip);
		boolean isZip = zipcode.matches();
		
		Matcher City = generalPattern.matcher(city);
		boolean isCity = City.matches();
		
		Matcher CCName = generalPattern.matcher(ccName);
		boolean isCCName = CCName.matches();
		
		Matcher Visa = visaPattern.matcher(ccNumber);
		boolean isVisa = Visa.matches();
		
		Matcher Master = masterPattern.matcher(ccNumber);
		boolean isMaster = Master.matches();
		
		Matcher Amex = amexPattern.matcher(ccNumber);
		boolean isAmex = Amex.matches();
		
		Matcher Month = monthPattern.matcher(month);
		boolean isMonth = Month.matches();
		
		Matcher Year = yearPattern.matcher(year);
		boolean isYear = Year.matches();
		
		Matcher Cvv = cvvPattern.matcher(cvv);
		boolean isCvv = Cvv.matches();
		
		if (firstname == null || firstname.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "First name is a required field");
			return false;
		}
		else if (!isFirst) {
			JOptionPane.showMessageDialog(null, "First name format is incorrect");
			return false;
		}
		if (lastname == null || lastname.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Last name is a required field");
			return false;
		}
		else if (!isLast) {
			JOptionPane.showMessageDialog(null, "Last name format is incorrect");
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
		else if (zip == null || zip.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Zip code is a required field");
			return false;
		}
		else if (!isZip) {
			JOptionPane.showMessageDialog(null, "Zip format is incorrect");
			return false;
		}
		else if (ccName == null || ccName.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Name on card is a required field");
			return false;
		}
		else if (!isCCName) {
			JOptionPane.showMessageDialog(null, "Credit card name format is incorrect (Only English letters are required)");
			return false;
		}
		else if (ccNumber == null || ccNumber.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Credit card number is a required field");
			return false;
		}
		else {
			if (visa.isSelected()) {
				if (!isVisa) {
					JOptionPane.showMessageDialog(null, "Credit card number format is incorrect");
					return false;
				}
			}
			else if (master.isSelected()) {
				if (!isMaster) {
					JOptionPane.showMessageDialog(null, "Credit card number format is incorrect");
					return false;
				}
			}
			else if (amex.isSelected()) {
				if (!isAmex) {
					JOptionPane.showMessageDialog(null, "Credit card number format is incorrect");
					return false;
				}
			}	
		}
		if (month == null || month.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Expiration month is a required field");
			return false;
		}
		else if (year == null || year.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Expiration year is a required field");
			return false;
		}
		else if (!isMonth || !isYear || (Integer.valueOf(year) < Integer.valueOf(currYear)) || ((Integer.valueOf(month) < Integer.valueOf(currMonth)) && (Integer.valueOf(year) == Integer.valueOf(currYear)))) {
			JOptionPane.showMessageDialog(null, "Expiration date is not correct");
			return false;
		}
		else if (cvv == null || cvv.isEmpty()) {
			JOptionPane.showMessageDialog(null, "CVV is a required field");
			return false;
		}
		else if (!isCvv) {
			JOptionPane.showMessageDialog(null, "CVV format is incorrect");
			return false;
		}
		else {
			return true;
		}
	}

	public static void main(String[] args) {
		JRadioButton visa = new JRadioButton("visa");
		JRadioButton master = new JRadioButton("master");
		JRadioButton amex = new JRadioButton("amex");
		master.setSelected(true);
		boolean c = checkPurchase("d","harv","yoni_we1@hotmail.com","dfdf","sdf","3009500",visa,master,amex,"Jonathan Weidenfeld","5111111111111111","02","18","954");
		System.out.println(c);
	}

}
