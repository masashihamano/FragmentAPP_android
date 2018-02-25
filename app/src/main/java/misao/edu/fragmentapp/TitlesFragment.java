package misao.edu.fragmentapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by masashihamano on 2018/02/23.
 */

public class TitlesFragment  extends ListFragment{

    public final static String EXTRA_POSITION ="misao.edu.fragmentapp.POSITION";

    private OnTitleSelectedListener listener;
    private boolean isDualPane;
    private int savedPosition;

    public TitlesFragment() {}

    public interface OnTitleSelectedListener {
        public void onTitleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnTitleSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "  must implement onTitleSelected");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                News.Titles
        ));

        View detailFrame = getActivity().findViewById(R.id.detailFrame);
        isDualPane = detailFrame != null && detailFrame.getVisibility() == View.VISIBLE;

        if (isDualPane){
            if(savedInstanceState != null) {
                savedPosition = savedInstanceState.getInt( "saved_position" );
            }else{
                savedPosition = 0;
            }
            listener.onTitleSelected( savedPosition );
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putInt( "saved_position", savedPosition );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        savedPosition = position;
        if (isDualPane) {
            listener.onTitleSelected(position);
        } else {
            Intent intent = new Intent(getActivity(), SubActivity.class);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        }
    }
}
