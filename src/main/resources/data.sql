INSERT INTO CREDENTIAL (PASSWORD, USER_NAME, IS_ADMIN) 
    VALUES ('$2a$10$.FlxCNheD6GicnVDXiYBc.vQIGOEoG4EZWti9GVtKKlRWShPIfxhq', 'admin', 'TRUE');
    
INSERT INTO ADDRESS (NEIGHBORHOOD, NUMBER, REFERENCE, STREET, ZIP_CODE)
VALUES
('consectetur adipiscing elit',300,'sed semper urna hendrerit quis','suscipit sed venenatis est','52110000');

INSERT INTO USER(EMAIL, NAME, SOCIAL_ID, CREDENTIAL_FK, ADDRESS_FK)
    VALUES ('admin@admin.com', 'admin admin', '123', LAST_INSERT_ID(), LAST_INSERT_ID());

INSERT INTO CREDENTIAL (PASSWORD, USER_NAME, IS_ADMIN) 
    VALUES ('$2a$10$duQ4mc01rsuHWseGV0b6Bu17bT.5q09iUGQyoTLJ2rFpRRe4dkHIS', 'user', 'FALSE');
    
INSERT INTO ADDRESS (NEIGHBORHOOD, NUMBER, REFERENCE, STREET, ZIP_CODE)
VALUES
('adipiscing elitconsectetur',3321,'hendrerit quissed semper urna ','venenatis estsuscipit sed ','43210000');

INSERT INTO USER(EMAIL, NAME, SOCIAL_ID, CREDENTIAL_FK, ADDRESS_FK)
    VALUES ('user@user.com', 'user user', '321', LAST_INSERT_ID(), LAST_INSERT_ID());
    

INSERT INTO BOOK (AUTHOR, CATEGORY, FREE, RESUME, TITLE)
VALUES 
('J.K ROWLING', 0, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'HARRY POTTER AND THE SORCERER’S STONE'),
('R.R. MARTIN', 0, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'A GAME OF THRONES BY GEORGE'),
('PATRICK ROTHFUSS', 0, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'THE NAME OF THE WIND'),
('BRANDON SANDERSON', 0, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'THE WAY OF KINGS'),
('Susan Ee', 1, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Angelfall'),
('Julie Kagawa', 1, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'The Immortal Rules'),
('Amanda Hocking', 1, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Switched'),
('Hunter', 1, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Born at Midnight'),
('Steven Levy', 2, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Hackers: Heroes of the Computer Revolution'),
('Katie Hafner', 2, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Where Wizards Stay Up Late: The Origins of the Internet'),
('David Vise', 2, 'TRUE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'The Google Story: Inside the Hottest Business, Media, and Technology Success of Our Time'),
;

INSERT INTO BOOK_ORDER (ORDER_DATE, DUE_DATE, USER_FK)
VALUES
('2019-07-21 15:00:00','2019-08-21 15:00:00', 1);


INSERT INTO BOOK (AUTHOR, CATEGORY, FREE, RESUME, TITLE, ORDER_ID)
VALUES
('David Kirkpatrick', 2, 'FALSE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 
'The Facebook Effect: The Inside Story of the Company That is Connecting the World', LAST_INSERT_ID());

