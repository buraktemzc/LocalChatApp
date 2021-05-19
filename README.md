# LocalChatApp

<img align="left" src="https://github.com/buraktemzc/LocalChatApp/raw/master/assets/login_screen.png" width="200">
<img align="center" src="https://github.com/buraktemzc/LocalChatApp/raw/master/assets/chat_screen.png" width="200">

In this project, I aim to make a chat application running on an android device without having any real-time end system. To ensure database synchronization, sample JSON data is retrieved from
the remote server when user enters nickname. However, the database is updated with new data. User session information (only nickname) is stored in Shared Preferences to check each time
the application is opened.

What did I use in this project?
- I used multi module Clean Architecture with MVVM using Dagger2-Hilt, Coroutines, LiveData, Flow, Room, Retrofit, ViewBinding, Gson and Glide. 
On the other hand, I used JUnit, MockWebServer and Truth libraries to verify the accuracy of the request. 

#### The app has below modules:
1. **data**: This module contains entities, datasources, DI modules, mappers, repository implementation and endpoint interface.
2. **domain**: This module contains database entities, usecases and repository for sending requests / DB operations to fill entities and serve data to presentation layer.
3. **presentation**: All UI components and ViewModels are located in this module.
4. **common**: Common classes used in many parts of the application are under this module.
5. **buildSrc**: It has kotlin dsl to organize and control dependencies at one point.
6. **app**: This module has Application class, app icon, themes for app.

#### Important for clean build:
- Please change or create "user_name" in sdk.dir line and "your_url" in BASE_URL line with real values in local.properties under project root folder. (If you are not successful, you can contact me.)

```
sdk.dir=/Users/user_name/Library/Android/sdk
BASE_URL="your_url"
```