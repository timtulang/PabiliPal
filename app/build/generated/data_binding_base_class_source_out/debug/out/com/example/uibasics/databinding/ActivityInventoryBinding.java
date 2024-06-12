// Generated by view binder compiler. Do not edit!
package com.example.uibasics.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.uibasics.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityInventoryBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView appLogo;

  @NonNull
  public final GridView availableItems;

  @NonNull
  public final ImageButton imageButton;

  @NonNull
  public final ImageButton imageButton9;

  @NonNull
  public final RelativeLayout main;

  @NonNull
  public final TextView screenTitle;

  @NonNull
  public final SearchView searchView;

  private ActivityInventoryBinding(@NonNull RelativeLayout rootView, @NonNull ImageView appLogo,
      @NonNull GridView availableItems, @NonNull ImageButton imageButton,
      @NonNull ImageButton imageButton9, @NonNull RelativeLayout main,
      @NonNull TextView screenTitle, @NonNull SearchView searchView) {
    this.rootView = rootView;
    this.appLogo = appLogo;
    this.availableItems = availableItems;
    this.imageButton = imageButton;
    this.imageButton9 = imageButton9;
    this.main = main;
    this.screenTitle = screenTitle;
    this.searchView = searchView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityInventoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityInventoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_inventory, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityInventoryBinding bind(@NonNull View rootView) {
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

      id = R.id.imageButton;
      ImageButton imageButton = ViewBindings.findChildViewById(rootView, id);
      if (imageButton == null) {
        break missingId;
      }

      id = R.id.imageButton9;
      ImageButton imageButton9 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton9 == null) {
        break missingId;
      }

      RelativeLayout main = (RelativeLayout) rootView;

      id = R.id.screenTitle;
      TextView screenTitle = ViewBindings.findChildViewById(rootView, id);
      if (screenTitle == null) {
        break missingId;
      }

      id = R.id.searchView;
      SearchView searchView = ViewBindings.findChildViewById(rootView, id);
      if (searchView == null) {
        break missingId;
      }

      return new ActivityInventoryBinding((RelativeLayout) rootView, appLogo, availableItems,
          imageButton, imageButton9, main, screenTitle, searchView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
