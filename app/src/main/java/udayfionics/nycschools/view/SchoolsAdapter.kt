package udayfionics.nycschools.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import udayfionics.nycschools.databinding.ItemSchoolBinding
import udayfionics.nycschools.model.School

class SchoolsAdapter(private val schools: ArrayList<School>) :
    Adapter<SchoolsAdapter.SchoolsViewHolder>() {

    fun updateList(newList: List<School>) {
        val existingListCount = schools.size
        schools.clear()
        notifyItemRangeRemoved(0, existingListCount)
        schools.addAll(newList)
        notifyItemRangeInserted(0, schools.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SchoolsViewHolder(
        ItemSchoolBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = schools.size

    override fun onBindViewHolder(holder: SchoolsViewHolder, position: Int) {
        holder.bind(schools[position])
    }

    inner class SchoolsViewHolder(private var binding: ItemSchoolBinding) :
        ViewHolder(binding.root) {
        fun bind(school: School) {
            binding.school = school
        }
    }
}