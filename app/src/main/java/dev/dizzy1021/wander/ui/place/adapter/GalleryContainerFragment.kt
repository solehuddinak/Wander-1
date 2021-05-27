package dev.dizzy1021.wander.ui.place.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.core.R
import dev.dizzy1021.core.utils.ARGS_IMAGE_DESC
import dev.dizzy1021.core.utils.ARGS_IMAGE_PATH
import dev.dizzy1021.core.utils.ARGS_SECTION_IMAGE
import dev.dizzy1021.core.utils.ARGS_SECTION_NUMBER
import dev.dizzy1021.wander.databinding.ItemListPlaceGalleryBinding

@AndroidEntryPoint
class GalleryContainerFragment: Fragment() {

    private var _binding: ItemListPlaceGalleryBinding? = null
    private val binding get() = _binding as ItemListPlaceGalleryBinding

    companion object {
        @JvmStatic
        fun newInstance(index: Int, image: Bundle) =
            GalleryContainerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARGS_SECTION_NUMBER, index)
                    putBundle(ARGS_SECTION_IMAGE, image)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val bundle = arguments?.getBundle(ARGS_SECTION_IMAGE)
        val image = bundle?.getString(ARGS_IMAGE_PATH)
        val desc =  bundle?.getString(ARGS_IMAGE_DESC)

        _binding = ItemListPlaceGalleryBinding.inflate(inflater, container, false)

        Glide.with(requireActivity())
            .load(image)
            .error(R.drawable.ic_no_image)
            .placeholder(R.drawable.ic_no_image)
            .into(binding.galleryImage)

        binding.galleryImage.contentDescription = desc

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}