package mohamed.soliman.targetube.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mohamed.soliman.targetube.R;
import mohamed.soliman.targetube.model.Product;
import mohamed.soliman.targetube.utility.RetrofitClientInstance;
import mohamed.soliman.targetube.utility.TradeService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.CustomViewHolder> {

    private List<Product> dataList;
    private Context context;


    public OrderAdapter(Context context,List<Product> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public OrderAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_element, parent, false);
        return new OrderAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView txtTitle,quantity;
        private ImageView coverImage;
        Button add,minus,addtoCart;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;


        }

    }
}




