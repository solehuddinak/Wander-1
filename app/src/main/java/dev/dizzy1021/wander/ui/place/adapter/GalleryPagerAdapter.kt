package dev.dizzy1021.wander.ui.place.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.dizzy1021.core.domain.model.ImagePath
import dev.dizzy1021.core.utils.ARGS_IMAGE_DESC
import dev.dizzy1021.core.utils.ARGS_IMAGE_PATH

class GalleryPagerAdapter(fragment: Fragment, private val images: List<ImagePath>?): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return images?.size ?: 0
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putString(ARGS_IMAGE_PATH, images?.get(position)?.url)
        bundle.putString(ARGS_IMAGE_DESC, images?.get(position)?.desc)


        return GalleryContainerFragment.newInstance(position + 1, bundle)
    }
}