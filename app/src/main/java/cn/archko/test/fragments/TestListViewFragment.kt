package cn.archko.test.fragments

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.archko.adapter.R
import cn.archko.test.FileBean
import cn.archko.test.OnItemClickListener
import cn.archko.test.adapters.BookListViewAdapter
import java.io.File
import java.io.FileFilter
import java.util.*
import kotlin.collections.ArrayList

/**
 * @description:
 *
 * @author: archko 11-11-17
 */
open class TestListViewFragment : Fragment() {

    private var mCurrentPath: String? = null

    protected var mSwipeRefreshWidget: SwipeRefreshLayout? = null
    protected var filesListView: ListView? = null
    private var fileFilter: FileFilter? = null
    protected var fileListAdapter: BookListViewAdapter? = null

    private val dirsFirst = true
    private var showExtension: Boolean? = false

    internal var mPathMap: MutableMap<String, Int> = HashMap()
    protected var mSelectedPos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCurrentPath = getHome()
    }

    open fun onBackPressed(): Boolean {
        val path = Environment.getExternalStorageDirectory().absolutePath
        if (this.mCurrentPath != path && this.mCurrentPath != "/") {
            val upFolder = File(this.mCurrentPath!!).parentFile
            if (upFolder.isDirectory) {
                this.mCurrentPath = upFolder.absolutePath
                updateAdapter()
                return true
            }
        }
        return false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.test_listview, container, false)

        this.filesListView = view.findViewById(R.id.files)

        mSwipeRefreshWidget = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_widget) as SwipeRefreshLayout
        fileListAdapter = BookListViewAdapter(activity as Context, itemClickListener)

        return view
    }

    fun onRefresh() {
        update()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.fileFilter = FileFilter { file ->
            //return (file.isDirectory() || file.getName().toLowerCase().endsWith(".pdf"));
            if (file.isDirectory)
                return@FileFilter true
            val fname = file.name.toLowerCase()

            if (fname.endsWith(".pdf"))
                return@FileFilter true
            if (fname.endsWith(".xps"))
                return@FileFilter true
            if (fname.endsWith(".cbz"))
                return@FileFilter true
            if (fname.endsWith(".png"))
                return@FileFilter true
            if (fname.endsWith(".jpe"))
                return@FileFilter true
            if (fname.endsWith(".jpeg"))
                return@FileFilter true
            if (fname.endsWith(".jpg"))
                return@FileFilter true
            if (fname.endsWith(".jfif"))
                return@FileFilter true
            if (fname.endsWith(".jfif-tbnl"))
                return@FileFilter true
            if (fname.endsWith(".tif"))
                return@FileFilter true
            if (fname.endsWith(".tiff"))
                return@FileFilter true
            if (fname.endsWith(".epub"))
                return@FileFilter true
            if (fname.endsWith(".txt"))
                return@FileFilter true
            false
        }

        updateAdapter()
    }

    fun updateAdapter() {
        val activity = activity
        this.filesListView?.adapter = this.fileListAdapter

        update()
    }

    fun update() {
        val fileList: ArrayList<FileBean> = ArrayList()
        var entry: FileBean

        entry = FileBean(FileBean.HOME, "Home")
        fileList.add(entry)

        if (this.mCurrentPath != "/") {
            val upFolder = File(this.mCurrentPath!!).parentFile
            entry = FileBean(FileBean.NORMAL, upFolder, "..")
            fileList.add(entry)
        }

        val files = File(this.mCurrentPath!!).listFiles(this.fileFilter)

        if (files != null) {
            try {
                Arrays.sort(files, Comparator<File> { f1, f2 ->
                    if (f1 == null) throw RuntimeException("f1 is null inside sort")
                    if (f2 == null) throw RuntimeException("f2 is null inside sort")
                    try {
                        if (dirsFirst && f1.isDirectory != f2.isDirectory) {
                            if (f1.isDirectory)
                                return@Comparator -1
                            else
                                return@Comparator 1
                        }
                        return@Comparator f1.name.toLowerCase().compareTo(f2.name.toLowerCase())
                    } catch (e: NullPointerException) {
                        throw RuntimeException("failed to compare $f1 and $f2", e)
                    }
                })
            } catch (e: NullPointerException) {
                throw RuntimeException("failed to sort file list " + files + " for path " + this.mCurrentPath, e)
            }

            for (file in files) {
                entry = FileBean(FileBean.NORMAL, file, showExtension)
                fileList.add(entry)
            }
        }

        fileListAdapter!!.setData(fileList)
        if (null != mPathMap[mCurrentPath!!]) {
            val pos = mPathMap[mCurrentPath!!]
            if (pos!! < fileList.size) {
                //filesListView!!.setSelection(pos)
            }
        }
        fileListAdapter!!.notifyDataSetChanged()
        mSwipeRefreshWidget!!.isRefreshing = false

    }

    private fun getHome(): String {
        val defaultHome = Environment.getExternalStorageDirectory().absolutePath
        var path: String? = null
        if (null == path) {
            path = defaultHome
        }
        if (path!!.length > 1 && path!!.endsWith("/")) {
            path = path.substring(0, path.length - 2)
        }

        val pathFile = File(path)

        if (pathFile.exists() && pathFile.isDirectory)
            return path
        else
            return defaultHome
    }

    val itemClickListener: OnItemClickListener<FileBean> = object : OnItemClickListener<FileBean> {
        override fun onItemClick(view: View?, data: FileBean?, position: Int) {
            clickItem(position)
        }

        override fun onItemClick2(view: View?, data: FileBean?, position: Int) {
            clickItem2(position, view!!)
        }
    }

    private fun clickItem(position: Int) {
        val clickedEntry = fileListAdapter!!.data[position]
        val clickedFile: File?

        if (clickedEntry.type == FileBean.HOME) {
            clickedFile = File(getHome())
        } else {
            clickedFile = clickedEntry.file
        }

        if (null == clickedFile || !clickedFile.exists())
            return

        if (clickedFile.isDirectory) {
            var pos: Int = 0
            if (pos < 0) {
                pos = 0
            }
            mPathMap.put(mCurrentPath!!, pos)
            this@TestListViewFragment.mCurrentPath = clickedFile.absolutePath
            updateAdapter()

            var map = mapOf("type" to "dir", "name" to clickedFile.name)
        } else {
            var map = mapOf("type" to "file", "name" to clickedFile.name)

        }
    }

    private fun clickItem2(position: Int, view: View) {
        val entry = this.fileListAdapter!!.data.get(position) as FileBean
        if (!entry.isDirectory && entry.type != FileBean.HOME) {
            mSelectedPos = position
            return
        }
        mSelectedPos = -1
    }

    companion object {

        val TAG = "TestRecyclerViewFragment"

    }
}
