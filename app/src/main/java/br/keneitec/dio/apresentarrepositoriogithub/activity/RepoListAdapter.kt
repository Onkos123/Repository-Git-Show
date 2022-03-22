package br.keneitec.dio.apresentarrepositoriogithub.activity

import android.content.res.Configuration
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.keneitec.dio.apresentarrepositoriogithub.R
import br.keneitec.dio.apresentarrepositoriogithub.data.model.Repo
import br.keneitec.dio.apresentarrepositoriogithub.databinding.ItemRepoBinding
import com.bumptech.glide.Glide

class RepoListAdapter() :ListAdapter<Repo,RepoListAdapter.ViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemRepoBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item:Repo){
            binding.tvRepoName.text = item.name
            binding.tvRepoDescription.text = item.description
            binding.tvRepoLanguage.text = item.language
            binding.chipStar.text = item.stargazersCount.toString()

            when (binding.root.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    binding.chipStar.setChipBackgroundColorResource(R.color.gray)
                }
            }

            Glide.with(binding.root.context)
                .load(item.owner.avatarURL).into(binding.ivOwner)

        }
    }


}
class DiffCallBack() : DiffUtil.ItemCallback<Repo>(){
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id

}