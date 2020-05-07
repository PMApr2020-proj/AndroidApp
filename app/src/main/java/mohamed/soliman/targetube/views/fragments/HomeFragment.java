package mohamed.soliman.targetube.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import mohamed.soliman.targetube.R;


import android.widget.Toast;


import android.app.ProgressDialog;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import mohamed.soliman.targetube.adapters.CustomAdapter;
import mohamed.soliman.targetube.utility.TradeService;
import mohamed.soliman.targetube.model.Product;
import mohamed.soliman.targetube.utility.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    List<Product>products;
    ProgressDialog progressDoalog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        products = new ArrayList<>();
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        TradeService tradeService = RetrofitClientInstance.getRetrofitInstance().create(TradeService.class);


        TradeService service = RetrofitClientInstance.getRetrofitInstance().create(TradeService.class);

        Call<List<Product>> call = service.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressDoalog.dismiss();
                products = response.body();
                generateDataList(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        return inflater.inflate(R.layout.home, container, false);




    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {


        }
    }

    private void generateDataList(List<Product> photoList) {
        recyclerView = getView().findViewById(R.id.rec_view);
        adapter = new CustomAdapter(getContext(),photoList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }

}