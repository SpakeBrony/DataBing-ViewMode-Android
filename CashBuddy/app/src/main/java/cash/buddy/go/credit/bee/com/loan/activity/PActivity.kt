package cash.buddy.go.credit.bee.com.loan.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import cash.buddy.go.credit.bee.com.loan.adapter.CardPagerAdapter
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityAgreeBinding
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityPBinding
import cash.buddy.go.credit.bee.com.loan.utils.ShadowTransformer
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences
import cash.buddy.go.credit.bee.com.loan.utils.Utils

class PActivity : BaseActivity(), ViewPager.OnPageChangeListener {


    private lateinit var binding: ActivityPBinding


    private val mCardAdapter: CardPagerAdapter by lazy {
        CardPagerAdapter()
    }

    private val mCardShadowTransformer: ShadowTransformer by lazy {
        ShadowTransformer(binding.viewPage, mCardAdapter)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("DISCLOSURE STATEMENT")

        binding.next.setOnClickListener {
            StorePreferences.getInstance(this).user_xy = "yes_xy"
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }


        mCardAdapter.addCardItem("https://cash-buddy.net/privacypolicy.html")
        mCardAdapter.addCardItem("https://cash-buddy.net/termsandconditions.html")
        mCardAdapter.addCardItem("https://cash-buddy.net/disclosure.html")

        binding.viewPage.run {
            adapter = mCardAdapter
            setPageTransformer(false, mCardShadowTransformer)
            offscreenPageLimit = 3
            setOnPageChangeListener(this@PActivity)
        }

        binding.indicator.setNumPages(3)
        binding.indicator.setCurrentPage(0)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        binding.indicator.setCurrentPage(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }


}