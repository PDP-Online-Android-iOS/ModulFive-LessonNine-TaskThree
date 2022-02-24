package dev.ogabek.java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import dev.ogabek.java.R;
import dev.ogabek.java.model.Feed;
import dev.ogabek.java.model.Story;

public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CREATE_STORY_VIEW = 0;
    private static final int TYPE_SIMPLE_STORY_VIEW = 1;

    private final List<Story> stories;

    public StoryAdapter(List<Story> stories) {
        this.stories = stories;
    }

    @Override
    public int getItemViewType(int position) {

        Story story = stories.get(position);

        if (story.isCreateStory()) {
            return TYPE_CREATE_STORY_VIEW;
        } else {
            return TYPE_SIMPLE_STORY_VIEW;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_CREATE_STORY_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_story_view, parent, false);
            return new CreateStoryViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story_view, parent, false);
            return new StoryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Story story = stories.get(position);

        if (holder instanceof StoryViewHolder) {
            ((StoryViewHolder) holder).iv_story_photo.setImageResource(story.getPhoto());
            ((StoryViewHolder) holder).iv_story_profile.setImageResource(story.getProfile());
            ((StoryViewHolder) holder).tv_story_full_name.setText(story.getFullName());
        }
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    private static class StoryViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView iv_story_photo, iv_story_profile;
        TextView tv_story_full_name;

        public StoryViewHolder(View view) {
            super(view);

            iv_story_photo = view.findViewById(R.id.iv_story_photo);
            iv_story_profile = view.findViewById(R.id.iv_story_profile);
            tv_story_full_name = view.findViewById(R.id.tv_story_full_name);

        }
    }

    private static class CreateStoryViewHolder extends RecyclerView.ViewHolder {
        public CreateStoryViewHolder(View view) {
            super(view);
        }
    }
}
