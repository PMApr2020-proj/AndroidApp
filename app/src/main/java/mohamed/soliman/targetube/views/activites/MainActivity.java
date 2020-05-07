package mohamed.soliman.targetube.views.activites;


import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import mohamed.soliman.targetube.R;
import mohamed.soliman.targetube.adapters.TabAdapter;
import mohamed.soliman.targetube.views.fragments.AboutUsFragment;
import mohamed.soliman.targetube.views.fragments.CartFragment;
import mohamed.soliman.targetube.views.fragments.HomeFragment;
import mohamed.soliman.targetube.views.fragments.LogInFragment;
import mohamed.soliman.targetube.views.fragments.OrderFragment;

import androidx.fragment.app.FragmentTransaction;

import androidx.fragment.app.FragmentManager;
import mohamed.soliman.targetube.views.fragments.RegisterFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         navigationView = (NavigationView) findViewById(R.id.nav_view);
        hideItemWithoutSignIn();

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue_color));
        tabLayout.setTabTextColors(getResources().getColor(R.color.black_color), getResources().getColor(R.color.blue_color));

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new CartFragment(), "My Cart");
        adapter.addFragment(new OrderFragment(), "My orders");
         int[] tabIcons = {
                R.drawable.home,
                R.drawable.cart,
                R.drawable.order
        };
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

        tabLayout.addOnTabSelectedListener(new
                                                   TabLayout.OnTabSelectedListener() {
                                                       @Override
                                                       public void onTabSelected(TabLayout.Tab tab) {


                                                           tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue_color));
                                                           tabLayout.setTabTextColors(getResources().getColor(R.color.black_color), getResources().getColor(R.color.blue_color));
                                                           viewPager.setVisibility(View.VISIBLE);
                                                           FragmentManager fragmentManager = getSupportFragmentManager();
                                                           FragmentTransaction ft = fragmentManager.beginTransaction();
                                                           if (fragmentManager.getBackStackEntryCount() > 0) {
                                                               FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
                                                               fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                                               ft.commit();
                                                           }



                                                       }

                                                       @Override
                                                       public void onTabUnselected(TabLayout.Tab tab) {

                                                       }

                                                       @Override
                                                       public void onTabReselected(TabLayout.Tab tab) {


                                                           tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue_color));
                                                           tabLayout.setTabTextColors(getResources().getColor(R.color.black_color), getResources().getColor(R.color.blue_color));
                                                           viewPager.setVisibility(View.VISIBLE);
                                                           FragmentManager fragmentManager = getSupportFragmentManager();
                                                           FragmentTransaction ft = fragmentManager.beginTransaction();
                                                           if (fragmentManager.getBackStackEntryCount() > 0) {
                                                               FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
                                                               fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                                               ft.commit();

                                                           }


                                                       }
                                                   });

                                                       final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        Button menuIcon = (Button) findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        viewPager.setVisibility(View.GONE);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.background_color));
        tabLayout.setTabTextColors(getResources().getColor(R.color.black_color), getResources().getColor(R.color.black_color));
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.signin) {
            replaceFragment(new LogInFragment());

        } else if (id == R.id.aboutUs) {
            replaceFragment(new AboutUsFragment());

        } else if (id == R.id.signup) {

            replaceFragment(new RegisterFragment());

        }

        else if (id == R.id.signOut) {

hideItemWithoutSignIn();
            SharedPreferences sharedPreferences
                    = getSharedPreferences("MySharedPref",
                    MODE_PRIVATE);
            SharedPreferences.Editor myEdit
                    = sharedPreferences.edit();
            myEdit.putString(
                    "jwt",
                    "");
            myEdit.commit();

            replaceFragment(new HomeFragment());

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void replaceFragment(Fragment newFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        ft.add(R.id.output, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    public void hideItemSignIn()
    {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.signin).setVisible(false);
        nav_Menu.findItem(R.id.signup).setVisible(false);
        nav_Menu.findItem(R.id.signOut).setVisible(true);


    }
    public void hideItemWithoutSignIn()
    {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.signOut).setVisible(false);
        nav_Menu.findItem(R.id.signin).setVisible(true);
        nav_Menu.findItem(R.id.signup).setVisible(true);

    }




}
