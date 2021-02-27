package com.alaksion.sneakerdex.presentation.model

import com.alaksion.sneakerdex.R

enum class Brands(val apiName: String, val image: Int) {
    ASICS("ASICS", R.drawable.ic_asics),
    CONVERSE("CONVERSE", R.drawable.ic_converse),
    JORDAN("JORDAN", R.drawable.ic_jordan),
    NEW_BALANCE("NEW BALANCE", R.drawable.ic_new_balance),
    NIKE("NIKE", R.drawable.ic_nike),
    OTHER("OTHER", R.drawable.ic_nike),
    PUMA("PUMA", R.drawable.ic_puma),
    REEBOK("REEBOK", R.drawable.ic_reebok),
    SAUCONY("SAUCONY", R.drawable.ic_saucony),
    UNDER_ARMOUR("UNDER_ARMOUR", R.drawable.ic_under_armour),
    VANS("VANS", R.drawable.ic_vans),
    YEEZY("YEEZY", R.drawable.ic_adidas),
    ADIDAS("ADIDAS", R.drawable.ic_adidas)

}