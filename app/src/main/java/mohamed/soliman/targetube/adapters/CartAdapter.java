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
import mohamed.soliman.targetube.model.CartLineUiList;
import mohamed.soliman.targetube.utility.RetrofitClientInstance;
import mohamed.soliman.targetube.utility.TradeService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CustomViewHolder> {

    private List<CartLineUiList> dataList;
    private Context context;
    TradeService service;


    public CartAdapter(Context context, List<CartLineUiList> dataList){
        this.context = context;
        this.dataList = dataList;
        service = RetrofitClientInstance.getRetrofitInstance().create(TradeService.class);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {


        holder.txtTitle.setText(dataList.get(position).getProductName());
        holder.quantity.setText(dataList.get(position).getQuantity()+"");

        Picasso.get().load(dataList.get(position).getImageLink()).into(holder.coverImage);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int quan = Integer.parseInt(holder.quantity.getText().toString())+1;
                holder.quantity.setText(String.valueOf(quan));

                SharedPreferences sh
                        = context.getSharedPreferences("MySharedPref",
                        MODE_PRIVATE);
                String jwt = sh.getString("jwt","");
                if(!jwt.isEmpty()) {
                    CartLineUiList product = dataList.get(position);

                    HashMap<String,String> SendData =new HashMap<>();
                    SendData.put("productId",product.getProductId()+"");
                    SendData.put("quantity",quan+"");


                    Call<ResponseBody> call = service.editCart(SendData,"Bearer "+jwt);

                    call.enqueue(new Callback<ResponseBody>() {

                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Toast.makeText(context, "Element Edited", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });



                }
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Integer.parseInt(holder.quantity.getText().toString())>1)
                {
                    final int quan = Integer.parseInt(holder.quantity.getText().toString())-1;
                    holder.quantity.setText(String.valueOf(quan));

                    SharedPreferences sh
                            = context.getSharedPreferences("MySharedPref",
                            MODE_PRIVATE);
                    String jwt = sh.getString("jwt","");
                    if(!jwt.isEmpty()) {
                        CartLineUiList product = dataList.get(position);

                        HashMap<String,String> SendData =new HashMap<>();
                        SendData.put("productId",product.getProductId()+"");
                        SendData.put("quantity",quan+"");


                        Call<ResponseBody> call = service.editCart(SendData,"Bearer "+jwt);

                        call.enqueue(new Callback<ResponseBody>() {

                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                Toast.makeText(context, "Element Edited", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                            }
                        });



                    }


                }

            }
        });





        holder.addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartLineUiList product = dataList.get(position);
                SharedPreferences sh
                        = context.getSharedPreferences("MySharedPref",
                        MODE_PRIVATE);
                String jwt = sh.getString("jwt","");
                if(!jwt.isEmpty()) {

                    HashMap<String,String> SendData =new HashMap<>();
                    SendData.put("productId",product.getProductId()+"");


                    Call<ResponseBody> call = service.deleteCart(SendData,"Bearer "+jwt);

                    call.enqueue(new Callback<ResponseBody>() {

                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Toast.makeText(context, "Element deleted", Toast.LENGTH_SHORT).show();
                            dataList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,dataList.size());

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




