package com.dispy.githubusermvp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dispy.githubusermvp.bean.User
import com.dispy.githubusermvp.databinding.ItemUserBinding


class UserAdapter(private val context: Context, private val users: ArrayList<User>): RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private lateinit var listener: OnClickListener

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return UserViewHolder(
            ItemUserBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) {
            val user: User = users[position]
            holder.textLogin.text = user.login
            holder.imgAvatar.load(user.avatarUrl)
            holder.itemView.setOnClickListener {
                listener.onItemClick(user.login)
            }
        }
    }

    fun swapItems(newItems: List<User>) {
        users.addAll(newItems)
        notifyDataSetChanged()
    }

    internal class UserViewHolder(binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgAvatar = binding.imgAvatar
        val textLogin = binding.textLogin
    }

    interface OnClickListener {
        fun onItemClick(login: String)
    }
}
