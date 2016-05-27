        package com.manjul.roposo.Volley;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.support.v4.util.LruCache;

        import com.android.volley.RequestQueue;
        import com.android.volley.toolbox.ImageLoader;
        import com.android.volley.toolbox.Volley;

        public class VolleySingleton {

        private static VolleySingleton mInstance = null;
        private RequestQueue mRequestQueue;
        private ImageLoader mImageLoader;

        public VolleySingleton(Context context){
                mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
                mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
                private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
                            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
                }
                public Bitmap getBitmap(String url) {
                return mCache.get(url);
                }
                });
        }

                public VolleySingleton(Context context, int a) {
                        mInstance=new VolleySingleton(context);
                }

                public static VolleySingleton getInstance(){

        return mInstance;
        }

        public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
        }

        public ImageLoader getImageLoader(){
        return this.mImageLoader;
        }

        }