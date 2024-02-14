package com.ligagriezne.simple

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ligagriezne.simple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemsAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val itemsList = mutableListOf<String>()
        itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsList)
        binding.listViewItems.adapter = itemsAdapter

        binding.buttonAdd.setOnClickListener {
            val newItem = binding.editTextNewItem.text.toString().trim()
            if (newItem.isNotEmpty()) {
                itemsList.add(newItem)
                itemsAdapter.notifyDataSetChanged()
                binding.editTextNewItem.text?.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val item = itemsList[position]
            Toast.makeText(this, "Clicked on: $item", Toast.LENGTH_SHORT).show()
        }

        binding.listViewItems.setOnItemLongClickListener { _, _, position, _ ->
            val item = itemsList[position]
            itemsList.removeAt(position)
            itemsAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Removed: $item", Toast.LENGTH_SHORT).show()
            true
        }
    }
}