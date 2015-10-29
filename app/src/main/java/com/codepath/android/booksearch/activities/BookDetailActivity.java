package com.codepath.android.booksearch.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.lib.MediaStoreHelper;
import com.codepath.android.booksearch.models.Book;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {

    private ShareActionProvider miShareAction;

    private static class ViewHolder {
        private ImageView ivBookCover;
        private TextView tvTitle;
        private TextView tvAuthor;
    }

    private ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        // Fetch views
        viewHolder = new ViewHolder();

        viewHolder.ivBookCover = (ImageView) findViewById(R.id.ivBookCover);
        viewHolder.tvTitle = (TextView) findViewById(R.id.tvTitle);
        viewHolder.tvAuthor = (TextView) findViewById(R.id.tvAuthor);

        // Extract book object from intent extras
        Intent intent = getIntent();
        Book book = intent.getParcelableExtra("book");

        // set title
        getSupportActionBar().setTitle(book.getTitle());

        // Use book object to populate data into views
        viewHolder.tvTitle.setText(book.getTitle());
        viewHolder.tvAuthor.setText(book.getAuthor());
        Picasso.with(this).load(Uri.parse(book.getLargeCoverUrl())).placeholder(R.drawable.ic_nocover).into(viewHolder.ivBookCover, new Callback() {
            @Override
            public void onSuccess() {
                setupShareIntent();
            }

            @Override
            public void onError() {
            }
        });
    }

    private void setupShareIntent() {
        ImageView ivImage = viewHolder.ivBookCover;
        Uri uri = MediaStoreHelper.getImage(ivImage, getContentResolver());
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/*");
        miShareAction.setShareIntent(shareIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);

        MenuItem shareButton = menu.findItem(R.id.action_share);
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(shareButton);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
