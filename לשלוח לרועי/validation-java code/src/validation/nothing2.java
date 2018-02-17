/*<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>payment</title>
<link rel="stylesheet" type="text/css" href="Pages/desginCSS/mycss.css">
</head>
<body>
          <h4>Billing address</h4>
          <form action="">
                <label for="firstName">First name</label>
                <input type="text"  id="firstName" placeholder="" value="" required>
                <div class="invalid-feedback">
                  <small>Valid first name is required.</small>
                </div>
                <label for="lastName">Last name</label>
                <input type="text" id="lastName" placeholder="" required>
                <div class="invalid-feedback">
                 <small>Valid last name is required.</small>
                </div>

              <label for="email">Email</label>
              <input type="email"  id="email" placeholder="you@example.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
              <div class="invalid-feedback">
                <small>Please enter a valid email address for shipping updates.</small>
              </div>

              <label for="address">Address</label>
              <input type="text"  id="address" placeholder="21 Harakafot St" required>
              <div class="invalid-feedback">
                <small>Please enter your shipping address.</small>
              </div>

              <label for="city">City</label>
              <input type="text"  id="city" placeholder="Tel Aviv" pattern="[a-zA-Z ]*" required>

          
               <label for="country">Country</label>
               <input type="text"  id="country" value="Israel" required disabled>
               <div class="invalid-feedback">
                 Please enter a valid country.
               </div>
                
                <label for="zip">Zip</label>
                <input type="text"  id="zip" placeholder="3009500" maxlength="7" pattern="[0-9]{7}" required>
                <div class="invalid-feedback">
                  <small>Zip code required.</small>
                </div>
      
            <hr>

            <h4>Payment</h4>

            <div id="credit card">
                <input id="visa" name="paymentMethod" type="radio" value="visa" checked required>
                <label for="visa">Visa</label><img class="img" src="pictures/pics for buy page/Visa (1).png">
                <input id="mastercard" name="paymentMethod" type="radio" value="mastercard" required>
                <label for="mastercard">Mastercard</label><img class="img" src="pictures/pics for buy page/320px-MasterCard_logo.png">
                <input id="amex" name="paymentMethod" type="radio" value="amex" required>
                <label for="amex">American Express</label><img class="img" src="pictures/pics for buy page/amex.png">
           </div>
           
                <label for="cc-name">Name on card</label>
                <input type="text"  id="cc-name" placeholder="" required>
                <small>Full name as displayed on card</small>
                <div class="invalid-feedback">
                  <small>Name on card is required</small>
                </div>

                <label for="cc-number">Credit card number</label>
                <input type="text" id="cc-number" placeholder="" required>
                <div class="invalid-feedback">
                  <small>Credit card number is required</small>
                </div>
                
    
                <label for="cc-expiration">Expiration</label>
                <input type="text" id="month" placeholder="MM" maxlength="2" size="2" required>
                <span>/</span>
                <input type="text" id="year" placeholder="YY" maxlength="2" size="2" required>
                <div class="invalid-feedback">
                  <small>Expiration date required</small>
                </div>

                <label for="cc-expiration">CVV</label>
                <input type="text" id="cc-cvv" placeholder="" required>
                <div class="invalid-feedback">
                  <small>Security code required</small>
                </div>
          
            <hr>

            <button type="button" class="signupbtn" onclick="validatePurchase()">Buy book</button>
           <p id="message"></p>
          </form>
          <script src="Pages/JavaScript/scripts.js"></script>
        
</body>
</html>*/