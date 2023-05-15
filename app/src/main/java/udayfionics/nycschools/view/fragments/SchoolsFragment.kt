package udayfionics.nycschools.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import udayfionics.nycschools.databinding.FragmentSchoolsBinding
import udayfionics.nycschools.view.SchoolsAdapter
import udayfionics.nycschools.viewmodel.SchoolsViewModel

class SchoolsFragment : Fragment() {

    private lateinit var binding: FragmentSchoolsBinding
    private lateinit var viewModel: SchoolsViewModel
    private val schoolsAdapter: SchoolsAdapter = SchoolsAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchoolsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = schoolsAdapter
        }

        viewModel = ViewModelProvider(this)[SchoolsViewModel::class.java]
        viewModel.loadData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.schools.observe(viewLifecycleOwner) {
            schoolsAdapter.updateList(it)
            binding.listView.visibility = View.VISIBLE
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
            if (it) {
                binding.errorTextView.visibility = View.GONE
                binding.listView.visibility = View.GONE
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                binding.errorTextView.visibility = View.VISIBLE
                binding.errorTextView.text = it
                binding.listView.visibility = View.GONE
            } else {
                binding.errorTextView.visibility = View.GONE
            }
        }
    }
}