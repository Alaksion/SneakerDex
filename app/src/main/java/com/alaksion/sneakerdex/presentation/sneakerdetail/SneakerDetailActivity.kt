package com.alaksion.sneakerdex.presentation.sneakerdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.databinding.ActivitySneakerDetailBinding
import com.alaksion.sneakerdex.domain.model.SneakersResponse
import com.alaksion.sneakerdex.presentation.model.Brands
import com.alaksion.sneakerdex.presentation.model.SneakerSizes
import com.alaksion.sneakerdex.shared.constants.SneakerDexConstants
import com.alaksion.sneakerdex.shared.extensions.ImageViewExtensions.setImageFromUrl
import com.alaksion.sneakerdex.shared.network.Resource
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class SneakerDetailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySneakerDetailBinding
    private lateinit var sneakerId: String
    private val mViewModel by viewModel<SneakerDetailViewModel>()

    private val adapter = SneakerSizeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySneakerDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        getExtras()
        setListeners()
        setObservers()
        setUpRecycler()
        setUpAdapterListener()
        mViewModel.getSneaker(sneakerId)
    }

    private fun getExtras() {
        sneakerId = intent.getStringExtra(SneakerDexConstants.SNEAKER_ID_BUNDLE).toString()
    }

    private fun setListeners() {
        viewBinding.hcDetailActivity.onBackClick = { onBackPressed() }

        viewBinding.ecActDetail.onClick = { mViewModel.getSneaker(sneakerId) }
    }

    private fun setObservers() {
        mViewModel.sneakerInfo.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    loadSneakerInfoIntoUi(response.data)
                    showUiBasedOnApiResponse(true)
                }
                is Resource.Error -> {
                    showUiBasedOnApiResponse(false)
                }
            }
        })

        mViewModel.isLoading.observe(this, Observer {
            viewBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        mViewModel.selectedSize.observe(this, Observer {
            adapter.handleItemChange(it)
        })
    }

    private fun showUiBasedOnApiResponse(isSuccess: Boolean) {
        if (isSuccess) {
            viewBinding.clContent.visibility = View.VISIBLE
            viewBinding.ecActDetail.visibility = View.GONE
        } else {
            viewBinding.clContent.visibility = View.GONE
            viewBinding.ecActDetail.visibility = View.VISIBLE
        }
    }

    private fun loadSneakerInfoIntoUi(sneakerInfo: SneakersResponse?) {
        val sneaker = sneakerInfo?.results?.get(0)

        val formattedPrice = "$ ${sneaker?.retailPrice}"

        viewBinding.ivSneakerImage.setImageFromUrl(sneaker?.media?.smallImageUrl)
        viewBinding.tvSneakerName.text = sneaker?.shoe
        viewBinding.tvRetailPrice.text = formattedPrice

        viewBinding.tvColorway.text =
            String.format(resources.getString(R.string.colorway_text), sneaker?.colorway)
        setBrandLogo(sneaker?.brand!!)
    }

    private fun setBrandLogo(brandName: String) {
        val brandImage = getLogoDrawableId(brandName)
        viewBinding.ivBrandLogo.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                brandImage,
                null
            )
        )
    }

    private fun getLogoDrawableId(brandName: String): Int {
        return when (brandName.toUpperCase(Locale.ROOT)) {
            Brands.ADIDAS.apiName -> Brands.ADIDAS.image
            Brands.NIKE.apiName -> Brands.NIKE.image
            Brands.CONVERSE.apiName -> Brands.CONVERSE.image
            Brands.SAUCONY.apiName -> Brands.SAUCONY.image
            Brands.JORDAN.apiName -> Brands.JORDAN.image
            Brands.ASICS.apiName -> Brands.ASICS.image
            Brands.NEW_BALANCE.apiName -> Brands.NEW_BALANCE.image
            Brands.PUMA.apiName -> Brands.PUMA.image
            Brands.UNDER_ARMOUR.apiName -> Brands.UNDER_ARMOUR.image
            Brands.VANS.apiName -> Brands.VANS.image
            Brands.OTHER.apiName -> Brands.OTHER.image
            Brands.REEBOK.apiName -> Brands.REEBOK.image
            Brands.YEEZY.apiName -> Brands.YEEZY.image
            else -> R.drawable.ic_nike
        }
    }

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewBinding.rvSizes.layoutManager = layoutManager
        viewBinding.rvSizes.adapter = adapter
    }

    private fun setUpAdapterListener() {
        this.adapter.attachListener(object :
            SneakerSizeListener {
            override fun onSizeItemClick(size: SneakerSizes) {
                mViewModel.selectSneakerSize(size)
            }
        })

    }

    companion object {
        fun getInstance(context: Context, sneakerId: String) {
            val intent = Intent(context, SneakerDetailActivity::class.java)
            intent.putExtra(SneakerDexConstants.SNEAKER_ID_BUNDLE, sneakerId)
            context.startActivity(intent)
        }
    }
}