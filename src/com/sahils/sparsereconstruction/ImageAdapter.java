package com.sahils.sparsereconstruction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;

	// references to our images
	public ArrayList<Uri> mThumbBmps = new ArrayList<Uri>();

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mThumbBmps.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			// Calculation of ImageView Size - density independent.
			// maybe you should do this calculation not exactly in this method
			// but put is somewhere else.
			Resources r = Resources.getSystem();
			float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
					150, r.getDisplayMetrics());

			// if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams((int) px,
					(int) px));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}
		Uri imageUri = mThumbBmps.get(position);
		try {
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(
					mContext.getContentResolver(), imageUri);
			imageView.setImageBitmap(bitmap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageView;
	}

}