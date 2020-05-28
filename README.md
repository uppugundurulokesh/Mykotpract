# Mykotpract - Google 30 days kotlin 
### This is a small application for display recipes list using kotlin

# Concepts include for developing kotlin app
* DataBinding-basics
* Coroutines
* Recyclerview
* Connect to Internet
* Layouts

# DataBinding:
> The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically. 
> To create an object that contains a reference to each view. This object, called a Binding object, can be used by your whole app. This technique is called data binding.

**DataBinding concept include in app**
> Databinding concept include for get the views into kotlin file without using findViewById and set the data into views

1. Enable dataBinding in build.gradle(module app) at android block before closing tag

       dataBinding{
        enabled=true
       }
       
 2. Add <layout></layout> as the outermost tag around the <LinearLayout> in xml files
  
        <layout>
         <androidx.constraintlayout.widget.ConstraintLayout ... >
         ...
         </androidx.constraintlayout.widget.ConstraintLayout>
        </layout>
        
 3. Fix the code indentation and cut the namespace declarations from the <LinearLayout> and paste them into the <layout> tag. Your opening <layout> tag should look as shown below, and the <LinearLayout> tag should only contain view properties.
  
        <layout xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:app="http://schemas.android.com/apk/res-auto"
          xmlns:tools="http://schemas.android.com/tools">
            <androidx.constraintlayout.widget.ConstraintLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                ------
                tools:context=".MainActivity">

             <Button
              android:id="@+id/button_Click"
              android:layout_width="339dp"
              android:layout_height="72dp"
             ------/>
          </androidx.constraintlayout.widget.ConstraintLayout>
        </layout>
        
  4. Create a Databinding object into main activity file:
  
         private lateinit var binding: ActivityMainBinding
         
  5. In onCreate(), replace the setContentView() call with the following line of code using from DataBindingUtilClass. This setContentView() function also takes care of some data binding setup for the views.
  
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
         
  6. In onCreate(), replace the code that uses findViewById() to find the button with code that references the button in the binding object.
  
         binding.buttonClick.setOnClickListener {                  //buttonClick is the button view id
            startActivity(Intent(this,Second::class.java))
            }
            
  7. By using binding object we can set the data. From my app I implement below code for details page.
  
         Picasso.get().load(i).into(binding.dimg);    // here dimg is the imageview id 
         binding.dname.text=n                         // dname  and ddes is the textview id
         binding.ddes.text=d
   
# Coroutines:
> Coroutines are the way to handle long-running tasks elegantly and efficiently. Kotlin coroutines let you convert callback-based code to sequential code.

***Coroutines concept include my app***
1. Add the coroutines dependancies in build.gradle(module app)
    
       //coroutines depend
       implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5'
       implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5'
       
2. Declare a one function in onCreate() in my second activity
 
       onCreate(){
            callCoroutines()
       }
3. Implement the callCoroutines() using below code:
   
       private fun callCoroutines() {
        CoroutineScope(Dispatchers.IO).launch {
            getrecepiData()
        }
        }
        
* Dispatcher: The dispatcher sends off coroutines to run on various threads. For example, Dispatcher.Main runs tasks on the main thread, and Dispatcher.IO offloads blocking I/O tasks to a shared pool of threads.

* Scope: A coroutine's scope defines the context in which the coroutine runs.

5. Implement the getrecepieData() for internet connect and get the json data

       suspend fun getrecepiData() {

        val url = URL(url)
        val httpsURLConnection : HttpsURLConnection = url.openConnection() as HttpsURLConnection
        val inputStream : InputStream = httpsURLConnection.inputStream
        val text = inputStream.bufferedReader().use(BufferedReader::readText)
        withContext(Dispatchers.Main){

            val root=JSONObject(text);
            val jarry=root.getJSONArray("categories")
            for( i in 0..jarry.length()-1){

                val po=jarry.getJSONObject(i);
                -----
                -----
                // get the json data and set to list objects

               }
            }
          }
          
# Recyclerview: 
> Display the large amount of data in list format. By using recyclerview we can display the data into linear format,horizontal format, grid format and straged grid format.

***Implement Reyclerview concept in my app***

* Add the recyclerview dependencies
* Add the recyclerview layout in xml file
* Get the recyclerview reference to second.kt file
* Data 
* Adapter Class
        - onCreateViewHolder : set the row design of the view display to user
        - onBindViewHolder   : bind the data into views 
        - getItemCount       : return the list size
* set this addapter class object to recyclerview
* set the layout manager to recyclerview

# Connect to Internet:

* Give the internet permission in manifest file
* Getting Data from Internet 
       * Here I use the meals api for get the data from internet
       * By using coroutines concept I did the api integration
       * Do the json parsing and set the data to lists
* Load and display the data
       * Set the list into recyclerview

# Layouts:
> Layouts is a example for viewgroups. collection of views is knows as view group.Layouts contains collection of views
> My app contans Constraint layout,Linear Layout




  
   
