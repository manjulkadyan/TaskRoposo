package com.manjul.roposo;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.manjul.roposo.Volley.VolleySingleton;

import java.util.List;

/**
 * Created by rnf-35 on 10/5/16.
 */
public class RoposoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int CARD = 0, FEEDS = 1;
    private List<DataRoposo> data;
    int position=-1;

        public class ViewHolderFeed extends RecyclerView.ViewHolder {

            public TextView  year;
            private TextView url;
            public NetworkImageView avatar;

            TextView description,verb,title;

            public TextView getYear() {
                return year;
            }

            public TextView getUrl() {
                return url;
            }

            public NetworkImageView getAvatar() {
                return avatar;
            }

            public TextView getDescription() {
                return description;
            }

            public TextView getVerb() {
                return verb;
            }

            public TextView getTitle() {
                return title;
            }

            public ViewHolderFeed(View view) {
                super(view);
                year = (TextView) view.findViewById(R.id.usernamefeeds);
                url= (TextView) view.findViewById(R.id.urlfeeds);
                description= (TextView) view.findViewById(R.id.descriptionfeeds);
                verb= (TextView) view.findViewById(R.id.verbfeeds);
                avatar = (NetworkImageView)view.findViewById(R.id.twitter_avatarfeeds);
                title= (TextView) view.findViewById(R.id.titlefeeds);

            }
        }
    public class ViewHolderCard extends RecyclerView.ViewHolder {

        NetworkImageView circle;

        public NetworkImageView getCircle() {
            return circle;
        }

        public TextView getUsernames() {
            return usernames;
        }

        public TextView getFolowers() {
            return Folowers;
        }

        public TextView getFollowings() {
            return followings;
        }

        public Button getFollowerher() {
            return Followerher;
        }

        TextView usernames,Folowers,followings;
        Button Followerher;

        public ViewHolderCard(View view) {
            super(view);
            usernames = (TextView) view.findViewById(R.id.usernames);
            Folowers= (TextView) view.findViewById(R.id.Folowers);
            followings= (TextView) view.findViewById(R.id.followings);
            circle = (NetworkImageView)view.findViewById(R.id.circle);
            Followerher= (Button) view.findViewById(R.id.Followerher);
        }

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  year;
        private TextView url;
        public NetworkImageView avatar;

        TextView description,verb,title;

        public MyViewHolder(View view) {
            super(view);
            year = (TextView) view.findViewById(R.id.usernamefeed);
            url= (TextView) view.findViewById(R.id.urlfeed);
            description= (TextView) view.findViewById(R.id.descriptionfeed);
            verb= (TextView) view.findViewById(R.id.verbfeed);
            avatar = (NetworkImageView)view.findViewById(R.id.twitter_avatarfeed);
            avatar.setDefaultImageResId(R.drawable.loading);
            title= (TextView) view.findViewById(R.id.titlefeed);

        }
    }


    public RoposoAdapter(List<DataRoposo> moviesList) {
        this.data = moviesList;
    }

    @Override
    public int getItemViewType(int position) {
        this.position=position;
        if(position==0||position==1)
        {
            return this.CARD;
        }
        else return this.FEEDS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DataRoposo movie = data.get(position);
//        if(!movie.getUsername().equalsIgnoreCase(""))
//        {
//            viewType =CARD;
//        }
//        else viewType =FEEDS;
        switch (viewType) {
            case CARD:
                View v1 = inflater.inflate(R.layout.layout_viewholder1, parent, false);
                viewHolder = new ViewHolderCard(v1);
                break;
            case FEEDS:
                View v2 = inflater.inflate(R.layout.layout_viewholder2, parent, false);
                viewHolder = new ViewHolderFeed(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        DataRoposo movie = data.get(position);
        RequestQueue mRequestQueue;
        ImageLoader mImageLoader;
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });

        switch (holder.getItemViewType()) {
            case CARD:
                ((ViewHolderCard)holder).usernames.setText(movie.getUsername());
                ((ViewHolderCard)holder).followings.setText(movie.getFollowing());
                ((ViewHolderCard)holder).circle.setImageUrl(movie.getImage(), mImageLoader);
                ((ViewHolderCard) holder).circle.setDefaultImageResId(R.drawable.loading);
                ((ViewHolderCard) holder).Followerher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((ViewHolderCard) holder).Followerher.setVisibility(View.INVISIBLE);
                    }
                });

                break;
            case FEEDS:
                ((ViewHolderFeed) holder).description.setText("Description :"+movie.getDescription());
                ((ViewHolderFeed) holder).verb.setText(movie.getVerb());
                ((ViewHolderFeed) holder).title.setText("Title :"+movie.getTitle());
                ((ViewHolderFeed) holder).avatar.setImageUrl(movie.getSi(), mImageLoader);
                ((ViewHolderFeed) holder).avatar.setDefaultImageResId(R.drawable.loading);

                break;
        }
    }
//
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        DataRoposo movie = data.get(position);
//        holder.year.setText(movie.getUsername());
////        holder.url.setText(movie.getUrl());
//        if(movie.getDescription()==null||movie.getDescription()=="")
//        {
//            holder.description.setText("None");
//        }
//        else
//        holder.description.setText(movie.getDescription());
//        holder.verb.setText(movie.getVerb());
//        holder.title.setText(movie.getTitle());
//        RequestQueue mRequestQueue;
//        ImageLoader mImageLoader;
//        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
//        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
//            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
//            public void putBitmap(String url, Bitmap bitmap) {
//                mCache.put(url, bitmap);
//            }
//            public Bitmap getBitmap(String url) {
//                return mCache.get(url);
//            }
//        });
//        holder.avatar.setImageUrl(movie.getSi(), mImageLoader);
//
//    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

