package com.manjul.roposo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.manjul.roposo.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

public class MainActivity extends AppCompatActivity {

//    private List<DataRoposo> movieList = new ArrayList<>();
    private List<DataRoposo>list=new ArrayList<>();
    private RecyclerView recyclerView;
    private RoposoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        new VolleySingleton(this.getApplicationContext(),5);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new RoposoAdapter(list);
      //  List<Object> obj = movieList;
//        cAdapter = new ComplexRecyclerViewAdapter(movieList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DataRoposo movie = list.get(position);
                WebView mWebview = new WebView(MainActivity.this);
                mWebview.loadUrl(list.get(position).getUrl());
                setContentView(mWebview);
//                Toast.makeText(getApplicationContext(), movie.getAbout() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

//        String s=readFromFile();
        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            JSONObject obj = new JSONObject(loadJSONFromRaw());
            String json=new String(loadJSONFromRaw());
            JSONArray obj = new JSONArray(json);
//            JSONArray m_jArry = obj.getJSONArray("formules");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int j = 0; j < obj.length(); j++) {
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
                String createdOn= jo_inside.optString("createdOn");
                String description = jo_inside.optString("description");
                String verb = jo_inside.optString("verb");
                String db = jo_inside.optString("db");
                String si = jo_inside.optString("si");
                String type = jo_inside.optString("type");
                String title = jo_inside.optString("title");
                String like_flag = jo_inside.optString("like_flag");
                String likes_count = jo_inside.optString("likes_count");
                String comment_count = jo_inside.optString("comment_count");
                DataRoposo movie = new DataRoposo(about,url_value,username,following,followers,image,url,handle,is_following,createdOn,description,verb,db,si,type,title,like_flag,likes_count,comment_count);
                list.add(movie);

            }
            int a=5;
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int a=5;
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

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}