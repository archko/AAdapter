package cn.archko.test.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import cn.archko.adapter.R
import cn.archko.adapters.BaseListAdapter
import cn.archko.adapters.BaseViewHolder
import cn.archko.test.FileBean
import cn.archko.test.OnItemClickListener

/**
 * @author: archko 2018/12/12 :15:43
 */
class BookListViewAdapter : BaseListAdapter<FileBean> {

    private var mMode = TYPE_FILE
    private var itemClickListener: OnItemClickListener<FileBean>? = null;

    internal fun setMode(mMode: Int) {
        this.mMode = mMode
    }

    constructor(context: Context, itemClickListener: OnItemClickListener<FileBean>) : super(context) {
        this.itemClickListener = itemClickListener
    }

    constructor(context: Context, arrayList: List<FileBean>, itemClickListener: OnItemClickListener<FileBean>) : super(context, arrayList) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {
        if (viewType == TYPE_FILE) {
            val view = mInflater.inflate(R.layout.item_book_normal, parent, false)
            return ViewHolder(view)
        } else if (viewType == TYPE_RENCENT) {
            val view = mInflater.inflate(R.layout.item_book_normal, parent, false)
            return ViewHolder(view)
        } else if (viewType == TYPE_SEARCH) {
            val view = mInflater.inflate(R.layout.item_book_search, parent, false)
            return SearchViewHolder(view)
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>?, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return mMode
    }

    /*override fun onBindViewHolder(holder: BaseListAdapter.BaseViewHolder<*>, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == TYPE_FILE) {
            holder.onBind(data[position], position)
        } else if (viewType == TYPE_RENCENT) {
            holder.onBind(data[position], position)
        } else if (viewType == TYPE_SEARCH) {
            holder.onBind(data[position], position)
        }
    }*/

    private inner class ViewHolder(itemView: View) : BaseViewHolder<FileBean>(itemView) {

        internal var mName: TextView? = null
        internal var mIcon: ImageView? = null
        internal var mSize: TextView? = null
        internal var mProgressBar: ProgressBar? = null

        init {

            mIcon = itemView.findViewById(R.id.icon)
            mName = itemView.findViewById(R.id.name)
            mSize = itemView.findViewById(R.id.size)
            mProgressBar = itemView.findViewById(R.id.progressbar)
        }

        override fun onBind(entry: FileBean, position: Int) {
            itemClickListener?.let {
                itemView.setOnClickListener { itemClickListener!!.onItemClick(itemView, entry, position) }
                itemView.setOnLongClickListener {
                    if (entry.type != FileBean.HOME
                            && !entry.isDirectory
                            && !entry.isUpFolder) {
                        itemClickListener!!.onItemClick2(itemView, entry, position)
                        return@setOnLongClickListener true
                    } else {
                        return@setOnLongClickListener false
                    }
                }
            }
            mName!!.text = entry.label
        }
    }

    private inner class SearchViewHolder(itemView: View) : BaseViewHolder<FileBean>(itemView) {

        internal var mName: TextView? = null
        internal var mIcon: ImageView? = null
        internal var mSize: TextView? = null
        internal var mPath: TextView? = null

        init {

            mIcon = itemView.findViewById(R.id.icon)
            mName = itemView.findViewById(R.id.name)
            mSize = itemView.findViewById(R.id.size)
            mPath = itemView.findViewById(R.id.fullpath)
        }

        override fun onBind(entry: FileBean, position: Int) {
            itemClickListener?.let {
                itemView.setOnClickListener { itemClickListener!!.onItemClick(itemView, entry, position) }
                itemView.setOnLongClickListener {
                    if (entry.type != FileBean.HOME
                            && !entry.isDirectory
                            && !entry.isUpFolder) {
                        itemClickListener!!.onItemClick2(itemView, entry, position)
                        return@setOnLongClickListener true
                    } else {
                        return@setOnLongClickListener false
                    }
                }
            }
            mName!!.text = entry.label
        }
    }

    companion object {

        @JvmField
        val TYPE_FILE = 0
        @JvmField
        val TYPE_RENCENT = 1
        @JvmField
        val TYPE_SEARCH = 2
    }
}
