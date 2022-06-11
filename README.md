# FetchRewardsApplication
makes a retrofit network request to  https://fetch-hiring.s3.amazonaws.com/hiring.json on kotlin coroutines Dispatchers.IO displaying the data in 
a RecyclerView updating the list with MutableLiveData. I have the Item cards using CardView and View Binding 

Inside ItemsViewModel.kt after retrieving the data i peform a group sort and filter operations on the list before serving the data on the main thread

side notes:
kotlin functions i wanted to use to manipulate the list but wasn't able.

response.body?.groupBy's not working for some weird reason but sortby() does the same operations 
response.body?.filter's  not working for some weird reason but removeAll() does the same operations
