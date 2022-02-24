package dev.ogabek.kotlin.adapter

import android.content.Context
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import dev.ogabek.kotlin.R
import dev.ogabek.kotlin.model.Feed
import dev.ogabek.kotlin.model.Photos

class FeedAdapter(var context: Context, var feeds: List<Feed>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val feed: Feed = feeds[position]
        return when {
            feed.isHeader -> {
                TYPE_HEADER_VIEW
            }
            feed.stories.size > 0 -> {
                TYPE_STORY_VIEW
            }
            feed.post!!.isNewProfile -> {
                TYPE_NEW_PROFILE_POST_VIEW
            }
            feed.post!!.photos.isNotEmpty() -> {
                TYPE_PHOTOS_VIEW
            }
            else -> {
                TYPE_POST_VIEW
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER_VIEW) {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_header, parent, false)
            FeedHeaderViewHolder(view)
        } else if (viewType == TYPE_STORY_VIEW) {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false)
            FeedStoryViewHolder(context, view)
        } else if (viewType == TYPE_NEW_PROFILE_POST_VIEW) {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_post_change_profile, parent, false)
            FeedChangeProfilePostViewHolder(view)
        } else if (viewType == TYPE_PHOTOS_VIEW) {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_post_all, parent, false)
            FeedAllPhotosPostViewHolder(view)
        } else {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post, parent, false)
            FeedPostViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed: Feed = feeds[position]
        if (holder is FeedStoryViewHolder) {
            val recyclerView = holder.recyclerView
            recyclerView.adapter = StoryAdapter(feed.stories)
        }
        if (holder is FeedPostViewHolder) {
            holder.iv_post_photo.setImageResource(feed.post!!.photo)
            holder.iv_post_profile.setImageResource(feed.post!!.profile)
            holder.tv_post_full_name.text = feed.post!!.fullName
        }
        if (holder is FeedChangeProfilePostViewHolder) {
            val newString = Html.fromHtml(
                "<b>" + feed.post!!.fullName
                    .toString() + "</b> Uploaded his profile picture"
            )
            holder.fullName.text = newString
            holder.profile.setImageResource(feed.post!!.profile)
            holder.newPhoto.setImageResource(feed.post!!.photo)
        }
        if (holder is FeedAllPhotosPostViewHolder) {
            holder.iv_post_profile.setImageResource(feed.post!!.profile)
            holder.tv_post_full_name.text = feed.post!!.fullName
            val photo: List<Photos> = feed.post!!.photos
            when (photo.size) {
                1 -> {
                    holder.iv_1.setImageResource(photo[0].photo)
                    holder.ll_2.visibility = View.GONE
                    holder.lll_1.visibility = View.GONE
                }
                2 -> {
                    holder.iv_1.setImageResource(photo[0].photo)
                    holder.iv_2.setImageResource(photo[1].photo)
                    holder.lll_1.visibility = View.GONE
                }
                3 -> {
                    holder.iv_1.setImageResource(photo[0].photo)
                    holder.iv_2.setImageResource(photo[1].photo)
                    holder.iv_3.setImageResource(photo[2].photo)
                    holder.ll_4.visibility = View.GONE
                    holder.ll_5.visibility = View.GONE
                    holder.lll_1.visibility = View.VISIBLE
                    holder.ll_text.visibility = View.GONE
                }
                4 -> {
                    holder.iv_1.setImageResource(photo[0].photo)
                    holder.iv_2.setImageResource(photo[1].photo)
                    holder.iv_3.setImageResource(photo[2].photo)
                    holder.iv_4.setImageResource(photo[3].photo)
                    holder.ll_5.visibility = View.GONE
                    holder.lll_1.visibility = View.VISIBLE
                    holder.ll_text.visibility = View.GONE
                }
                5 -> {
                    holder.iv_1.setImageResource(photo[0].photo)
                    holder.iv_2.setImageResource(photo[1].photo)
                    holder.iv_3.setImageResource(photo[2].photo)
                    holder.iv_4.setImageResource(photo[3].photo)
                    holder.iv_5.setImageResource(photo[4].photo)
                    holder.lll_1.visibility = View.VISIBLE
                    holder.ll_text.visibility = View.GONE
                }
                else -> {
                    holder.iv_1.setImageResource(photo[0].photo)
                    holder.iv_2.setImageResource(photo[1].photo)
                    holder.iv_3.setImageResource(photo[2].photo)
                    holder.iv_4.setImageResource(photo[3].photo)
                    holder.iv_5.setImageResource(photo[4].photo)
                    holder.lll_1.visibility = View.VISIBLE
                    holder.ll_text.visibility = View.VISIBLE
                    holder.tv_how_much.text = "+ " + (photo.size - 5)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    private class FeedHeaderViewHolder(view: View?) : RecyclerView.ViewHolder(view!!)
    private class FeedPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_post_profile: ShapeableImageView = view.findViewById(R.id.iv_post_profile)
        var tv_post_full_name: TextView = view.findViewById(R.id.tv_post_full_name)
        var iv_post_photo: ImageView = view.findViewById(R.id.iv_post_photo)
    }

    private class FeedStoryViewHolder(context: Context?, view: View) :
        RecyclerView.ViewHolder(view) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewStory)

        init {
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private class FeedChangeProfilePostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fullName: TextView = view.findViewById(R.id.tv_post_new_full_name)
        var profile: ShapeableImageView = view.findViewById(R.id.iv_post_new_profile_profile)
        var newPhoto: ShapeableImageView = view.findViewById(R.id.iv_new_photo)
    }

    private class FeedAllPhotosPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_post_profile: ShapeableImageView = view.findViewById(R.id.iv_post_profile)
        var tv_post_full_name: TextView = view.findViewById(R.id.tv_post_full_name)
        var iv_1: ImageView = view.findViewById(R.id.iv_1)
        var iv_2: ImageView = view.findViewById(R.id.iv_2)
        var iv_3: ImageView = view.findViewById(R.id.iv_3)
        var iv_4: ImageView = view.findViewById(R.id.iv_4)
        var iv_5: ImageView = view.findViewById(R.id.iv_5)
        var ll_2: LinearLayout = view.findViewById(R.id.ll_2)
        var ll_4: LinearLayout = view.findViewById(R.id.ll_4)
        var ll_5: LinearLayout = view.findViewById(R.id.ll_5)
        var ll_text: LinearLayout = view.findViewById(R.id.ll_text)
        var lll_1: LinearLayout = view.findViewById(R.id.lll_1)
        var tv_how_much: TextView = view.findViewById(R.id.tv_how_much)
    }

    companion object {
        private const val TYPE_HEADER_VIEW = 0
        private const val TYPE_STORY_VIEW = 1
        private const val TYPE_POST_VIEW = 2
        private const val TYPE_NEW_PROFILE_POST_VIEW = 3
        private const val TYPE_PHOTOS_VIEW = 4
    }

}