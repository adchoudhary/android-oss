package com.kickstarter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.view.View;
import android.widget.TextView;

import com.kickstarter.R;
import com.kickstarter.libs.BaseActivity;
import com.kickstarter.ui.views.KickstarterWebView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DisplayWebViewActivity extends BaseActivity {
  @InjectView(R.id.close_text_view) TextView closeTextView;
  @InjectView(R.id.generic_webview) KickstarterWebView webView;

  public static final int RIGHT_BAR_BUTTON_NONE = 0;
  public static final int RIGHT_BAR_BUTTON_CLOSE = 1;

  @IntDef({RIGHT_BAR_BUTTON_NONE, RIGHT_BAR_BUTTON_CLOSE})
  @Retention(RetentionPolicy.SOURCE)
  public @interface RightBarButton {}

  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.webview_layout);
    ButterKnife.inject(this);

    final Intent intent = getIntent();
    final String url = intent.getExtras().getString(getString(R.string.intent_url));
    final int rightBarButton = intent.getExtras().getInt(getString(R.string.intent_right_bar_button), RIGHT_BAR_BUTTON_NONE);

    switch (rightBarButton) {
      case RIGHT_BAR_BUTTON_CLOSE:
        closeTextView.setVisibility(View.VISIBLE);
        break;
      case RIGHT_BAR_BUTTON_NONE:
        closeTextView.setVisibility(View.GONE);
        break;
    }

    webView.loadUrl(url);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overrideExitTransition();
  }

  public void closeTextViewOnClick(final View view) {
    finish();
    overrideExitTransition();
  }

  private void overrideExitTransition() {
    overridePendingTransition(R.anim.fade_in, R.anim.slide_out_bottom);
  }
}