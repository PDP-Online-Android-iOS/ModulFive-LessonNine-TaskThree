package dev.ogabek.java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dev.ogabek.java.R;
import dev.ogabek.java.adapter.FeedAdapter;
import dev.ogabek.java.model.Feed;
import dev.ogabek.java.model.Photos;
import dev.ogabek.java.model.Post;
import dev.ogabek.java.model.Story;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new FeedAdapter(this, getAllFeeds()));
    }

    private ArrayList<Feed> getAllFeeds() {
        ArrayList<Story> stories = new ArrayList<>();

        stories.add(new Story());
        stories.add(new Story(R.drawable.aziz, "Azizbek", R.drawable.aziz));
        stories.add(new Story(R.drawable.bogibek, "Bogibek Matyaqubov", R.drawable.bogibek));
        stories.add(new Story(R.drawable.elmurod, "Elmurod Nazirov", R.drawable.elmurod));
        stories.add(new Story(R.drawable.jabbor, "Jabbor", R.drawable.jabbor));
        stories.add(new Story(R.drawable.jonibek, "Jonibek Xolmonov", R.drawable.jonibek));
        stories.add(new Story(R.drawable.ogabekdev, "Ogabek Matyakubov", R.drawable.ogabekdev));
        stories.add(new Story(R.drawable.shakhriyor, "Shakhriyor", R.drawable.shakhriyor));
        stories.add(new Story(R.drawable.yahyo, "Yahyo Mahmudiy", R.drawable.yahyo));

        ArrayList<Feed> feeds = new ArrayList<>();

        //Head
        feeds.add(new Feed());

        //Story
        feeds.add(new Feed(stories));

        //PhotoList
        List<Photos> photos = new ArrayList<>();
        photos.add(new Photos(R.drawable.post_4));
        photos.add(new Photos(R.drawable.post_2));
        photos.add(new Photos(R.drawable.post_3));
        photos.add(new Photos(R.drawable.post_4));
        photos.add(new Photos(R.drawable.post_2));
        photos.add(new Photos(R.drawable.post_3));
        photos.add(new Photos(R.drawable.post_3));
        photos.add(new Photos(R.drawable.post_3));

        //Post
        feeds.add(new Feed(new Post(R.drawable.aziz, "Azizbek", R.drawable.post_1, false)));
        feeds.add(new Feed(new Post(R.drawable.bogibek, "Bogibek Matyaqubov", R.drawable.post_2, false)));
        feeds.add(new Feed(new Post(R.drawable.elmurod, "Elmurod Nazirov", R.drawable.post_3, false)));
        feeds.add(new Feed(new Post(R.drawable.jabbor, "Jabbor", R.drawable.jabbor, true)));
        feeds.add(new Feed(new Post(R.drawable.jonibek, "Jonibek Xolmonov", R.drawable.post_1, false)));
        feeds.add(new Feed(new Post(R.drawable.ogabekdev, "Ogabek Matyakubov", R.drawable.ogabekdev, true)));
        feeds.add(new Feed(new Post(R.drawable.shakhriyor, "Shakhriyor", R.drawable.post_3, false)));
        feeds.add(new Feed(new Post(R.drawable.yahyo, "Yahyo Mahmudiy", R.drawable.post_4, false)));
        feeds.add(new Feed(new Post(R.drawable.aziz, "Azizbek", R.drawable.aziz, true)));
        feeds.add(new Feed(new Post(R.drawable.bogibek, "Bogibek Matyaqubov", R.drawable.post_2, false)));
        feeds.add(new Feed(new Post(R.drawable.elmurod, "Elmurod Nazirov", R.drawable.elmurod, true)));
        feeds.add(new Feed(new Post(R.drawable.jabbor, "Jabbor", photos)));
        feeds.add(new Feed(new Post(R.drawable.jonibek, "Jonibek Xolmonov", R.drawable.post_1, false)));
        feeds.add(new Feed(new Post(R.drawable.ogabekdev, "Ogabek Matyakubov", R.drawable.post_2, false)));
        feeds.add(new Feed(new Post(R.drawable.shakhriyor, "Shakhriyor", R.drawable.post_3, false)));
        feeds.add(new Feed(new Post(R.drawable.yahyo, "Yahyo Mahmudiy", R.drawable.yahyo, true)));
        feeds.add(new Feed(new Post(R.drawable.aziz, "Azizbek", R.drawable.post_1, false)));
        feeds.add(new Feed(new Post(R.drawable.bogibek, "Bogibek Matyaqubov", R.drawable.post_2, false)));
        feeds.add(new Feed(new Post(R.drawable.elmurod, "Elmurod Nazirov", R.drawable.elmurod, true)));
        feeds.add(new Feed(new Post(R.drawable.jabbor, "Jabbor", R.drawable.post_4, false)));
        feeds.add(new Feed(new Post(R.drawable.jonibek, "Jonibek Xolmonov", R.drawable.post_1, false)));
        feeds.add(new Feed(new Post(R.drawable.ogabekdev, "Ogabek Matyakubov", R.drawable.post_2, false)));
        feeds.add(new Feed(new Post(R.drawable.shakhriyor, "Shakhriyor", R.drawable.shakhriyor, true)));
        feeds.add(new Feed(new Post(R.drawable.yahyo, "Yahyo Mahmudiy", R.drawable.post_4, false)));

        return feeds;

    }

}