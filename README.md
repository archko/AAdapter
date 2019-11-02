# AAdapter
## samples

- delegate adapter
- listview adapter
- recyclerview adapter
- HeaderAndFooterRecyclerAdapter

## ItemAdapter
with it you can easily to impl a list with multitypes
you can add type1,type2,type3... to a list
## useage:
```
class TestAdapter(activity: Context?) : ItemAdapter<IType<*>>(activity) {
    init {
        addAdapterItem(TestItem.TYPE, TestItem(activity))
        addAdapterItem(TestItem2.TYPE, TestItem2(activity))
        addAdapterItem(TestAdapterItem.TYPE, TestAdapterItem(activity))
    }
}
//diffenent itemType,is used by getItemViewType()

class TestAdapterItem extends AdapterItem {

    public final static int TYPE = 4;

    public TestAdapterItem(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new TestViewHolder(inflater.inflate(R.layout.test_viewholder1, parent, false));
    }

    @Override
    public <T extends IType> void onBind(BaseViewHolder viewHolder, IType tItemBean, int position) {
        viewHolder.onBind(tItemBean, position);
    }
}

//add some data

val list = ArrayList<IType<*>>()
list.add(StringBean(",.MLK1J90X8DV"))
list.add(StringBean2("2222222adf9X xcbvO"))
list.add(TestBean("4444444JK09XD09PZXO"))
```

## BaseListAdapter
usage is like BaseRecyclerAdapter

## BaseRecyclerAdapter
```
fun setup() {
    val adapter = object : BaseRecyclerAdapter<Bean>(this) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
            val view = mInflater.inflate(R.layout.test_viewholder2, parent, false)
            return ViewHolder(view)
        }
    }
    recyclerView.adapter = adapter
    val list = ArrayList<Bean>()
    var bean = Bean(TEST_LISTVIEW, TestListViewFragment::class.java.simpleName)
    list.add(bean)
    adapter.data = list
}
```

now you forget getView(),you only need to override onCreateViewHolder(),then bind data into viewholder:

internal inner class ViewHolder(itemView: View) : BaseViewHolder<Bean>(itemView) {

    private val textView: TextView

    init {
        textView = itemView.findViewById(R.id.text)
    }

    override fun onBind(data: Bean, position: Int) {
        textView.text = data.name
    }
}

## HeaderAndFooterRecyclerAdapter
if a recyclerview need some headers or footers,you can use it.
instead  override onCreateViewHolder(),you should override: 
```
override fun doGetItemViewType(position: Int): Int {
    return mMode
}

override fun doCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FileBean> {
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
    return BaseViewHolder(parent)
}
```
the viewholder like BaseRecyclerAdapter'viewholder
