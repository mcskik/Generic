package com.example.km.genericapp.utilities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.example.km.genericapp.R;

/**
 * Snackbar helper.
 */
public final class SnackbarHelper {

    public static void showSnackbar(Context context, View view, String message) {
        final ForegroundColorSpan snackbarSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.secondary_text_fg_color));
        SpannableStringBuilder snackbarText = new SpannableStringBuilder(message);
        snackbarText.setSpan(snackbarSpan, 0, snackbarText.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        Snackbar snackbar = Snackbar.make(view, snackbarText, Snackbar.LENGTH_LONG).setAction(context.getString(R.string.fab_action), null);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        snackbar.show();
    }
}
