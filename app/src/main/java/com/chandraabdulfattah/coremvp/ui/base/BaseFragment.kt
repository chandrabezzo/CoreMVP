package com.chandraabdulfattah.coremvp.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandraabdulfattah.coremvp.R
import com.chandraabdulfattah.coremvp.di.component.ActivityComponent

/**
 * Created by bezzo on 21/12/17.
 */

open class BaseFragment : Fragment(), BaseFragmentView {

    var baseActivity: BaseActivity? = null
        private set
    //    private Unbinder mUnbinder;
    private var mProgressDialog: ProgressDialog? = null
    protected var dataReceived: Bundle? = null
    private var rootView: View? = null
    private var mContext: Context? = null

    val activityComponent: ActivityComponent?
        get() = if (baseActivity != null) {
            baseActivity!!.activityComponent
        } else null

    protected open fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    protected fun showOptionMenu() {
        setHasOptionsMenu(true)
    }

    protected fun hideOptionMenu() {
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(setLayout(), container, false)
        //        setUnBinder(ButterKnife.bind(this, rootView));
        dataReceived = arguments
        mContext = activity
        onViewInitialized(savedInstanceState)
        return rootView
    }

    protected fun getRootView(): View {
        return (activity as BaseActivity).rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.baseActivity = activity
            activity!!.onFragmentAttached()
        }
    }

    override fun getContext(): Context? {
        return this.mContext
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun setLayout(): Int {
        return 0
    }

    override fun showLoading() {
        hideLoading()
//        mProgressDialog = CommonUtils.showLoadingDialog(this.context)
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    override fun openActivityOnTokenExpire() {
        // when token expired
    }

    override fun onError(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.onError(resId)
        }
    }

    override fun onError(message: String?) {
        if (baseActivity != null) {
            baseActivity!!.onError(message)
        }
    }

    override fun showMessage(message: String?) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(message)
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(resId)
        }
    }

    override fun isNetworkConnected(): Boolean {
        return if (baseActivity != null) {
            baseActivity!!.isNetworkConnected()
        } else false
    }

    override fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }

    override fun showToast(message: String, duration: Int) {
        (activity as BaseActivity).showToast(message, duration)
    }

    override fun goToActivity(c: Class<*>, bundle: Bundle?, isFinish: Boolean) {
        (activity as BaseActivity).goToActivity(c, bundle, isFinish)
    }

    override fun goToActivityClearAllStack(c: Class<*>, bundle: Bundle?) {
        (activity as BaseActivity).goToActivityClearAllStack(c, bundle)
    }

    override fun goToActivityForResult(c: Class<*>, bundle: Bundle?, result: Int) {
        (activity as BaseActivity).goToActivityForResult(c, bundle, result)
    }

    override fun finishActivityforResult(bundle: Bundle?, result: Int) {
        (activity as BaseActivity).finishActivityforResult(bundle, result)
    }

    override fun gotoFragment(contentReplace: Int, data: Bundle?, classFragment: Class<*>) {
        (activity as BaseActivity).gotoFragment(contentReplace, data, classFragment)
    }

    override fun showProgressDialog(message: String, cancelable: Boolean) {
        (activity as BaseActivity).showProgressDialog(message, cancelable)
    }

    override fun dismissProgressDialog() {
        (activity as BaseActivity).dismissProgressDialog()
    }

    override fun gotoDialog(dialogClass: Class<*>, data: Bundle?) {
        (activity as BaseActivity).gotoDialog(dialogClass, data)
    }

    override fun showSnackBar(message: String, duration: Int) {
        val snackbar = Snackbar.make(baseActivity!!
                .findViewById(android.R.id.content),
                message, duration)
        val subView = snackbar.view
        val textView = subView.findViewById<View>(android.support.design
                .R.id.snackbar_text) as AppCompatTextView
        textView.setTextColor(ContextCompat.getColor(baseActivity!!, R.color.white))
        snackbar.show()
    }

    fun setActionBarTitle(title: String) {
        (activity as BaseActivity).setActionBarTitle(title)
    }

    interface Callback {
        fun onFragmentAttached()

        fun onFragmentDetached(TAG: String)
    }
}