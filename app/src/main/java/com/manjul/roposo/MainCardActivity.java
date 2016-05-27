package com.manjul.roposo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manjul.roposo.tindercard.DataCard;
import com.manjul.roposo.tindercard.FlingCardListener;
import com.manjul.roposo.tindercard.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainCardActivity extends AppCompatActivity implements FlingCardListener.ActionDownInterface {


    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
//    private ArrayList<DataCard> al;
    private ArrayList<DataCard> cardList= new ArrayList<>();
    private SwipeFlingAdapterView flingContainer;

    public static void removeBackground() {


        viewHolder.background.setVisibility(View.GONE);
        myAppAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_card);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            JSONObject obj = new JSONObject(loadJSONFromRaw());
            String json=new String(loadJSONFromRaw());
            JSONArray obj = new JSONArray(json);
//            JSONArray m_jArry = obj.getJSONArray("formules");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int j = 0; j < 2; j++) {
                JSONObject jo_inside = obj.getJSONObject(j);

//                Log.d("Details-->", jo_inside.getString("formule"));
                    String about = jo_inside.optString("about");
                    String url_value = jo_inside.optString("id");
                    String username = jo_inside.optString("username");
                    String followers = jo_inside.optString("followers");
                    String following = jo_inside.optString("following");
                    String image = jo_inside.optString("image");
                    String url = jo_inside.optString("url");
                    String handle = jo_inside.optString("handle");
                    String is_following = jo_inside.optString("is_following");
                    String createdOn = jo_inside.optString("createdOn");
                    DataCard cardDetails = new DataCard(about, url_value, username, following, followers, image, url, handle, is_following, createdOn);
                    cardList.add(cardDetails);

            }
            int a=5;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        al = new ArrayList<>();
//        al.add(new DataCard("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
//        al.add(new DataCard("http://switchboard.nrdc.org/blogs/dlashof/mission_impossible_4-1.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
//        al.add(new DataCard("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
//        al.add(new DataCard("http://switchboard.nrdc.org/blogs/dlashof/mission_impossible_4-1.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
//        al.add(new DataCard("http://i.ytimg.com/vi/PnxsTxV8y3g/maxresdefault.jpg", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness."));
//
        myAppAdapter = new MyAppAdapter(cardList, MainCardActivity.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                cardList.remove(0);
                myAppAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                moveToStoryBoard();

            }

            @Override
            public void onRightCardExit(Object dataObject) {

                cardList.remove(0);
                myAppAdapter.notifyDataSetChanged();
                moveToStoryBoard();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapter.notifyDataSetChanged();
            }
        });

    }

    private void moveToStoryBoard() {
        if(cardList.size()==0)
        {
            Intent i=new Intent(MainCardActivity.this,MainActivity.class);
            startActivity(i);
        }
    }

    private String loadJSONFromRaw() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.json_file);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }

        String jsonString = writer.toString();
        return jsonString;
    }
    @Override
    public void onActionDownPerform() {
        Log.e("action", "bingo");
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView about;
        public TextView aboaut, year, genre;
        private TextView following;
        private TextView followers,followersTotal;
        private ImageView image;
        private TextView url,handle,is_following,createdOn;
//        public ImageView cardImage;


    }

    public class MyAppAdapter extends BaseAdapter {


        public List<DataCard> parkingList;
        public Context context;

        private MyAppAdapter(List<DataCard> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.about = (TextView) rowView.findViewById(R.id.about);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.image = (ImageView) rowView.findViewById(R.id.cardImage);
                viewHolder.handle = (TextView) rowView.findViewById(R.id.handle);
                viewHolder.followersTotal= (TextView) rowView.findViewById(R.id.followerstotal);
                viewHolder.following= (TextView) rowView.findViewById(R.id.following);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.about.setText("About "+parkingList.get(position).getUsername()+" : "+parkingList.get(position).getAbout() + "");
            viewHolder.handle.setText(parkingList.get(position).getUsername()+ "");
            viewHolder.followersTotal.setText("Followers:"+parkingList.get(position).getFollowers()+ "");
            viewHolder.following.setText("Followingss:"+parkingList.get(position).getFollowing()+ "");

            Glide.with(MainCardActivity.this).load(parkingList.get(position).getImage()).into(viewHolder.image);

            return rowView;
        }
    }
}
