# Bridge-International-Demo-Project
Simple Android app with inifinite scrolling and built using Material Design, MVVM, Coroutines and Android Architecture Components. 

This is a simple application for fetching and displaying pupil list, and it load data in chunks (5 pupil with each api call) and has infite scrolling feature as well as offline data feature. You can also add new pupil (works both online /offline ) if you added any Pupil in offline mode come back to the app later when you have internet and press the sync from options menu.

There are simple test cases written for 
1. Model.
2. Obeserver.

# Technology Stack
   	Kotlin
    MVVM
    Coroutines
    Retrofit2
    Koin
	Android Architecture Components
 	 -DataBiniding
	 -LiveData
	 -Room
	Recycler View 
    Juni4 
    Mockito
 
	
# UI 
	Material Design Principles
	Supports Dark Mode and Light Mode 
# Reference
API :: https://androidtechnicaltestapi-test.bridgeinternationalacademies.com/swagger/index.html
UI  :: https://material.io/
# App Screenshots


# LightMode : 

![Pupil List Screen](/images/pupillist.jpeg)

![Add New Pupil](/images/addnewpupil.jpeg)

![Add New Pupil Error](/images/addnewpupilerror.jpeg)

# How to use the app :

The app works in both offline and online more. But due to limited time constraint there might be cases when you are using the app in offline mode and you suddenly comes online, then you have to exit the app and come back to it to load new data. 

# Future Goals : 
While this is just the MVP of the actual prodcut, given more time there are few advances which this app would need to give its users a flawless and memorable experience. 

1. Pull to Refresh -> The pupil list needs pull to refresh listview. This is specially important when the user was offline while he was using the app( he was being displayed the cached data from DB) but if he suddeenly comes online, currently user have to kill the app and reload the app to load new data from server. But with pull to refresh the user can be saved lots of trouble. 

2. Test Cases: Due to limited time I have not written many test cases, but certainly it makes sense to make this code more roburst test cases should cover more areas.
  a.)For -> Models(Pupil). 
  b.)For -> ViewModel( LiveData Obeservers for API Result, DB cached Data result)
  c.)For -> any business related logic it might contain in future. 

