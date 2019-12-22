package cn.archko.test.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.archko.adapter.R
import cn.archko.adapters.item.IType
import cn.archko.adapters.item.ItemAdapter
import cn.archko.test.item.*
import java.util.*

/**
 * @author: archko 2018/12/27 :14:03
 */
class TestItemFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.test_recyclerview, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = TestAdapter(activity)

        val list = ArrayList<IType>()
        list.add(StringBean("LAKDFALDKFAKLDF"))
        list.add(StringBean("P0=2IP45M4TMASLDV"))
        list.add(StringBean(".M,ZCV-00-79X89ZV756"))
        list.add(StringBean("/.,1/5.,SDF"))
        list.add(StringBean("1M,NKZXCJHVI"))
        list.add(StringBean(",.AJDOU9ADF"))
        list.add(StringBean("8913KLZDVKZ"))
        list.add(StringBean(",.MLK1J90X8DV"))
        list.add(StringBean("89Y19322KLAR"))
        list.add(StringBean("KLAJDPFOIUA"))
        list.add(StringBean("KLZCXOUF"))
        list.add(StringBean("JK0afad09PZdfg"))
        list.add(StringBean("JK09X34dfPZX sfg"))
        list.add(StringBean("Jadfadf9XDPZXO"))
        list.add(StringBean("1M,NKZXCJHVI"))
        list.add(StringBean(",.AJDOU9ADF"))
        list.add(StringBean("8913KLZDVKZ"))
        list.add(StringBean(",.MLK1J90X8DV"))

        list.add(StringBean2("2222222adf9X xcbvO"))
        list.add(StringBean2("3333333Kvxczxv9PZXO"))
        list.add(StringBean2("4444444JK09X8902435PZXO"))
        list.add(StringBean2("5555555JK09XadfadfPZXO"))
        list.add(StringBean2("6666666JK0sdfgh09PZXO"))

        list.add(StringBean("JK0zxcvD09PZXO"))
        list.add(StringBean("adfadJK09O"))
        list.add(StringBean("Jzcvz9adf PZXO"))
        list.add(StringBean("JK0adfadD09PZXO"))
        list.add(StringBean("J adf9XD09adf"))

        list.add(TestBean("4444444JK09XD09PZXO"))
        list.add(TestBean("2222222adf9XD09xcbvO"))
        list.add(TestBean("JK09X34dfPZXO"))
        list.add(TestBean("89Y19322KLAR"))
        adapter.data = list
        recyclerView.adapter = adapter
    }

    class TestAdapter(activity: Context?) : ItemAdapter<IType>(activity) {
        init {
            addAdapterItem(TestItem.TYPE, TestItem(activity))
            addAdapterItem(TestItem2.TYPE, TestItem2(activity))
            addAdapterItem(TestAdapterItem.TYPE, TestAdapterItem(activity))
        }
    }
}
