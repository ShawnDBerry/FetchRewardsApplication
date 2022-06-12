# FetchRewardsApplication
makes a retrofit network request to  https://fetch-hiring.s3.amazonaws.com/hiring.json on kotlin
coroutines Dispatchers.IO displaying the data in a RecyclerView to the MainActivity via 
View Binding updating the MutableLiveData list or displaying the error in a toast on the
Dispatchers.Main.

The recycler view implements data binding binding the item_layout views to the Item model.

Producing the ItemViewModel via ViewModelFactory retrieving the data from the itemsRepository.
performing a group sort and filter operations on the list before serving the data on the 
main thread.

side notes:
kotlin functions I wanted to use to manipulate the list but wasn't able.

response.body?.groupBy's not working for some weird reason but sortby() does the same operations 
response.body?.filter's  not working for some weird reason but removeAll() does the same operations
