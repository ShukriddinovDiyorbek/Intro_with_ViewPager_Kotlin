package uz.pdp.tasks5_modul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.intropagekotlin.Page
import com.example.intropagekotlin.PageAdapter
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var pages: ArrayList<Page>
    private lateinit var viewPager: ViewPager
    private lateinit var tv_skip: TextView
    private lateinit var btn_start: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        viewPager = findViewById(R.id.vp_intro)
        val tabLayout = findViewById<TabLayout>(R.id.tab_intro)
        tv_skip = findViewById(R.id.tv_skip)
        btn_start = findViewById(R.id.btn_start)


        pages = ArrayList()
        addPages()
        refreshAdapter()
        tabLayout.setupWithViewPager(viewPager)

        controlPage()
    }

    private fun refreshAdapter() {
        val pagerAdapter: PagerAdapter = PageAdapter(pages, this)
        viewPager.adapter = pagerAdapter
    }

    private fun controlButton(position: Int) {
        if (position != 2) {
            btn_start.visibility = View.GONE
            tv_skip.visibility = View.VISIBLE
        } else {
            tv_skip.visibility = View.GONE
            btn_start.visibility = View.VISIBLE
        }
    }

    private fun controlPage() {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                controlButton(position)
                tv_skip.setOnClickListener {
                    viewPager.currentItem = position + 1
                }
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun addPages() {
        pages.add(
            Page(
                R.drawable.communication,
                "Say Hello to Global-Top Up",
                "Send mobile top-up to more than 500 networks in over 140 countries"
            )
        )
        pages.add(
            Page(
                R.drawable.secure,
                "Safe, Trusted & Fully Secure",
                "Encrypted transactions mean your payments & Privacy and protected"
            )
        )
        pages.add(
            Page(
                R.drawable.easy_to_share,
                "Easy to Use",
                "Pick a number, choose an account, send your Top-up. Simple"
            )
        )
    }
}