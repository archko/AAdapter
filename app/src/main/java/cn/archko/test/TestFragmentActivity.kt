package cn.archko.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import cn.archko.adapter.R

/**
 * @author: wushuyong 2019/8/5 :16:55
 */
open class TestFragmentActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val intent = intent

        if (null == intent) {
            finish()
            return
        }

        val title = intent.getStringExtra("title")
        val className = intent.getStringExtra("fragment_class")
        var bundle = intent.extras
        if (null == bundle) {
            bundle = Bundle()
        }
        if (!TextUtils.isEmpty(title)) {
            bundle.putString("title", title)
        }
        initFragment(className, bundle)
    }

    /**
     * 这个方法在Fragment的执行之后才执行的.而且两个requestCode不同.使用fragment.startActivityForResult时,这里不需要处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        /*Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
        if(fragment != null){
			fragment.onActivityResult(requestCode, resultCode, data);
		}*/
    }

    fun initFragment(className: String, bundle: Bundle) {
        val intent = intent
        if (null == intent) {
            finish()
            return
        }

        try {
            Log.d(TAG, "initFragment.$className")
            val old = supportFragmentManager.findFragmentById(R.id.content)
            Log.d(TAG, "initFragment.$className old:$old")
            if (null == old) {
                val newFragment = Fragment.instantiate(this, className, intent.extras)
                val ft = supportFragmentManager.beginTransaction()
                if (bundle.getBoolean("add_to_back_stack", false)) {
                    ft.addToBackStack(null)
                }
                ft.add(R.id.content, newFragment).commitAllowingStateLoss()
            } else {
                val newFragment = Fragment.instantiate(this, className, bundle)
                val ft = supportFragmentManager.beginTransaction()
                ft.add(R.id.content, newFragment)
                //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                //ft.addToBackStack(null);
                if (bundle.getBoolean("add_to_back_stack", false)) {
                    ft.addToBackStack(null)
                }
                ft.commitAllowingStateLoss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun addFragmentToStack(newFragment: Fragment) {
        // Add the fragment to the activity, pushing this transaction
        // on to the back stack.
        val ft = supportFragmentManager.beginTransaction()
        ft.add(android.R.id.content, newFragment)  //add需要背景,否则前面的Fragment不销毁,后面的背景透明的.replace则前面的销毁.
        //replace时,返回,前面SLSelectCategoryFragment标题左侧返回按钮没有了.它使用的是GJActivity的标题.
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null)
        ft.commitAllowingStateLoss()
    }

    fun popFragment() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack(fm.getBackStackEntryAt(0).id,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    companion object {
        @JvmStatic
        fun start(self: Activity, bundle: Bundle?, fragmentClass: String) {
            val intent = Intent(self, TestFragmentActivity::class.java)
            if (null != bundle) {
                intent.putExtras(bundle)
            }
            intent.putExtra("fragment_class", fragmentClass)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            self.startActivity(intent)
        }

        val TAG = "Test"
    }
}