package com.area51.semana8_3;

import java.io.File;
import java.util.List;

import com.area51.libs.AlbumStorageDirFactory;
import com.area51.libs.BitmapManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static final int TAKE_PICTURE = 1;
	private static final int SELECT_PICTURE = 2;
	private static String ALBUM_NAME = "area51_group10";
	private static String BITMAP_STORAGE_KEY = "bitmap";
	private static Bitmap mImageBitmap;

	private ImageView picture;
	private Button btnCamara;
	private Button btnAlbum;
	private String photoPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageBitmap = null;
		btnCamara = (Button) findViewById(R.id.btnCamara);
		btnAlbum = (Button) findViewById(R.id.btnAlbum);
		picture = (ImageView) findViewById(R.id.picture);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		btnCamara.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isIntentAvailable(getApplicationContext(),
						MediaStore.ACTION_IMAGE_CAPTURE)) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					File f = null;
					try {
						f = AlbumStorageDirFactory.setUpPhotoFile(ALBUM_NAME);
						photoPath = f.getAbsolutePath();
						intent.putExtra(MediaStore.EXTRA_OUTPUT,
								Uri.fromFile(f));
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						f = null;
						photoPath = null;
					}
					startActivityForResult(intent, TAKE_PICTURE);
				}
			}
		});

		btnAlbum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(
						Intent.createChooser(intent, "SElect Picture"),
						SELECT_PICTURE);
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		mImageBitmap = savedInstanceState.getParcelable(BITMAP_STORAGE_KEY);
	}

	private void addPictureToGallery() {
		Intent intent = new Intent(
				"android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		File f = new File(photoPath);
		Uri contentUri = Uri.fromFile(f);
		intent.setData(contentUri);
		this.sendBroadcast(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case TAKE_PICTURE:
				if (photoPath != null) {
					mImageBitmap = BitmapManager.setPic(picture, photoPath);
					picture.setImageBitmap(mImageBitmap);
					addPictureToGallery();
					photoPath = null;
				}
				break;
			case SELECT_PICTURE:
				Uri selectImageUri = data.getData();
				photoPath = AlbumStorageDirFactory.getImageFromGalleryPath(
						this, selectImageUri);
				picture.setImageURI(selectImageUri);
				break;
			}
		}
	}

	private boolean isIntentAvailable(Context context, String action) {
		PackageManager packageManager = context.getPackageManager();
		Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
