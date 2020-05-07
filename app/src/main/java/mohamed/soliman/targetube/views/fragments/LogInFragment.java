package mohamed.soliman.targetube.views.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashMap;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import mohamed.soliman.targetube.R;
import mohamed.soliman.targetube.model.SignInResponse;
import mohamed.soliman.targetube.utility.RetrofitClientInstance;
import mohamed.soliman.targetube.utility.TradeService;
import mohamed.soliman.targetube.views.activites.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class LogInFragment extends Fragment {
    Button signIn;
    EditText username,password;
    ProgressDialog progressDoalog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViews();
View view = inflater.inflate(R.layout.login, container, false);
signIn = (Button)view.findViewById(R.id.signup);
username = (EditText) view.findViewById(R.id.username);
password = (EditText)view.findViewById(R.id.password);

signIn.setOnClickListener(new View.OnClickListener(){

    @Override
    public void onClick(View v) {


        if(!username.getText().toString().isEmpty()&&!password.getText().toString().isEmpty())
        {
            progressDoalog = new ProgressDialog(getContext());
            progressDoalog.setMessage("Loading....");
            progressDoalog.show();

            TradeService service = RetrofitClientInstance.getRetrofitInstance().create(TradeService.class);
            HashMap<String,String> SendData =new HashMap<>();
            SendData.put("username",username.getText().toString());
            SendData.put("password",password.getText().toString());

                Call<SignInResponse> call = service.signIn(SendData);

                call.enqueue(new Callback<SignInResponse>() {

                    @Override
                    public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                        SignInResponse signInResponse = response.body();
                        if(signInResponse == null)
                        { Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {

                            SharedPreferences sharedPreferences
                                    = getContext().getSharedPreferences("MySharedPref",
                                    MODE_PRIVATE);
                            SharedPreferences.Editor myEdit
                                    = sharedPreferences.edit();
                            myEdit.putString(
                                    "jwt",
                                    signInResponse.getJwt());
                            myEdit.commit();
                            ((MainActivity) getActivity()).hideItemSignIn();


                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            if (fragmentManager.getBackStackEntryCount() > 0) {
                                FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
                                fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            }

                            ft.add(R.id.output, new HomeFragment());
                            ft.addToBackStack(null);
                            ft.commit();
                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                            progressDoalog.dismiss();

                        }

                    }

                    @Override
                    public void onFailure(Call<SignInResponse> call, Throwable t) {
                        progressDoalog.dismiss();


                        Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });





        }
        else
        {
            Toast.makeText(getContext(), "Please fill your data...Please try later!", Toast.LENGTH_SHORT).show();

        }
    }
});




        return view;

    }

}