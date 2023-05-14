package udayfionics.nycschools.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import udayfionics.nycschools.databinding.FragmentSchoolDetailsBinding
import udayfionics.nycschools.viewmodel.SchoolDetailsViewModel

class SchoolDetailsFragment : Fragment() {

    private lateinit var schoolDbn: String
    private lateinit var binding: FragmentSchoolDetailsBinding
    private lateinit var viewModel: SchoolDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchoolDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            schoolDbn = SchoolDetailsFragmentArgs.fromBundle(it).schoolDbn
        }
        viewModel = ViewModelProvider(this)[SchoolDetailsViewModel::class.java]
    }
}