			

			CREATE_SCROLLING_TABLE = CREATE table SCROLLING(
			ID INTEGER  NOT NULL  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			UserID INTEGER not null, BookID INTEGER not NULL, Location varchar(50) );
			
			UPDATE SCROLLING SET Location=? WHERE UserID=? AND BookID=?;
			INSERT INTO SCROLLING (UserID, BookID,Location)  VALUES(?,?,?);
		    SELECT Location FROM SCROLLING WHERE BookID=? AND UserID=?;
			
			CREATE_USERS_TABLE = CREATE table USERS(
			ID INTEGER  NOT NULL  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			Username varchar(100) not null primary key, Password varchar(100) not null,
			Email varchar(200) not null, Address varchar(300) not null, Telephone varchar(100) not null,
			Nickname varchar(100) not null, Description varchar(100), PhotoUrl varchar(7000));

			INSERT INTO USERS (Username,Password,Email,Address,Telephone,Nickname,Description,PhotoUrl)VALUES(?,?,?,?,?,?,?,?);

			SELECT * FROM USERS WHERE ID=?;
			SELECT * FROM USERS ORDER BY ID DESC;
			DELETE FROM USERS WHERE ID=?;

			CREATE table BOOKS(

			ID INTEGER  NOT NULL  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			Title varchar(100) not null, Url varchar(100) ,Price varchar(100),
			Description varchar(1000),Image varchar(100));
			
			INSERT INTO BOOKS (Title, Url,Price,Description,Image)  VALUES(?,?,?,?,?);
			SELECT * FROM BOOKS WHERE Title=?;
			SELECT * FROM BOOKS WHERE ID=?;

			SELECT ID, TITLE, URL, PRICE, DESCRIPTION, IMAGE,(SELECT count(islike) FROM REVIEWS WHERE bookid=BOOKS.ID AND islike=TRUE ),
			(SELECT COUNT(REVIEWS.ISLIKE) FROM REVIEWS WHERE REVIEWS.USERID=? AND REVIEWS.BOOKID=BOOKS.ID AND REVIEWS.ISLIKE=TRUE ),
			(SELECT COUNT(PURCHASED.ID) FROM PURCHASED WHERE PURCHASED.USERID=? AND PURCHASED.BOOKID=BOOKS.ID )
			FROM USERNAME.BOOKS;

			CREATE table PURCHASED(

			ID INTEGER  NOT NULL  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			UserID INTEGER not null, BookID INTEGER not NULL, TimePurchased TIMESTAMP);
			
			SELECT BookID FROM PURCHASED WHERE UserID=?;
			INSERT INTO PURCHASED (UserID, BookID,TimePurchased)  VALUES(?,?,CURRENT_TIMESTAMP);
			SELECT USERS.USERNAME, USERS.TELEPHONE ,BOOKS.TITLE ,BOOKS.ID,BOOKS.PRICE,PURCHASED.TimePurchased
			FROM PURCHASED INNER JOIN USERS ON USERS.ID=PURCHASED.UserID 
			INNER JOIN BOOKS ON BOOKS.ID=PURCHASED.BOOKID ORDER BY PURCHASED.TimePurchased DESC;

			CREATE table REVIEWS(

			ID INTEGER  NOT NULL  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			UserID INTEGER not null, BookID INTEGER not NULL, Review  varchar(8000) not NULL,
			Approved INTEGER, IsLike boolean);
			
			INSERT INTO REVIEWS (UserID, BookID,Review,Approved,IsLike)  VALUES(?,?,?,?,?);
			INSERT INTO REVIEWS (UserID, BookID,Review,Approved,IsLike)  VALUES(?,?,?,?,?);
			DELETE FROM REVIEWS WHERE ID=?;
			UPDATE REVIEWS SET Approved=2 WHERE ID=?;
			UPDATE REVIEWS SET Approved=1 WHERE ID=?;
			SELECT  USERS.Nickname, REVIEWS.Review FROM REVIEWS
			INNER JOIN USERS ON USERS.ID=REVIEWS.UserID
			WHERE REVIEWS.Approved=1 AND REVIEWS.BookID=? ORDER BY REVIEWS.ID DESC;
			SELECT  REVIEWS.ID, REVIEWS.BookID ,REVIEWS.UserID , USERS.Nickname, REVIEWS.Review
			FROM REVIEWS INNER JOIN USERS ON USERS.ID=REVIEWS.UserID WHERE REVIEWS.Approved=0;
			SELECT REVIEWS.BookID, USERS.ID ,USERS.NickName
			FROM REVIEWS INNER JOIN USERS ON USERS.ID=REVIEWS.UserID
			WHERE REVIEWS.IsLIKE=TRUE AND BookID=?;
			UPDATE REVIEWS SET IsLIKE=? WHERE UserID=? AND BookID=?;
			UPDATE REVIEWS SET REVIEW=? , Approved=0 WHERE UserID=? AND BookID=?;
			SELECT Nickname, ID FROM USERS WHERE Username=? and Password=?;

			SELECT BOOKS.ID,BOOKS.Title, BOOKS.Url ,BOOKS.Description ,BOOKS.Image,PURCHASED.TimePurchased,
			(SELECT count(islike) FROM REVIEWS WHERE bookid=BOOKS.ID AND islike=TRUE ),
			(SELECT COUNT(REVIEWS.ISLIKE) FROM REVIEWS WHERE REVIEWS.USERID=? AND REVIEWS.BOOKID=BOOKS.ID AND REVIEWS.ISLIKE=TRUE),
			BOOKS.Price	 FROM PURCHASED  INNER JOIN BOOKS ON BOOKS.ID=PURCHASED.BOOKID 
			WHERE PURCHASED.UserID=? ORDER BY PURCHASED.TimePurchased DESC;

			SELECT ID FROM PURCHASED WHERE UserID=? AND BookID=?;
			CREATE table MESSAGES(
			ID INTEGER  NOT NULL  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			FromUserID INTEGER not null, ToUserID INTEGER , Message varchar(8000), IsRead boolean,
			CreatedDate TIMESTAMP);
			INSERT INTO MESSAGES (FromUserID, ToUserID,Message,IsRead,CreatedDate)  VALUES(?,?,?,?,CURRENT_TIMESTAMP);
			SELECT ID,FromUserID, ToUserID, Message, ISREAD, CREATEDDATE
			FROM MESSAGES WHERE FROMUSERID=? OR TOUSERID=? ORDER BY CREATEDDATE DESC;
			UPDATE MESSAGES SET IsRead=true WHERE ID=?;

}