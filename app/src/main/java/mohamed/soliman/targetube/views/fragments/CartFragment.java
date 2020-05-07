package mohamed.soliman.targetube.views.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import android.app.ProgressDialog;

import java.util.ArrayList;
import java.util.List;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import mohamed.soliman.targetube.adapters.CartAdapter;
import mohamed.soliman.targetube.model.CartLineUi;
import mohamed.soliman.targetube.model.CartLineUiList;
import mohamed.soliman.targetube.utility.TradeService;
import mohamed.soliman.targetube.utility.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.fragment.app.Fragment;
import mohamed.soliman.targetube.R;

import static android.content.Context.MODE_PRIVATE;

public class CartFragment extends Fragment {
    TextView message2,empty_message;
    RecyclerView recyclerView ;
    Button checkout;
     public  CartAdapter adapter;
    ProgressDialog progressDoalog;
    Integer user_id;
List<CartLineUiList>cartLineUiLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cart, container, false);
        recyclerView = view.findViewById(R.id.rec_view);
        message2 = view.findViewById(R.id.message2);
        empty_message = view.findViewById(R.id.empty_message);
        checkout = view.findViewById(R.id.checkout);
        cartLineUiLists = new ArrayList<>();

checkout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.checkout, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        Button close = (Button) popupView.findViewById(R.id.close);
        Button place = (Button) popupView.findViewById(R.id.place);

        popupWindow.setFocusable(true);
        popupWindow.update();

        popupWindow.showAsDropDown(popupView, 0, 0);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                empty_message.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                checkout.setVisibility(View.GONE);
                SharedPreferences sh
                        = getContext().getSharedPreferences("MySharedPref",
                        MODE_PRIVATE);
                String jwt = sh.getString("jwt","");
                if(!jwt.isEmpty()) {
                    TradeService service = RetrofitClientInstance.getRetrofitInstance().create(TradeService.class);

                    Call<ResponseBody> call = service.removeCart(user_id,"Bearer "+jwt);
                    call.enqueue(new Callback<ResponseBody>() {


                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            progressDoalog.dismiss();
                            Toast.makeText(getContext(), "Order Placed "+user_id, Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDoalog.dismiss();
                            Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }




            }
        });


    }
});
        return view;

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {

            cartLineUiLists.clear();
            progressDoalog = new ProgressDialog(getContext());
            progressDoalog.setMessage("Loading....");
            progressDoalog.show();
            SharedPreferences sh
                    = getContext().getSharedPreferences("MySharedPref",
                    MODE_PRIVATE);
            String jwt = sh.getString("jwt","");
            if(!jwt.isEmpty())
            {

                message2.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                checkout.setVisibility(View.VISIBLE);



                TradeService service = RetrofitClientInstance.getRetrofitInstance().create(TradeService.class);

                Call<CartLineUi> call = service.getAllCart("Bearer "+jwt);
                call.enqueue(new Callback<CartLineUi>() {

                    @Override
                    public void onResponse(Call<CartLineUi> call, Response<CartLineUi> response) {
                        progressDoalog.dismiss();
                        user_id = response.body().getUserId();

                        if (response.body().getCartLineUiList().size() == 0) {
                            empty_message.setVisibility(View.VISIBLE);

                        } else {
                            cartLineUiLists =response.body().getCartLineUiList();
                            generateDataList(cartLineUiLists);

                            empty_message.setVisibility(View.GONE);

                        }

                    }

                    @Override
                    public void onFailure(Call<CartLineUi> call, Throwable t) {
                        progressDoalog.dismiss();
                        Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });





            }
            else
            {
                progressDoalog.dismiss();

                message2.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                checkout.setVisibility(View.GONE);
                empty_message.setVisibility(View.GONE);

            }
        }
        else {

        }

    }
    private void generateDataList(List<CartLineUiList> photoList) {

        adapter = new CartAdapter(getContext(),photoList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



    }

}