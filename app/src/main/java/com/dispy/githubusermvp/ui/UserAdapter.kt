package com.dispy.githubusermvp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.dispy.githubusermvp.bean.User
import com.dispy.githubusermvp.databinding.ItemUserBinding
import com.dispy.githubusermvp.databinding.ItemUserHeaderBinding


class UserAdapter(private val context: Context, private val users: ArrayList<User>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listener: OnClickListener
    private val TYPE_NORMAL = 0
    private val TYPE_HEADER = 1

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_HEADER
            else -> TYPE_NORMAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return when (viewType) {
            TYPE_HEADER -> {
                UserHeaderViewHolder(
                    ItemUserHeaderBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> {
                UserViewHolder(
                    ItemUserBinding.inflate(layoutInflater, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) {
            val user: User = users[position]
            holder.textLogin.text = user.login
            holder.imgAvatar.load(user.avatarUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            if (user.type.equals("staff", true)) {
                holder.textStaff.visibility = ViewGroup.VISIBLE
            } else {
                holder.textStaff.visibility = ViewGroup.GONE
            }

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
        val textStaff = binding.textStaff
    }

    internal class UserHeaderViewHolder(binding: ItemUserHeaderBinding) :
            RecyclerView.ViewHolder(binding.root) {
    }

    interface OnClickListener {
        fun onItemClick(login: String)
    }
}
