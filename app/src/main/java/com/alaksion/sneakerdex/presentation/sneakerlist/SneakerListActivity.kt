package com.alaksion.sneakerdex.presentation.sneakerlist

import android.view.LayoutInflater
import com.alaksion.sneakerdex.core.views.BaseViewBindingActivity
import com.alaksion.sneakerdex.databinding.ActivitySneakerListBinding

class SneakerListActivity : BaseViewBindingActivity<ActivitySneakerListBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivitySneakerListBinding =
        ActivitySneakerListBinding::inflate
}