package com.hannesdorfmann.mosby.sample.mail.details;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.InjectView;
import com.hannesdorfmann.mosby.MosbyActivity;
import com.hannesdorfmann.mosby.sample.mail.R;
import com.hannesdorfmann.mosby.sample.mail.model.mail.Mail;
import com.hannesdorfmann.mosby.sample.mail.model.mail.Person;

/**
 * @author Hannes Dorfmann
 */
public class DetailsActivity extends MosbyActivity {

  public static final String KEY_MAIL = "com.hannesdorfmann.mosby.MosbyActivity.MAIL";

  @InjectView(R.id.toolbar) Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mail_details);

    toolbar.setNavigationIcon(getBackArrowDrawable());
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (Build.VERSION.SDK_INT >= 21) {
          finishAfterTransition();
        } else {
          finish();
        }
      }
    });

    if (savedInstanceState == null) {
      Mail mail = getIntent().getParcelableExtra(KEY_MAIL);
      Person sender = mail.getSender();

      DetailsFragment fragment =
          new DetailsFragmentBuilder(mail.getId(), sender.getEmail(), sender.getName(),
              sender.getImageRes(), mail.isStarred()).build();

      getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragmentContainer, fragment)
          .commit();
    }
  }

  private Drawable getBackArrowDrawable() {

    if (Build.VERSION.SDK_INT >= 21) {
      return getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha, getTheme());
    } else {
      return getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
    }
  }
}