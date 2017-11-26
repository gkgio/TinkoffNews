package com.gig.georgy.tinkoffnews.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.gig.georgy.tinkoffnews.R;
import com.gig.georgy.tinkoffnews.common.enums.SnackBarType;
import com.gig.georgy.tinkoffnews.di.components.TinkoffNewsAppComponent;

/**
 * Created by georgy on 26.11.2017.
 * GIG
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(TinkoffNewsApp.get(this).getAppComponent());
    }

    public void showSnackBar(View view, int message, @SnackBarType int type) {

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setAlpha(0.95f);
        snackBarView.setBackgroundResource(type == SnackBarType.ERROR ?
                R.drawable.toast_error_bg : R.drawable.toast_info_bg);
        TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setCompoundDrawablesWithIntrinsicBounds(type == SnackBarType.ERROR ?
                R.drawable.ic_report_problem : R.drawable.ic_info_outline, 0, 0, 0);
        textView.setCompoundDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.snackBar_icon_padding));
        snackbar.show();
    }

    protected abstract void setupComponent(TinkoffNewsAppComponent appComponent);
}

