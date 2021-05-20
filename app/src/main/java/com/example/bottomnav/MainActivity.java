package com.example.bottomnav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import static com.example.bottomnav.R.drawable.ic_baseline_about;
import static com.example.bottomnav.R.drawable.ic_baseline_home;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation bottomnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomnav = findViewById(R.id.bottomnav);
        //Add menu item
        bottomnav.add(new MeowBottomNavigation.Model(1, ic_baseline_about));
        bottomnav.add(new MeowBottomNavigation.Model(2, ic_baseline_home));
        bottomnav.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_person));

        bottomnav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                //Initalize fragments
                Fragment fragment = null;

                //check condition

                switch (item.getId()) {
                    case 1:
                        fragment = new aboutfragment();
                        break;

                    case 2:
                        fragment = new homefragment();
                        break;

                    case 3:
                        fragment = new profilefragment();
                        break;


                }

                loadFragment (fragment);
            }
        });

//set about count
      //  bottomnav.setCount(1, String.valueOf(10));
       // Set Home fragment initally selected
        bottomnav.show(2,true);

        bottomnav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "You Clicked" + item.getId(), Toast.LENGTH_SHORT).show();

            }
        });
bottomnav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
    @Override
    public void onReselectItem(MeowBottomNavigation.Model item) {
        Toast.makeText(MainActivity.this, "You Reselected" + item.getId(), Toast.LENGTH_SHORT).show();
    }
});
    }

    private void loadFragment(Fragment fragment){

        //REplace fragemnts
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout,fragment)
                .commit();
    }
}