package com.brocode.carbon_footprint_app.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.brocode.carbon_footprint_app.R
import com.brocode.carbon_footprint_app.adapters.TreeAdapter
import com.brocode.carbon_footprint_app.models.Tree
import com.brocode.carbon_footprint_app.viewmodels.TreeViewModel
import kotlinx.android.synthetic.main.layout_trees.view.*

class TreesFragment() : Fragment() {
    constructor(context: Context) : this()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.layout_trees, container, false)

        val treeViewModel = ViewModelProviders.of(this).get(TreeViewModel::class.java)
        val treeAdapter=TreeAdapter(context!!, treeViewModel.trees.value as ArrayList<Tree>)
        root.trees_view.layoutManager=LinearLayoutManager(context)
        root.trees_view.adapter=treeAdapter

        return root
    }
}