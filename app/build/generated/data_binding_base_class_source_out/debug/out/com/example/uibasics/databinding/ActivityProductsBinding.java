// Generated by view binder compiler. Do not edit!
package com.example.uibasics.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.uibasics.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProductsBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView appLogo;

  @NonNull
  public final GridView availableItems;

  @NonNull
  public final RelativeLayout main;

  @NonNull
  public final TextView screenTitle;

  @NonNull
  public final EditText searchBar;

  @NonNull
  public final GridView soldoutItems;

  private ActivityProductsBinding(@NonNull RelativeLayout rootView, @NonNull ImageView appLogo,
      @NonNull GridView availableItems, @NonNull RelativeLayout main, @NonNull TextView screenTitle,
      @NonNull EditText searchBar, @NonNull GridView soldoutItems) {
    this.rootView = rootView;
    this.appLogo = appLogo;
    this.availableItems = availableItems;
    this.main = main;
    this.screenTitle = screenTitle;
    this.searchBar = searchBar;
    this.soldoutItems = soldoutItems;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProductsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProductsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_products, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProductsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appLogo;
      ImageView appLogo = ViewBindings.findChildViewById(rootView, id);
      if (appLogo == null) {
        break missingId;
      }

      id = R.id.availableItems;
      GridView availableItems = ViewBindings.findChildViewById(rootView, id);
      if (availableItems == null) {
        break missingId;
      }

      RelativeLayout main = (RelativeLayout) rootView;

      id = R.id.screenTitle;
      TextView screenTitle = ViewBindings.findChildViewById(rootView, id);
      if (screenTitle == null) {
        break missingId;
      }

      id = R.id.searchBar;
      EditText searchBar = ViewBindings.findChildViewById(rootView, id);
      if (searchBar == null) {
        break missingId;
      }

      id = R.id.soldoutItems;
      GridView soldoutItems = ViewBindings.findChildViewById(rootView, id);
      if (soldoutItems == null) {
        break missingId;
      }

      return new ActivityProductsBinding((RelativeLayout) rootView, appLogo, availableItems, main,
          screenTitle, searchBar, soldoutItems);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}