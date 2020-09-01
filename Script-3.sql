--CREATE DATABASE project1;


CREATE TABLE Ers_Users(
	Ers_Users_ID SERIAL PRIMARY KEY,
	Ers_Username VARCHAR(50) NOT NULL,
	Ers_Password VARCHAR(50) NOT NULL,
	User_First_Name VARCHAR(100) NOT NULL,
	User_Last_Name VARCHAR(100) NOT NULL,
	User_Email VARCHAR(150) NOT NULL,
	User_Role_ID_FK INTEGER REFERENCES Ers_User_Roles(User_Role_ID),
	UNIQUE(Ers_Username, User_Email)
);

CREATE TABLE Ers_Reimbursement(
	Reimb_ID SERIAL PRIMARY KEY,
	Reimb_Amount NUMERIC(10,2) NOT NULL,
	Reimb_Submitted TIMESTAMP NOT NULL,
	Reimb_Resolved TIMESTAMP,
	Reimb_Description VARCHAR(250),
	Reimb_Receipt BYTEA,
	Reimb_Author_FK INTEGER NOT NULL REFERENCES Ers_Users(Ers_Users_ID),
	Reimb_Resolver_FK INTEGER REFERENCES Ers_Users(Ers_Users_ID),
	Reimb_Status_ID_FK INTEGER NOT NULL REFERENCES Ers_Reimbursement_Status(Reimb_Status_ID),
	Reimb_Type_ID_FK INTEGER NOT NULL REFERENCES Ers_Reimbursement_Type(Reimb_Type_ID)
);

CREATE TABLE Ers_Reimbursement_Status(
	Reimb_Status_ID SERIAL PRIMARY KEY,
	Reimb_Status VARCHAR(50) NOT NULL
);


CREATE TABLE Ers_Reimbursement_Type (
	Reimb_Type_ID SERIAL PRIMARY KEY,
	Reimb_Type VARCHAR(50) NOT NULL
);


CREATE TABLE Ers_User_Roles (
	User_Role_ID SERIAL PRIMARY KEY,
	User_Role VARCHAR(50) NOT NULL
);

insert into Ers_User_Roles(User_Role) 
Values('Employee'),
('Finance Manager');

insert into Ers_Reimbursement_Type(Reimb_Type)
Values('LODGING'),
('TRAVEL'),
('FOOD'),
('OTHER');

insert into Ers_Reimbursement_Status(Reimb_Status)
Values('PENDING'),
('APPROVED'),
('DENIED');

DROP TABLE IF EXISTS Ers_User_Roles;
DROP TABLE IF EXISTS Ers_Users;
DROP TABLE IF EXISTS Ers_Reimbursement;
DROP TABLE IF EXISTS Ers_Reimbursement_Type;
DROP TABLE IF EXISTS Ers_Reimbursement_Status;
DROP TABLE IF EXISTS Users;

