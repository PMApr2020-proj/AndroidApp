package mohamed.soliman.targetube.adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import mohamed.soliman.targetube.R;
import mohamed.soliman.targetube.model.Product;
import mohamed.soliman.targetube.model.SignInResponse;
import mohamed.soliman.targetube.utility.RetrofitClientInstance;
import mohamed.soliman.targetube.utility.TradeService;
import mohamed.soliman.targetube.views.activites.MainActivity;
import mohamed.soliman.targetube.views.fragments.HomeFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Product> dataList;
    private Context context;


    public CustomAdapter(Context context,List<Product> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_recycle_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {


        holder.txtTitle.setText(dataList.get(position).getName());

        Picasso.get().load(dataList.get(position).getImageLink()).into(holder.coverImage);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan = Integer.parseInt(holder.quantity.getText().toString());
                holder.quantity.setText(String.valueOf(quan+1));
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(holder.quantity.getText().toString())>1)
                {
                    int quan = Integer.parseInt(holder.quantity.getText().toString())-1;

                    holder.quantity.setText(String.valueOf(quan));

                }

            }
        });

        holder.addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = dataList.get(position);
                SharedPreferences sh
                        = context.getSharedPreferences("MySharedPref",
                        MODE_PRIVATE);
                String jwt = sh.getString("jwt","");
                if(!jwt.isEmpty()) {

                    TradeService service = RetrofitClientInstance.getRetrofitInstance().create(TradeService.class);
                    HashMap<String,String> SendData =new HashMap<>();
                    SendData.put("categoryId",product.getCategory().getCategoryId()+"");
                    SendData.put("categoryName",product.getCategory().getName());
                    SendData.put("imageLink",product.getImageLink());
                    SendData.put("price",product.getPrice()+"");
                    SendData.put("productDesc",product.getDescription());
                    SendData.put("productId",product.getProductID()+"");
                    SendData.put("productName",product.getName());
                    SendData.put("quantity",holder.quantity.getText().toString());
                    SendData.put("sellerId",product.getSellerId()+"");

                    Call<ResponseBody> call = service.addToCart(SendData,"Bearer "+jwt);

                    call.enqueue(new Callback<ResponseBody>() {

                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Toast.makeText(context, "Element Added", Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });



                }
            }
        });



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

        txtTitle = mView.findViewById(R.id.textView5);
        coverImage = mView.findViewById(R.id.imageView3);
        minus = mView.findViewById(R.id.minus);
        add = mView.findViewById(R.id.addQuan);
        addtoCart = mView.findViewById(R.id.addto);
        quantity = mView.findViewById(R.id.quan);





    }

}
}




