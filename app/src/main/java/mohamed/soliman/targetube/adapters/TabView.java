package mohamed.soliman.targetube.adapters;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class TabView extends FrameLayout {
    public TabView(Context c) {
        super(c);

        View indicator = new View(c);
        //Might even wanna go for 0 here?
        LayoutParams params = new FrameLayout.LayoutParams(1, 1);
        indicator.setLayoutParams(params);

        addView(indicator);
    }
}