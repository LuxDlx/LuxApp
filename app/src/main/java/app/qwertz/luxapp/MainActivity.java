package app.qwertz.luxapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://sillysoft.net/lux");

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                webView.loadUrl("https://sillysoft.net/lux");
            } else if (itemId == R.id.rankings) {
                webView.loadUrl("https://sillysoft.net/lux/rankings/");
            } else if (itemId == R.id.forum) {
                webView.loadUrl("https://sillysoft.net/forums/");
            } else if (itemId == R.id.menu) {
                showMenuBottomSheet();
            }
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String url = data.getStringExtra("url");
            webView.loadUrl(url);
        }
    }

    private void showMenuBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.menu_bottom_sheet, null);

        // Set up click listeners
        view.findViewById(R.id.activeGames).setOnClickListener(v -> {
            webView.loadUrl("https://sillysoft.net/lux/active.php");
            bottomSheetDialog.dismiss();
        });

        view.findViewById(R.id.mostCommonMaps).setOnClickListener(v -> {
            webView.loadUrl("https://sillysoft.net/lux/rankings/mapcount.php");
            bottomSheetDialog.dismiss();
        });

        view.findViewById(R.id.mostGames).setOnClickListener(v -> {
            webView.loadUrl("https://sillysoft.net/lux/rankings/mostgames/");
            bottomSheetDialog.dismiss();
        });

        view.findViewById(R.id.versus).setOnClickListener(v -> {
            webView.loadUrl("https://sillysoft.net/lux/rankings/versus.php");
            bottomSheetDialog.dismiss();
        });
        view.findViewById(R.id.hosts).setOnClickListener(v -> {
            webView.loadUrl("https://sillysoft.net/lux/rankings/hosts/");
            bottomSheetDialog.dismiss();
        });

        view.findViewById(R.id.qLuxTags).setOnClickListener(v -> {
            webView.loadUrl("http://qwertz-exe.com:1234/");
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}
