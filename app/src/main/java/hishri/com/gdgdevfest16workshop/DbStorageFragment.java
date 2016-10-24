package hishri.com.gdgdevfest16workshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class DbStorageFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Button addBtn ;
    EditText editText ;
SQLiteDatabase sql ;
    public DbStorageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TaDBManager db = new TaDBManager(getActivity().getApplicationContext());
        sql = db.getWritableDatabase();
        View frag = inflater.inflate(R.layout.fragment_db_storage, container, false);
        addBtn = (Button) frag.findViewById(R.id.addBtn);
        editText = (EditText) frag.findViewById(R.id.addRec);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                addRecord();
            }
        });


        return frag;
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

    public void addRecord()
    {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TaDBManager.FeedEntry.COLUMN_NAME_TITLE, editText.getText().toString());


// Insert the new row, returning the primary key value of the new row
        long newRowId = sql.insert("testDB", null, values);
values.clear();
        if ( newRowId > 0 ) Log.i("taha", "inserted");
    }
}
