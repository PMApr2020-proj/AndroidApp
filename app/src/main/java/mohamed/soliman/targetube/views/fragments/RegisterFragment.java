package mohamed.soliman.targetube.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import mohamed.soliman.targetube.R;
import mohamed.soliman.targetube.model.SignUpResponse;
import mohamed.soliman.targetube.utility.RetrofitClientInstance;
import mohamed.soliman.targetube.utility.TradeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    Button signUp;
    EditText username,password,email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViews();
        View view = inflater.inflate(R.layout.register, container, false);

        signUp = (Button)view.findViewById(R.id.signup);
        username = (EditText) view.findViewById(R.id.username);
        password = (EditText)view.findViewById(R.id.password);
        email = (EditText)view.findViewById(R.id.email);
        signUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!username.getText().toString().isEmpty()&&!password.getText().toString().isEmpty()&&!email.getText().toString().isEmpty())
                {
                    TradeService service = RetrofitClientInstance.getRetrofitInstance().create(TradeService.class);
                    HashMap<String,String> SendData =new HashMap<>();
                    SendData.put("username",username.getText().toString());
                    SendData.put("password",password.getText().toString());
                    SendData.put("email",email.getText().toString());

                    Call<SignUpResponse> call = service.signUp(SendData);

                    call.enqueue(new Callback<SignUpResponse>() {

                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                            SignUpResponse signInResponse = response.body();
                            if(signInResponse == null)
                            { Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(getContext(), "You Signed Up Successfully Sign In now", Toast.LENGTH_SHORT).show();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction ft = fragmentManager.beginTransaction();
                                if (fragmentManager.getBackStackEntryCount() > 0) {
                                    FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
                                    fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                }

                                ft.add(R.id.output, new LogInFragment());
                                ft.addToBackStack(null);
                                ft.commit();



                                }

                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {

                            Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });





                }
            }
        });

        return view;

    }
}