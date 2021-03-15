Movie Collection
================

General Info
------------
> Movie Collection is a Spring Boot Web Application using Spring Boot, Maven, JPA/Hibernate, Thymeleaf, Bootstrap, Spring Security, H2 Database.

### Features
* Add movies to the collection or Delete / Update movies from the collection.
* View movie entries (Name, Release Date, Description, Image, Rating, Cast)
* Login / Register to have access to viewing the collection or managing it.
* Authorization system, meaning that you have to be the admin to add/update/delete movies but viewing is accessable via a user role.

### How To Use
> #### What You Need
> Eclipse or SpringToolSuite4, Make sure port 8080 isn't in use.
* Download the files and make sure they're inside a folder. (Name can be anything)
* On SpringToolSuite4 (or Eclipse), File -> Import -> Existing Maven Projects -> Choose the folder
* When importing process is done, right-click on the root directory (from Package Explorer) Run As -> Spring Boot Application
* Once the app runs, open your browser and type http://localhost:8080 as the url. You'll get redirected to the login page.
* You can register, then login. There are already two registered accounts in the app.
* **Username: user Password: user** for a normal user experince (same as a newly registered account)
* **Username: admin Password: admin** for an admin user experience (You can add, edit and delete movie entries with this account).

### Screenshots
> ![Screenshot](ScreenShots/Screenshot_1.png)
> ![Screenshot](ScreenShots/Screenshot_2.png)
