package cn.chenhaonee.cnlab.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

import cn.chenhaonee.cnlab.R;

/**
 * Created by chenh on 2016/6/3.
 */
public class LoadingDiaolog extends DialogFragment {
   // private WebView mWebView;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_loading,null);

      /*  mWebView= (WebView) v.findViewById(R.id.webView);
        mWebView.loadUrl("file:///android_asset/Loading.html");
        mWebView.setBackgroundColor(0);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8") ;*/


    /*    mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setBackgroundColor(0);*/

/*        mWebView.setBackgroundColor(0); // 设置背景色
        mWebView.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255*/


        Dialog dialog=new AlertDialog.Builder(getActivity()).setView(v).create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

}
