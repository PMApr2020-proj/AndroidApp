package mohamed.soliman.targetube.views.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import mohamed.soliman.targetube.R;

import static android.content.Context.MODE_PRIVATE;


public class OrderFragment extends Fragment {
    TextView message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order, container, false);


        message = view.findViewById(R.id.message2);


        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            SharedPreferences sh
                    = getContext().getSharedPreferences("MySharedPref",
                    MODE_PRIVATE);
            String jwt = sh.getString("jwt","");
            if(!jwt.isEmpty())
            {
               message.setVisibility(View.GONE);
            }
            else
            {

            }
        }


    }
}