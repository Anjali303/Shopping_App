package com.example.shoppinglistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.shoppingapp.R

class ShoppingListFragment : Fragment() {

    private lateinit var textViewShoppingList: TextView
    private val shoppingList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shopping_list, container, false)

        val editTextItem = view.findViewById<EditText>(R.id.editTextItem)
        val spinnerCategory = view.findViewById<Spinner>(R.id.spinnerCategory)
        val radioGroupPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
        val buttonAddItem = view.findViewById<Button>(R.id.buttonAddItem)
        textViewShoppingList = view.findViewById(R.id.textViewShoppingList)

        buttonAddItem.setOnClickListener {
            val itemName = editTextItem.text.toString()
            val category = spinnerCategory.selectedItem.toString()
            val selectedRadioButtonId = radioGroupPriority.checkedRadioButtonId
            val priority = view.findViewById<RadioButton>(selectedRadioButtonId)?.text

            if (itemName.isNotEmpty() && priority != null) {
                val item = "Item: $itemName, Category: $category, Priority: $priority"
                shoppingList.add(item)
                updateShoppingList()
                editTextItem.text.clear()
            } else {
                Toast.makeText(context, "Please enter item details", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun updateShoppingList() {
        textViewShoppingList.text = "Shopping List:\n" + shoppingList.joinToString("\n")
    }

    fun clearList() {
        shoppingList.clear()
        updateShoppingList()
    }
}