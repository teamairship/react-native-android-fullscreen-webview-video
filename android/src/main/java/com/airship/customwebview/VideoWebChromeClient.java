package com.airship.customwebview;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;

import android.view.ViewGroup.LayoutParams;

/**
 * Provides support for full-screen video on Android
 */
public class VideoWebChromeClient extends WebChromeClient {

  private final FrameLayout.LayoutParams FULLSCREEN_LAYOUT_PARAMS = new FrameLayout.LayoutParams(
      LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER);

  private WebChromeClient.CustomViewCallback mCustomViewCallback;

  private Activity mActivity;
  private View mWebView;
  private View mVideoView;
  private Boolean isVideoFullscreen;
  public Integer nbFois;
  private ViewGroup.LayoutParams paramsNotFullscreen;
  private ThemedReactContext mReactContext;

  public VideoWebChromeClient(Activity activity, WebView webView, ThemedReactContext reactContext) {
    mWebView = webView;
    mActivity = activity;
    isVideoFullscreen = false;
    nbFois = 0;
    mReactContext = reactContext;
  }

  @Override
  public void onShowCustomView(View view, CustomViewCallback callback) {
    if (mVideoView != null) {
      callback.onCustomViewHidden();
      return;
    }
    WritableMap params = Arguments.createMap();

    sendEvent(mReactContext, "VideoWillEnterFullScreen", params);
    // Store the view and it's callback for later, so we can dispose of them correctly
    mVideoView = view;
    mCustomViewCallback = callback;

    view.setBackgroundColor(Color.BLACK);
    //mVideoView.setRotation(90);
    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    /*mVideoView.setKeepScreenOn(true);
    mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);*/
    // paramsNotFullscreen= mVideoView.getLayoutParams();
    //LayoutParams params = new LayoutParams(paramsNotFullscreen);
    //params.setMargins(0, 0, 0, 0);
    //params.height=ViewGroup.LayoutParams.MATCH_PARENT;
    //params.width=ViewGroup.LayoutParams.MATCH_PARENT;
    //params.addRule(RelativeLayout.CENTER_IN_PARENT);
    //mVideoView.setLayoutParams(params);
    getRootView().addView(mVideoView, FULLSCREEN_LAYOUT_PARAMS);

    ((View) mWebView.getRootView()).setVisibility(View.GONE);
    isVideoFullscreen = true;
  }

  @Override
  public void onHideCustomView() {
    if (mVideoView == null) {
      return;
    }
    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    ((View) mWebView.getRootView()).setVisibility(View.VISIBLE);
    mVideoView.setVisibility(View.GONE);

    // Remove the custom view from its container.
    getRootView().removeView(mVideoView);
    mVideoView = null;
    mCustomViewCallback.onCustomViewHidden();
    isVideoFullscreen = false;
    WritableMap params = Arguments.createMap();

    sendEvent(mReactContext, "VideoNotFullScreenAnymore", params);
  }

  private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
  }

  private static ReactRootView findRootView(View parent) {
    if (parent == null) {
      return null;
    }
    if (parent instanceof ReactRootView) {
      return (ReactRootView) parent;
    }
    return findRootView((ReactRootView) parent.getParent());
  }

  private ViewGroup getRootView() {
    return ((ViewGroup) mActivity.findViewById(android.R.id.content));
  }

  /**
   * Notifies the class that the back key has been pressed by the user.
   * This must be called from the Activity's onBackPressed(), and if it returns false, the activity itself should handle it. Otherwise don't do anything.
   * @return Returns true if the event was handled, and false if was not (video view is not visible)
   */
  public boolean onBackPressed() {
    if (isVideoFullscreen) {
      onHideCustomView();
      return true;
    } else {
      return false;
    }
  }
}

// package com.ovoenergy.customwebview;

// import android.app.Activity;
// import android.graphics.Color;
// import android.view.Gravity;
// import android.view.View;
// import android.view.ViewGroup;
// import android.webkit.WebChromeClient;
// import android.webkit.WebView;
// import android.widget.FrameLayout;

// import static android.view.ViewGroup.LayoutParams;

// /**
//  * Provides support for full-screen video on Android
//  */
// public class VideoWebChromeClient extends WebChromeClient {

//   private final FrameLayout.LayoutParams FULLSCREEN_LAYOUT_PARAMS = new FrameLayout.LayoutParams(
//       LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER);

//   private WebChromeClient.CustomViewCallback mCustomViewCallback;

//   private Activity mActivity;
//   private View mWebView;
//   private View mVideoView;

//   public VideoWebChromeClient(Activity activity, WebView webView) {
//     mWebView = webView;
//     mActivity = activity;
//   }

//   @Override
//   public void onShowCustomView(View view, CustomViewCallback callback) {
//     if (mVideoView != null) {
//       callback.onCustomViewHidden();
//       return;
//     }

//     // Store the view and it's callback for later, so we can dispose of them correctly
//     mVideoView = view;
//     mCustomViewCallback = callback;

//     view.setBackgroundColor(Color.BLACK);

//     getRootView().addView(view, FULLSCREEN_LAYOUT_PARAMS);

//     mWebView.setVisibility(View.GONE);
//   }

//   @Override
//   public void onHideCustomView() {
//     if (mVideoView == null) {
//       return;
//     }

//     mVideoView.setVisibility(View.GONE);

//     // Remove the custom view from its container.
//     getRootView().removeView(mVideoView);
//     mVideoView = null;
//     mCustomViewCallback.onCustomViewHidden();

//     mWebView.setVisibility(View.VISIBLE);
//   }

//   private ViewGroup getRootView() {
//     return ((ViewGroup) mActivity.findViewById(android.R.id.content));
//   }
// }
