package suemar.com.br.swipe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MyActivity extends Activity {

    private ListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> nomes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nomes = new ArrayList<String>();

        setContentView(R.layout.activity_my);

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(
                Color.BLUE, Color.RED, Color.GREEN
        );

        mListView = (ListView) findViewById(R.id.listview);
        atualizarNomes();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        atualizarNomes();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                },3000);
            }
        });
    }

    private void atualizarNomes() {
        int total = nomes.size();
        for(int j = total; j <= total + 3; j++) {
            nomes.add(0, String.format("Nome %d",j));
        }

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);
        mListView.setAdapter(mAdapter);
    }

}
