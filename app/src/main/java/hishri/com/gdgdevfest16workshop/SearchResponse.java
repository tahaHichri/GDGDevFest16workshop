package hishri.com.gdgdevfest16workshop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SearchResponse extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;
    ArrayList<String> mDataset ;

    OkHttpClient client;
    String url = "https://jsonplaceholder.typicode.com/posts/1/comments";

    Context context ;
    public SearchResponse() {
        // Required empty public constructor
    }


    public static SearchResponse newInstance(String param1, String param2) {
        SearchResponse fragment = new SearchResponse();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
        mDataset = new ArrayList<>();

        client = new OkHttpClient();
        try {
            run();
        } catch (Exception ioe)
        {
            Log.e ( "taha", ioe.getMessage() );
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View searchFragment =  inflater.inflate(R.layout.fragment_search_response, container, false);

        mRecyclerView = (RecyclerView) searchFragment.findViewById(R.id.response_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RequestResponseAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        return searchFragment;
    }

    /**
     *
     * @throws Exception
     */
    public void run() throws Exception {

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
//                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                }
                try {
                    JSONArray jsonarray = new JSONArray(response.body().string());

                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
//                        String name = jsonobject.getString("name");
                        mDataset.add( jsonobject.getString("name") );

                        Log.d("taha", jsonobject.getString("name"));
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("taha", "data length "+mDataset.size());
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }catch(JSONException jsex)
                {
                    Log.e("taha", jsex.getMessage());
                }

            }
        });
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
