Jetpack compose is a modern toolkit for building ui components. When it comes to android it makes life easier for android devs for creating android ui screens with less code compared to xml

Here i will try to demonstrate how i build an android app using jetpack compose.

Jetpack allows us to preview app design just like xml within a composable function withot a paramenter.

Colors, themes, typography and matrial3 componets are easy to use here.

Here is the project Structure:

App --

   models(for holding data)----contains data and sealed classes
   
   navigation( composable navigation function to navigate through screen using routing)

   network (Contains Retrofit, Api Interface)
   
   repo ( For Api calling using coroutine function)
   
   viewmodels (hold ui and date states, fetch date from repository and handles errors)
   
   MainActivity
   
Applications that follows jetpack compose having only single activity and screens(No fragment in compose)


