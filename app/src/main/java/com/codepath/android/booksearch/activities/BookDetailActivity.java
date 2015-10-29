package com.codepath.android.booksearch.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.models.Book;
import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {

    private static class ViewHolder {
        private ImageView ivBookCover;
        private TextView tvTitle;
        private TextView tvAuthor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        // Fetch views
        ViewHolder viewHolder = new ViewHolder();

        viewHolder.ivBookCover = (ImageView) findViewById(R.id.ivBookCover);
        viewHolder.tvTitle = (TextView) findViewById(R.id.tvTitle);
        viewHolder.tvAuthor = (TextView) findViewById(R.id.tvAuthor);

        // Extract book object from intent extras
        Intent intent = getIntent();
        Book book = (Book) intent.getParcelableExtra("book");

        // Use book object to populate data into views
        Picasso.with(getApplicationContext()).load(Uri.parse(book.getLargeCoverUrl())).placeholder(R.drawable.ic_nocover).into(viewHolder.ivBookCover);
        viewHolder.tvTitle.setText(book.getTitle());
        viewHolder.tvAuthor.setText(book.getAuthor());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
