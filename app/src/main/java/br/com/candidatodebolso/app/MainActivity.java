package br.com.candidatodebolso.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import br.com.candidatodebolso.app.fragment.CandidateListFragment;
import br.com.candidatodebolso.app.fragment.HomeTestFragment;
import br.com.candidatodebolso.app.fragment.LastedNewsFragment;
import br.com.candidatodebolso.app.fragment.PollIntentionFragment;

public class MainActivity extends AppCompatActivity {

    private CandidateListFragment candidateListFragment;
    private LastedNewsFragment lastedNewsFragment;
    private HomeTestFragment homeTestFragment;
    private PollIntentionFragment pollIntentionFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_candidates:
                    replaceFragment(candidateListFragment);
                    return true;
                case R.id.navigation_news:
                    replaceFragment(lastedNewsFragment);
                    return true;
                case R.id.navigation_test:
                    replaceFragment(homeTestFragment);
                    return true;
                case R.id.navigation_poll:
                    replaceFragment(pollIntentionFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidateListFragment = new CandidateListFragment();
        lastedNewsFragment = new LastedNewsFragment();
        homeTestFragment = new HomeTestFragment();
        pollIntentionFragment = new PollIntentionFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.navigationFrameLayout, candidateListFragment).commit();
        }

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.navigationFrameLayout, fragment).commit();
    }

}
