package com.terlici.dragndroplist;

import android.widget.ListAdapter;

public interface DragNDropAdapter extends ListAdapter, DragNDropListView.OnItemDragNDropListener {
	public int getDragHandler();
}
