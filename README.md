# PagingRecycler [![](https://jitpack.io/v/AKiniyalocts/PagingRecycler.svg)](https://jitpack.io/#AKiniyalocts/PagingRecycler)

A quick way to implement a paging pattern for a RecyclerView. PagingRecycler will show a "loading" view at the bottom of your RecyclerView while you are waiting for a page of results from your api call, then remove it when you are finished.
### Gradle
```gradle

  allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

		dependencies {
	        compile 'com.github.Batterii:PagingRecycler:v1.1'
	}
```
## Usage
### Adapter
```java

public class MyAdapter extends PagingAdapter{

  private List<Item> myItems;

  public MyAdapter(){
    myItems = new ArrayList<>();
  }

  @Override
    public int getPagingLayout() {
        return R.layout.paging_item;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // Do your normal view creation

    // End with this
    return super.onCreateViewHolder(parent, viewType);

  }  

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    // Call super!
    super.onBindViewHolder(holder, position);

    // Do your normal binding

  }

  @Override
  public int getItemCount() {
    // just return super here
    return super.getItemCount();
  }

  @Override
  public int getPagingItemCount() {
    // return your actual item size here
    return myItems.size();
  }

}
```

### Your Activity/Fragment etc.
```java

public class MyActivity extends AppCompatActivity implements PagingDelegate.OnPageListener{

 @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      MyAdapter adapter = new MyAdapter();

      RecyclerView mRecycler = (RecyclerView)findViewById(R.id.my_recycler);

      PagingDelegate pagingDelegate = new PagingDelegate.Builder(adapter)
                .attachTo(mRecycler)
                .listenWith(this)
                .build();

      mRecycler.setAdapter(adapter);
  }

   @Override
    public void onPage(int offset) {
        // Perform your paging request
    }

    @Override
    public void onDonePaging() {

    }

  }
```

License
--------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
