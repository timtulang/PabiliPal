// Generated by data binding compiler. Do not edit!
package com.example.uibasics.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.uibasics.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivitySignUpUserBinding extends ViewDataBinding {
  @NonNull
  public final TextView createUserLabel;

  @NonNull
  public final ImageButton imageButton8;

  @NonNull
  public final TextView loginRedirectText;

  @NonNull
  public final Button signupButton;

  @NonNull
  public final EditText signupConfirm;

  @NonNull
  public final EditText signupPassword;

  @NonNull
  public final EditText signupUser;

  protected ActivitySignUpUserBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView createUserLabel, ImageButton imageButton8, TextView loginRedirectText,
      Button signupButton, EditText signupConfirm, EditText signupPassword, EditText signupUser) {
    super(_bindingComponent, _root, _localFieldCount);
    this.createUserLabel = createUserLabel;
    this.imageButton8 = imageButton8;
    this.loginRedirectText = loginRedirectText;
    this.signupButton = signupButton;
    this.signupConfirm = signupConfirm;
    this.signupPassword = signupPassword;
    this.signupUser = signupUser;
  }

  @NonNull
  public static ActivitySignUpUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_sign_up_user, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySignUpUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivitySignUpUserBinding>inflateInternal(inflater, R.layout.activity_sign_up_user, root, attachToRoot, component);
  }

  @NonNull
  public static ActivitySignUpUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_sign_up_user, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySignUpUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivitySignUpUserBinding>inflateInternal(inflater, R.layout.activity_sign_up_user, null, false, component);
  }

  public static ActivitySignUpUserBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivitySignUpUserBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivitySignUpUserBinding)bind(component, view, R.layout.activity_sign_up_user);
  }
}
