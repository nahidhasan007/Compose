Jetpack compose is a modern toolkit for building ui components. When it comes to android it makes life easier for android devs for creating android ui screens with less code compared to xml.\n
Here i will try to demonstrate how i build an android app using jetpack compose.\n
Jetpack allows us to preview app design just like xml within a composable function withot a paramenter.\n
Colors, themes, typography and matrial3 componets are easy to use here.\n

Here is the project Structure:
App ----
   models(for holding data)----contains data and sealed classes\n
   navigation( composable navigation function to navigate through screen using routing)\n
   network (Contains Retrofit, Api Interface)\n
   repo ( For Api calling using coroutine function)\n
   viewmodels (hold ui and date states, fetch date from repository and handles errors)\n
   MainActivity\n
Applications that follows jetpack compose having only single activity and screens(No fragment in compose)

