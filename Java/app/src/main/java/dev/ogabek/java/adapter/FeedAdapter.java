package dev.ogabek.java.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import dev.ogabek.java.R;
import dev.ogabek.java.model.Feed;
import dev.ogabek.java.model.Photos;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Feed> feeds;

    private static final int TYPE_HEADER_VIEW = 0;
    private static final int TYPE_STORY_VIEW = 1;
    private static final int TYPE_POST_VIEW = 2;
    private static final int TYPE_NEW_PROFILE_POST_VIEW = 3;
    private static final int TYPE_PHOTOS_VIEW = 4;

    public FeedAdapter(Context context, List<Feed> feeds) {
        this.context = context;
        this.feeds = feeds;
    }

    @Override
    public int getItemViewType(int position) {
        Feed feed = feeds.get(position);
        if (feed.isHeader()) {
            return TYPE_HEADER_VIEW;
        } else if (feed.getStories().size() > 0) {
            return TYPE_STORY_VIEW;
        } else if (feed.getPost().isNewProfile()) {
            return TYPE_NEW_PROFILE_POST_VIEW;
        } else if (feed.getPost().getPhotos().size() > 0) {
            return TYPE_PHOTOS_VIEW;
        } else {
            return TYPE_POST_VIEW;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_header, parent, false);
            return new FeedHeaderViewHolder(view);
        } else if (viewType == TYPE_STORY_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_story, parent, false);
            return new FeedStoryViewHolder(context, view);
        } else if (viewType == TYPE_NEW_PROFILE_POST_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_post_change_profile, parent, false);
            return new FeedChangeProfilePostViewHolder(view);
        } else if (viewType == TYPE_PHOTOS_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_post_all, parent, false);
            return new FeedAllPhotosPostViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_post, parent, false);
            return new FeedPostViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Feed feed = feeds.get(position);

        if (holder instanceof FeedStoryViewHolder) {
            RecyclerView recyclerView = ((FeedStoryViewHolder) holder).recyclerView;
            recyclerView.setAdapter(new StoryAdapter(feed.getStories()));
        }

        if (holder instanceof FeedPostViewHolder) {
            ((FeedPostViewHolder) holder).iv_post_photo.setImageResource(feed.getPost().getPhoto());
            ((FeedPostViewHolder) holder).iv_post_profile.setImageResource(feed.getPost().getProfile());
            ((FeedPostViewHolder) holder).tv_post_full_name.setText(feed.getPost().getFullName());
        }

        if (holder instanceof FeedChangeProfilePostViewHolder) {
            Spanned newString = Html.fromHtml("<b>" + feed.getPost().getFullName() + "</b> Uploaded his profile picture");
            ((FeedChangeProfilePostViewHolder) holder).fullName.setText(newString);
            ((FeedChangeProfilePostViewHolder) holder).profile.setImageResource(feed.getPost().getProfile());
            ((FeedChangeProfilePostViewHolder) holder).newPhoto.setImageResource(feed.getPost().getPhoto());
        }

        if (holder instanceof FeedAllPhotosPostViewHolder) {
            ((FeedAllPhotosPostViewHolder) holder).iv_post_profile.setImageResource(feed.getPost().getProfile());
            ((FeedAllPhotosPostViewHolder) holder).tv_post_full_name.setText(feed.getPost().getFullName());

            List<Photos> photo = feed.getPost().getPhotos();
            switch (feed.getPost().getPhotos().size()) {
                case 1: {
                    ((FeedAllPhotosPostViewHolder) holder).iv_1.setImageResource(photo.get(0).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).ll_2.setVisibility(View.GONE);
                    ((FeedAllPhotosPostViewHolder) holder).lll_1.setVisibility(View.GONE);
                    ((FeedAllPhotosPostViewHolder) holder).ll_text.setVisibility(View.GONE);
                    break;
                }
                case 2: {
                    ((FeedAllPhotosPostViewHolder) holder).iv_1.setImageResource(photo.get(0).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_2.setImageResource(photo.get(1).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).lll_1.setVisibility(View.GONE);
                    ((FeedAllPhotosPostViewHolder) holder).ll_text.setVisibility(View.GONE);
                    break;
                }
                case 3: {
                    ((FeedAllPhotosPostViewHolder) holder).iv_1.setImageResource(photo.get(0).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_2.setImageResource(photo.get(1).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_3.setImageResource(photo.get(2).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).ll_4.setVisibility(View.GONE);
                    ((FeedAllPhotosPostViewHolder) holder).ll_5.setVisibility(View.GONE);
                    ((FeedAllPhotosPostViewHolder) holder).lll_1.setVisibility(View.VISIBLE);
                    ((FeedAllPhotosPostViewHolder) holder).ll_text.setVisibility(View.GONE);
                    break;
                }
                case 4: {
                    ((FeedAllPhotosPostViewHolder) holder).iv_1.setImageResource(photo.get(0).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_2.setImageResource(photo.get(1).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_3.setImageResource(photo.get(2).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_4.setImageResource(photo.get(3).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).ll_5.setVisibility(View.GONE);
                    ((FeedAllPhotosPostViewHolder) holder).lll_1.setVisibility(View.VISIBLE);
                    ((FeedAllPhotosPostViewHolder) holder).ll_text.setVisibility(View.GONE);
                    break;
                }
                case 5: {
                    ((FeedAllPhotosPostViewHolder) holder).iv_1.setImageResource(photo.get(0).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_2.setImageResource(photo.get(1).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_3.setImageResource(photo.get(2).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_4.setImageResource(photo.get(3).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_5.setImageResource(photo.get(4).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).lll_1.setVisibility(View.VISIBLE);
                    ((FeedAllPhotosPostViewHolder) holder).ll_text.setVisibility(View.GONE);
                    break;
                }
                default: {
                    ((FeedAllPhotosPostViewHolder) holder).iv_1.setImageResource(photo.get(0).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_2.setImageResource(photo.get(1).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_3.setImageResource(photo.get(2).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_4.setImageResource(photo.get(3).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).iv_5.setImageResource(photo.get(4).getPhoto());
                    ((FeedAllPhotosPostViewHolder) holder).lll_1.setVisibility(View.VISIBLE);
                    ((FeedAllPhotosPostViewHolder) holder).ll_text.setVisibility(View.VISIBLE);
                    ((FeedAllPhotosPostViewHolder) holder).tv_how_much.setText("+" + (photo.size() - 5));
                    break;
                }
            }

        }

    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    private static class FeedHeaderViewHolder extends RecyclerView.ViewHolder {
        public FeedHeaderViewHolder(View view) {
            super(view);
        }
    }

    private static class FeedPostViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView iv_post_profile;
        TextView tv_post_full_name;
        ImageView iv_post_photo;

        public FeedPostViewHolder(View view) {
            super(view);

            iv_post_profile = view.findViewById(R.id.iv_post_profile);
            tv_post_full_name = view.findViewById(R.id.tv_post_full_name);
            iv_post_photo = view.findViewById(R.id.iv_post_photo);

        }
    }

    private static class FeedStoryViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public FeedStoryViewHolder(Context context, View view) {
            super(view);

            recyclerView = view.findViewById(R.id.recyclerViewStory);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        }
    }

    private static class FeedChangeProfilePostViewHolder extends RecyclerView.ViewHolder {

        TextView fullName;
        ShapeableImageView profile, newPhoto;

        public FeedChangeProfilePostViewHolder(View view) {
            super(view);

            fullName = view.findViewById(R.id.tv_post_new_full_name);
            profile = view.findViewById(R.id.iv_post_new_profile_profile);
            newPhoto = view.findViewById(R.id.iv_new_photo);

        }
    }

    private static class FeedAllPhotosPostViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView iv_post_profile;
        TextView tv_post_full_name;

        ImageView iv_1, iv_2, iv_3, iv_4, iv_5;
        LinearLayout ll_1, ll_2, ll_3, ll_4, ll_5, ll_text, lll_1;
        TextView tv_how_much;

        public FeedAllPhotosPostViewHolder(View view) {
            super(view);

            iv_post_profile = view.findViewById(R.id.iv_post_profile);
            tv_post_full_name = view.findViewById(R.id.tv_post_full_name);

            iv_1 = view.findViewById(R.id.iv_1);
            iv_2 = view.findViewById(R.id.iv_2);
            iv_3 = view.findViewById(R.id.iv_3);
            iv_4 = view.findViewById(R.id.iv_4);
            iv_5 = view.findViewById(R.id.iv_5);

            ll_1 = view.findViewById(R.id.ll_1);
            ll_2 = view.findViewById(R.id.ll_2);
            ll_3 = view.findViewById(R.id.ll_3);
            ll_4 = view.findViewById(R.id.ll_4);
            ll_5 = view.findViewById(R.id.ll_5);

            lll_1 = view.findViewById(R.id.lll_1);

            ll_text = view.findViewById(R.id.ll_text);

            tv_how_much = view.findViewById(R.id.tv_how_much);

        }
    }
}
