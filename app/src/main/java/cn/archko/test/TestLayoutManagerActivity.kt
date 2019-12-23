package cn.archko.test

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

import java.util.ArrayList
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.archko.adapter.R
import cn.archko.adapters.BaseRecyclerAdapter
import cn.archko.adapters.BaseViewHolder
import cn.archko.test.fragments.TestItemFragment
import cn.archko.test.fragments.TestListViewFragment
import cn.archko.test.fragments.TestRecyclerViewFragment

/**
 * @author: archko 2018/12/27 :14:03
 */
class TestLayoutManagerActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_recyclerview)

        checkSdcardPermission()
    }

    internal inner class Bean(var layout: Int, var name: String)

    internal inner class ViewHolder(itemView: View) : BaseViewHolder<Bean>(itemView) {

        private val textView: TextView

        init {
            textView = itemView.findViewById(R.id.text)
        }

        override fun onBind(data: Bean, position: Int) {
            textView.text = data.name
            textView.setOnClickListener { view ->
                when (data.layout) {
                    TEST_LISTVIEW -> TestFragmentActivity.start(this@TestLayoutManagerActivity, null, TestListViewFragment::class.java.name)
                    TEST_RECYCLERVIEW -> TestFragmentActivity.start(this@TestLayoutManagerActivity, null, TestRecyclerViewFragment::class.java.name)
                    TEST_ITEM_ADAPTER -> TestFragmentActivity.start(this@TestLayoutManagerActivity, null, TestItemFragment::class.java.name)
                }
            }
        }
    }

    private fun loadView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = object : BaseRecyclerAdapter<Bean>(this) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Bean> {
                val view = mInflater.inflate(R.layout.test_viewholder2, parent, false)
                return ViewHolder(view)
            }
        }
        recyclerView.adapter = adapter

        val list = ArrayList<Bean>()
        var bean = Bean(TEST_LISTVIEW, TestListViewFragment::class.java.simpleName)
        list.add(bean)
        bean = Bean(TEST_RECYCLERVIEW, TestRecyclerViewFragment::class.java.simpleName)
        list.add(bean)
        bean = Bean(TEST_ITEM_ADAPTER, TestItemFragment::class.java.simpleName)
        list.add(bean)
        adapter.data = list
        adapter.notifyDataSetChanged()
    }

    fun checkSdcardPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // WRITE_EXTERNAL_STORAGE permission has not been granted.

            requestSdcardPermission()
        } else {
            loadView()
        }
    }

    /**
     * Requests the sdcard permission.
     * If the permission has been denied previously, a SnackBar will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private fun requestSdcardPermission() {
        Log.d(TAG, "sdcard permission has NOT been granted. Requesting permission.")

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSION_CODE)
        } else {

            // sdcard permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //  权限通过
                //((RefreshableFragment) (mPagerAdapter.getItem(mViewPager.getCurrentItem()))).update();
                loadView()
            } else {
                // 权限拒绝
                Toast.makeText(this, "没有获取sdcard的读取权限", Toast.LENGTH_LONG).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    companion object {

        val TAG = "Test"
        val TEST_LISTVIEW = 0
        val TEST_RECYCLERVIEW = 1
        val TEST_ITEM_ADAPTER = 2

        val REQUEST_PERMISSION_CODE = 0x001
    }


}
