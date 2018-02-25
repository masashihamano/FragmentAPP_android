package misao.edu.fragmentapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by masashihamano on 2018/02/23.
 */

public class DetailFragment extends Fragment{

    public DetailFragment(){}

    public static DetailFragment newInstance(int position){
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle(  );
        args.putInt( "position",position );
        detailFragment.setArguments( args );
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View detailView = inflater.inflate( R.layout.view_detail, container,false );
        ((TextView)detailView.findViewById( R.id.detailText )).setText( News.Details[getArguments().getInt( "position" )] );
        return detailView;
    }
}
