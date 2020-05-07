package mohamed.soliman.targetube.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import mohamed.soliman.targetube.R;

public class AboutUsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViews();

        return inflater.inflate(R.layout.aboutus, container, false);

    }
}