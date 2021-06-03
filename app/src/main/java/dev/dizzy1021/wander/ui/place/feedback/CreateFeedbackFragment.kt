package dev.dizzy1021.wander.ui.place.feedback

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.core.utils.ResourceState
import dev.dizzy1021.core.utils.SharedPreferenceUtil
import dev.dizzy1021.wander.R
import dev.dizzy1021.wander.databinding.FragmentCreateFeedbackBinding
import java.io.InputStream
import javax.inject.Inject

@AndroidEntryPoint
class CreateFeedbackFragment : Fragment() {

    private var _binding: FragmentCreateFeedbackBinding? = null
    private val binding get() = _binding as FragmentCreateFeedbackBinding
    private var listInputStream: ArrayList<InputStream?> = arrayListOf(null, null, null, null, null)
    private val viewModel: FeedbackViewModel by viewModels()

    @Inject
    lateinit var pref: SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar.title = "Give a Feedback"
        actionBar.isVisible = true

        actionBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        actionBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateFeedbackBinding.inflate(inflater, container, false)
        ratingText(5)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idPlace = CreateFeedbackFragmentArgs.fromBundle(arguments as Bundle).id
        val user = pref.getUser()

        binding.buttonAddReview.setOnClickListener {

            val desc = binding.reviewDesc.text.toString()
            val rate = binding.ratingBar.numStars

            Log.d("ListImages OKHTTP", "Images - $listInputStream")

            user?.let {
                viewModel.addReview(
                    idPlace,
                    listInputStream,
                    it,
                    desc,
                    rate
                ).observe(viewLifecycleOwner, { review ->
                    if (review != null) {
                        when (review.state) {
                            ResourceState.SUCCESS -> {
                                Toast.makeText(requireContext(), "Successfully give a feedback", Toast.LENGTH_LONG).show()
                                activity?.onBackPressed()
                            }
                            ResourceState.FAILURE -> {
                                Toast.makeText(requireContext(), "Failed give a feedback", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })
            }
        }

        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            ratingText(rating.toInt())
        }

        binding.addImageOne.setOnClickListener {
            openFileChooser(1)
        }

        binding.addImageTwo.setOnClickListener {
            openFileChooser(2)
        }
        binding.addImageThree.setOnClickListener {
            openFileChooser(3)
        }
        binding.addImageFour.setOnClickListener {
            openFileChooser(4)
        }
        binding.addImageFive.setOnClickListener {
            openFileChooser(5)
        }

    }

    private var resultChooserOne =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                val inputStream = uri?.let { activity?.contentResolver?.openInputStream(it) }

                listInputStream[0] = inputStream
                binding.addImageOne.setImageURI(uri)
            }
        }

    private var resultChooserTwo =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                val inputStream = uri?.let { activity?.contentResolver?.openInputStream(it) }

                listInputStream[1] = inputStream
                binding.addImageTwo.setImageURI(uri)
            }
        }

    private var resultChooserThree =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                val inputStream = uri?.let { activity?.contentResolver?.openInputStream(it) }

                listInputStream[2] = inputStream
                binding.addImageThree.setImageURI(uri)
            }
        }
    private var resultChooserFour =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                val inputStream = uri?.let { activity?.contentResolver?.openInputStream(it) }

                listInputStream[3] = inputStream
                binding.addImageFour.setImageURI(uri)
            }
        }
    private var resultChooserFive =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                val inputStream = uri?.let { activity?.contentResolver?.openInputStream(it) }

                listInputStream[4] = inputStream
                binding.addImageFive.setImageURI(uri)
            }
        }

    private fun openFileChooser(position: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        val chooser = Intent.createChooser(intent, "Choose a Picture")

        when(position) {
            1 -> resultChooserOne.launch(chooser)
            2 -> resultChooserTwo.launch(chooser)
            3 -> resultChooserThree.launch(chooser)
            4 -> resultChooserFour.launch(chooser)
            else -> resultChooserFive.launch(chooser)
        }
    }

    private fun ratingText(rating: Int) {

        when (rating) {
            1 -> {
                binding.ratingText.text = getString(R.string.disappointed)
            }
            2 -> {
                binding.ratingText.text = getString(R.string.dont_like)
            }
            3 -> {
                binding.ratingText.text = getString(R.string.not_bad)
            }
            4 -> {
                binding.ratingText.text = getString(R.string.like_it)
            }
            else -> {
                binding.ratingText.text = getString(R.string.best_place)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}