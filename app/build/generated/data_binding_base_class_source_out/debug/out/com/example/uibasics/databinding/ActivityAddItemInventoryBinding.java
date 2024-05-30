// Generated by view binder compiler. Do not edit!
package com.example.uibasics.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public final class ActivityAddItemInventoryBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageButton addImage;

  @NonNull
  public final EditText addStock;

  @NonNull
  public final Button addtoInventory;

  @NonNull
  public final ImageButton decrementStock;

  @NonNull
  public final ImageButton incrementStock;

  @NonNull
  public final TextView labelProdName;

  @NonNull
  public final TextView labelProdPrice;

  @NonNull
  public final TextView labelProdStock;

  @NonNull
  public final RelativeLayout main;

  @NonNull
  public final EditText productName;

  @NonNull
  public final EditText productPrice;

  private ActivityAddItemInventoryBinding(@NonNull RelativeLayout rootView,
      @NonNull ImageButton addImage, @NonNull EditText addStock, @NonNull Button addtoInventory,
      @NonNull ImageButton decrementStock, @NonNull ImageButton incrementStock,
      @NonNull TextView labelProdName, @NonNull TextView labelProdPrice,
      @NonNull TextView labelProdStock, @NonNull RelativeLayout main, @NonNull EditText productName,
      @NonNull EditText productPrice) {
    this.rootView = rootView;
    this.addImage = addImage;
    this.addStock = addStock;
    this.addtoInventory = addtoInventory;
    this.decrementStock = decrementStock;
    this.incrementStock = incrementStock;
    this.labelProdName = labelProdName;
    this.labelProdPrice = labelProdPrice;
    this.labelProdStock = labelProdStock;
    this.main = main;
    this.productName = productName;
    this.productPrice = productPrice;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddItemInventoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddItemInventoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_item_inventory, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddItemInventoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addImage;
      ImageButton addImage = ViewBindings.findChildViewById(rootView, id);
      if (addImage == null) {
        break missingId;
      }

      id = R.id.addStock;
      EditText addStock = ViewBindings.findChildViewById(rootView, id);
      if (addStock == null) {
        break missingId;
      }

      id = R.id.addtoInventory;
      Button addtoInventory = ViewBindings.findChildViewById(rootView, id);
      if (addtoInventory == null) {
        break missingId;
      }

      id = R.id.decrementStock;
      ImageButton decrementStock = ViewBindings.findChildViewById(rootView, id);
      if (decrementStock == null) {
        break missingId;
      }

      id = R.id.incrementStock;
      ImageButton incrementStock = ViewBindings.findChildViewById(rootView, id);
      if (incrementStock == null) {
        break missingId;
      }

      id = R.id.labelProdName;
      TextView labelProdName = ViewBindings.findChildViewById(rootView, id);
      if (labelProdName == null) {
        break missingId;
      }

      id = R.id.labelProdPrice;
      TextView labelProdPrice = ViewBindings.findChildViewById(rootView, id);
      if (labelProdPrice == null) {
        break missingId;
      }

      id = R.id.labelProdStock;
      TextView labelProdStock = ViewBindings.findChildViewById(rootView, id);
      if (labelProdStock == null) {
        break missingId;
      }

      RelativeLayout main = (RelativeLayout) rootView;

      id = R.id.productName;
      EditText productName = ViewBindings.findChildViewById(rootView, id);
      if (productName == null) {
        break missingId;
      }

      id = R.id.productPrice;
      EditText productPrice = ViewBindings.findChildViewById(rootView, id);
      if (productPrice == null) {
        break missingId;
      }

      return new ActivityAddItemInventoryBinding((RelativeLayout) rootView, addImage, addStock,
          addtoInventory, decrementStock, incrementStock, labelProdName, labelProdPrice,
          labelProdStock, main, productName, productPrice);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
