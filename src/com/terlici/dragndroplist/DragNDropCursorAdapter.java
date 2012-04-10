package com.terlici.dragndroplist;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;

public class DragNDropCursorAdapter extends SimpleCursorAdapter implements DragNDropAdapter {
	int mPosition[];
	int mHandler;
	
	public DragNDropCursorAdapter(Context context, int layout, Cursor cursor, String[] from, int[] to, int handler) {
		super(context, layout, cursor, from, to, 0);
		
		mHandler = handler;
		setup();
	}
	
	@Override
	public Cursor swapCursor(Cursor c) {
		Cursor cursor = super.swapCursor(c);
		
		mPosition = null;
		setup();
		
		return cursor;
	}
	
	private void setup() {
		Cursor c = getCursor();
		
		if (c == null || !c.moveToFirst()) return;
		
		mPosition = new int[c.getCount()];
		
		for (int i = 0; i < mPosition.length; ++i) mPosition[i] = i;
	}
	
	@Override
	public View getDropDownView(int position, View view, ViewGroup group) {
		return super.getDropDownView(mPosition[position], view, group);
	}
	
	@Override
	public Object getItem(int position) {
		return super.getItem(mPosition[position]);
	}
	
	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(mPosition[position]);
	}
	
	@Override
	public long getItemId(int position) {
		return super.getItemId(mPosition[position]);
	}
	
	@Override
	public View getView(int position, View view, ViewGroup group) {
		return super.getView(mPosition[position], view, group);
	}
	
	@Override
	public boolean isEnabled(int position) {
		return super.isEnabled(mPosition[position]);
	}

	@Override
	public void onItemDrag(DragNDropListView parent, View view, int position, long id) {
		
	}

	@Override
	public void onItemDrop(DragNDropListView parent, View view, int startPosition, int endPosition, long id) {
		int position = mPosition[startPosition];
		
		if (startPosition < endPosition)
			for(int i = startPosition; i < endPosition; ++i)
				mPosition[i] = mPosition[i + 1];
		else if (endPosition < startPosition)
			for(int i = startPosition; i > endPosition; --i)
				mPosition[i] = mPosition[i - 1];
		
		mPosition[endPosition] = position;
	}

	@Override
	public int getDragHandler() {
		return mHandler;
	}
	
}
