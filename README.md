# DragNDropListView
DragNDropListView is a direct replacement for the stock Android ListView. If you know how to use ListView, you already know how to use DragNDropListView. All you have to do is replace ListView and SimpleCursorAdapter/SimpleAdapter with DragNDropListView and its adapters.

# Usage
DragNDropListView is an Android Library project. If you use Eclipse do the following.
* Clone `git clone git://github.com/terlici/DragNDropList.git`
* Import it in Eclipse
* Add DragNDropList as a library to your Android project

### Layout

A common layout for list view is the following:

````xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <ListView
        android:id="@id/android:list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />

</LinearLayout>
````

Just replace `ListView` with `com.terlici.dragndroplist.DragNDropListView`:

````xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.terlici.dragndroplist.DragNDropListView
        android:id="@id/android:list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />

</LinearLayout>
````

### Row Layout
In DragNDropListView each item has a drag handler, which the users touch to drag the item around.

Here is a common layout for each row. It contains an image of a star and some text.
The user can reorder the items by touching the star of an item and dragging it around.

````xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="80px"
    android:orientation="horizontal"
    android:gravity="center_vertical"
    >

    <ImageView
        android:id="@+id/handler"
        android:layout_width="60px"
        android:layout_height="60px"
        android:src="@android:drawable/btn_star_big_on"
        android:layout_marginLeft="8px"
        />

    <TextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Large Text"
        android:textAppearance="?android:attr/textAppearanceLarge" />

</LinearLayout>
````

### Loading DragNDropListView

This is the usual way to load a ListView:

````java
ListView list = (ListView)findViewById(android.R.id.list);

SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
                        R.layout.row,
                        cursor,
                        new String[]{"text"},
                        new int[]{R.id.text},
                        0);

list.setListAdapter(adapter);
````

When using DragNDropListView just replace `ListView` with `DragNDropListView`,
`SimpleCursorAdapter` with `DragNDropCursorAdapter` and set the id of the drag
handler as the last parameter of the adapter. In the row layout above this will
be `R.id.handler`. Last, instead of `setListAdapter` use `setDragNDropAdapter`.

````java
DragNDropListView list = (DragNDropListView)findViewById(android.R.id.list);

DragNDropCursorAdapter adapter = new DragNDropCursorAdapter(context,
                           R.layout.row,
                           cursor,
                           new String[]{"text"},
                           new int[]{R.id.text},
                           R.id.handler);

list.setDragNDropAdapter(adapter);
````

You are done! As you see there is very little to change to your existing code.

### Events
DragNDropListView provides an event listener for drag and drop.

````java
list.setOnItemDragNDropListener(...)
````

The interface for this listener is:

````java
public interface OnItemDragNDropListener {
    // Called when the item begins dragging.
    public void onItemDrag(DragNDropListView parent, View view, int position, long id);

    // Called after the item is dropped in place
    public void onItemDrop(DragNDropListView parent, View view, int startPosition, int endPosition, long id);
}
````

This event is useful when storing the reordered items.

### Adapters

DragNDropList comes with two adapters. The first one is `DragNDropCursorAdapter`
which is a direct replacement for `SimpleCursorAdapter`. The second is `DragNDropSimpleAdapter`
which is a replacement for `SimpleAdapter`.

# Apps that use DragNDropList
DragNDropList was originally created for our apps [Tasks](http://play.google.com/store/apps/details?id=com.vnsndev.tasks) and
[Tasks Pro](http://play.google.com/store/apps/details?id=com.vnsndev.taskspro). Download one of them to see it in action.

If other apps use it, please let us know and we will include them here.

# Contribution
If you want to contribute, there are two more projects. [DragNDropListApp](https://github.com/terlici/DragNDropListApp) implements a basic demo of the DragNDropList. [DragNDropListAppTest](https://github.com/terlici/DragNDropListAppTest) runs tests on the DragNDropListApp and contains all the tests for the DragNDropList.

If you want to contribute, please fork also those two projects and add your tests to DragNDropListAppTest.

# License

`DragNDropList`'s code uses the Apache license, see our `LICENSE` file.