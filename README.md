README.md  
ABINAYA R  
12/28/2020  

**CODE AVAILABILITY:**       

GitHub URL: **https://github.com/AbinayaRav/AlbumApp.git**  
AWS Elastic BeanStalk URL : **albumapp-env.eba-2nksypm8.us-east-1.elasticbeanstalk.com/**  


**PROJECT DESCRIPTION:**  

This Spring MVC Album application performs all the CRUD operations from the front end.  
**1. Create a new Album, Song or Singer**      
**2. Retrieve details of all the entities (Album, Song or Singer)**       
**3. Edit individual album, Song or Singer details.**  
**4. Delete any of the album, Song or Singer.**          
**4. Add/Delete song to an existing album, view all the songs/singers in an album**        
**5. Retrieve all the songs sung by a singer.**    

The following have been implemented this architecture.
-The Presentation layer consists of controllers(AlbumController, SingerController, SongController) to communicate with Service Layer.  
-Views(Create/Edit,Retrieve) are implemented to display front end web pages (UI). Technologies Used : HTML5, Bootstrap, Thymeleaf (EL).    
-The business logic to perform all the CRUD operations by interacting with Databases are provided in RestControllers and Controllers.   
-Three Repositories (AlbumRepository, SingerRepository, SongRepository) are used to expose the entities as resources.  
-3 Models - Album, Song and Singer which are ORM related. JPA is used to exhibit the models as persistence objects. Relationships used are:  
     &nbsp;&nbsp; &nbsp;&nbsp;    One to Many Bidirectional relationship between Album and Song.  
     &nbsp;&nbsp; &nbsp;&nbsp;    Many to One Unidirectional relationship between Song and Singer.  
     &nbsp;&nbsp; &nbsp;&nbsp;    Many to Many Bidirectional relationship between Album and Singer.  
-MySQL DB for database Connectivity.
-Spring Security has been implemented for the project. Oauth2 secured through github and google.  
-Project has been successfully completed and deployed on to AWS cloud.  


**INSTALLATION, COMPILE AND RUNTIME REQUIREMENTS:**  

**IDE**    
IntelliJ IDEA Community Edition 2020.2.3 x64  

The assignment has been coded and runs on  
**HARDWARE**    
• Intel® Core™ i7-7500U CPU @ 2.70 GHz 2.90 GHz  

**SOFTWARE**    
• Windows 8 64-bit, Java SE8 (jdk1.8.0_251, jre8)  
