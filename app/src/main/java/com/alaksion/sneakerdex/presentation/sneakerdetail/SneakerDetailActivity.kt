package com.alaksion.sneakerdex.presentation.sneakerdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.databinding.ActivitySneakerDetailBinding
import com.alaksion.sneakerdex.shared.constants.SneakerDexConstants
import com.alaksion.sneakerdex.shared.extensions.ImageViewExtensions.setImageFromUrl

class SneakerDetailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySneakerDetailBinding
    private lateinit var sneakerId: String
    private lateinit var mViewModel: SneakerDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_sneaker_detail)
        setContentView(viewBinding.root)
        mViewModel = ViewModelProvider(this).get(SneakerDetailViewModel::class.java)

        getExtras()
        setListeners()
        setObservers()
        mViewModel.getSneaker(sneakerId)
    }

    private fun getExtras() {
        sneakerId = intent.getStringExtra(SneakerDexConstants.SNEAKER_ID_EXTRA).toString()
    }

    private fun setListeners() {
        viewBinding.ivBackButton.setOnClickListener() {
            finish()
        }
    }

    private fun setObservers() {
        mViewModel.validationListener.observe(this, Observer {
            if (!it.getSuccess()) {
                Toast.makeText(this, it.getMessage(), Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.sneakerInfo.observe(this, Observer {
            loadSneakerInfoIntoUi(it)
        })

        mViewModel.isLoading.observe(this, Observer {
            viewBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    private fun loadSneakerInfoIntoUi(sneaker: SneakerData) {
        val formattedPrice = "$ ${sneaker.retailPrice}"

        viewBinding.ivSneakerImage.setImageFromUrl(sneaker.media.smallImageUrl)
        viewBinding.tvSneakerName.text = sneaker.shoe
        viewBinding.tvRetailPrice.text = formattedPrice
    }

    companion object {
        fun getInstance(context: Context, sneakerId: String) {
            val intent = Intent(context, SneakerDetailActivity::class.java)
            intent.putExtra(SneakerDexConstants.SNEAKER_ID_EXTRA, sneakerId)
            context.startActivity(intent)
        }
    }
}